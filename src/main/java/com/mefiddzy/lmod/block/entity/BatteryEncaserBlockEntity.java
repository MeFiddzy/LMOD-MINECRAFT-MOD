package com.mefiddzy.lmod.block.entity;

import com.mefiddzy.lmod.recipe.ModRecipes;
import com.mefiddzy.lmod.recipe.custom.battery_encaser.BatteryEncaserRecipe;
import com.mefiddzy.lmod.recipe.custom.battery_encaser.BatteryEncaserRecipeInput;
import com.mefiddzy.lmod.recipe.custom.plate_applier.PlateApplierRecipe;
import com.mefiddzy.lmod.screen.custom.battery_encaser.BatteryEncaserMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BatteryEncaserBlockEntity extends BlockEntity implements MenuProvider {
    private static int in_1 = 0, in_2 = 1, out = 2;

    public final ItemStackHandler inv = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    protected final ContainerData data;
    private int curTicks = 0, maxTicks = 40;

    public BatteryEncaserBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BATTERY_ENCASER_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> BatteryEncaserBlockEntity.this.curTicks;
                    case 1 -> BatteryEncaserBlockEntity.this.maxTicks;
                    default -> 0;
                };
            }
            @Override
            public void set(int i, int val) {
                switch (i) {
                    case 0:
                        BatteryEncaserBlockEntity.this.curTicks = val;
                    case 1:
                        BatteryEncaserBlockEntity.this.maxTicks = val;
                }
            }
            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void drop() {
        SimpleContainer invCont = new SimpleContainer(inv.getSlots());
        for (int i = 0; i < inv.getSlots(); i++) {
            invCont.setItem(i, inv.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, invCont);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putInt("battery_encaser.curTick", curTicks);
        tag.putInt("battery_encaser.maxTick", maxTicks);
        tag.put("inventory", inv.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        curTicks = tag.getInt("battery_encaser.curTick");
        maxTicks = tag.getInt("battery_encaser.maxTick");

        inv.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Battery Encaser");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new BatteryEncaserMenu(i, inventory, ((BlockEntity) this));
    }

    public static void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof BatteryEncaserBlockEntity blockEntity)) return;

        ItemStackHandler inv = blockEntity.inv;

        ItemStack input1 = inv.getStackInSlot(in_1);
        ItemStack input2 = inv.getStackInSlot(in_2);
        ItemStack output = inv.getStackInSlot(out);

        BatteryEncaserRecipeInput recipeInput = new BatteryEncaserRecipeInput(input1.copy(), input2.copy());

        Optional<RecipeHolder<BatteryEncaserRecipe>> match = level.getRecipeManager()
                .getRecipeFor(ModRecipes.BATTERY_ENCASER_TYPE.get(), recipeInput, level);

        if (match.isEmpty()) {
            blockEntity.data.set(0, 0);
            return;
        }

        BatteryEncaserRecipe recipe = match.get().value();

        ItemStack recipeOutput = recipe.output();
        boolean canOutput = output.isEmpty() ||
                (ItemStack.isSameItemSameComponents(output, recipeOutput) &&
                        output.getCount() + recipeOutput.getCount() <= output.getMaxStackSize());

        if (!canOutput) {
            blockEntity.data.set(0, 0);
            return;
        }

        if (blockEntity.data.get(0) < blockEntity.data.get(1)) {
            blockEntity.data.set(0, blockEntity.data.get(0) + 1);
            return;
        }

        blockEntity.data.set(0, 0);

        if (input1.isEmpty() || input2.isEmpty()) return;

        input1.shrink(1);
        input2.shrink(1);
        inv.setStackInSlot(in_1, input1);
        inv.setStackInSlot(in_2, input2);

        if (output.isEmpty()) {
            inv.setStackInSlot(out, recipeOutput.copy());
        } else {
            output.grow(recipeOutput.getCount());
            inv.setStackInSlot(out, output);
        }

        blockEntity.setChanged();
        level.sendBlockUpdated(pos, state, state, 3);
    }



    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public @javax.annotation.Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
