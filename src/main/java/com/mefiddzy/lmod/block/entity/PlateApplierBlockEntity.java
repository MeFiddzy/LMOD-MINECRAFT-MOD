package com.mefiddzy.lmod.block.entity;

import com.mefiddzy.lmod.block.custom.BatteryBlock;
import com.mefiddzy.lmod.item.custom.BatteryItem;
import com.mefiddzy.lmod.recipe.ModRecipes;
import com.mefiddzy.lmod.recipe.PlateApplierRecipe;
import com.mefiddzy.lmod.recipe.PlateApplierRecipeInput;
import com.mefiddzy.lmod.screen.custom.PlateApplierMenu;
import com.mefiddzy.lmod.util.ModTags;
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
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Optional;

public class PlateApplierBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inv = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == in_bat)
                return stack.is(ModTags.Items.BATTERIES);
            if (slot == out)
                return false;
            return true;
        }
    };

    public static final int in_1 = 0, in_2 = 1, in_bat = 2, out = 3;

    protected final ContainerData data;
    private int charge = 0;
    private int cooldownTimeTicks = 0;

    public PlateApplierBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PLATE_APPLIER_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PlateApplierBlockEntity.this.charge;
                    case 1 -> PlateApplierBlockEntity.this.cooldownTimeTicks;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PlateApplierBlockEntity.this.charge = value;
                    case 1 -> PlateApplierBlockEntity.this.cooldownTimeTicks = value;
                }
            }


            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Plate Applier");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new PlateApplierMenu(containerId, playerInventory, ((BlockEntity) this));
    }



    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
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

        tag.putInt("plate_applier.charge", charge);
        tag.put("inventory", inv.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        inv.deserializeNBT(registries, tag.getCompound("inventory"));
        charge = tag.getInt("plate_applier.charge");
    }

    public static void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) 
            return;
        
        tickBattery(level, pos);
        tickRecipe(level, pos);
    }

    private static void tickRecipe(Level level, BlockPos pos) {
        PlateApplierBlockEntity blockEntity = (PlateApplierBlockEntity) level.getBlockEntity(pos);
        if (blockEntity == null) return;

        ItemStackHandler inv = blockEntity.inv;

        ItemStack input1 = inv.getStackInSlot(in_1);
        ItemStack input2 = inv.getStackInSlot(in_2);
        int currentCharge = blockEntity.data.get(0);

        PlateApplierRecipeInput input = new PlateApplierRecipeInput(input1, input2);

        if (input == null)
            return;

        Optional<RecipeHolder<PlateApplierRecipe>> match = level.getRecipeManager()
                .getRecipeFor(ModRecipes.PLATE_APPLIER_TYPE.get(), input, level);

        if (match.isPresent()) {
            PlateApplierRecipe recipe = match.get().value();

            if (currentCharge < recipe.charge())
                return;

            ItemStack result = recipe.output();
            ItemStack outputSlot = inv.getStackInSlot(out);

            boolean canOutput =
                    outputSlot.isEmpty() || (ItemStack.isSameItemSameComponents(outputSlot, result)
                                    && outputSlot.getCount() + result.getCount() <= outputSlot.getMaxStackSize());

            if (canOutput) {
                if (outputSlot.isEmpty()) {
                    inv.setStackInSlot(out, result.copy());
                }
                else {
                    outputSlot.grow(result.getCount());
                    inv.setStackInSlot(out, outputSlot);
                }

                input1.shrink(1);
                input2.shrink(1);
                inv.setStackInSlot(in_1, input1);
                inv.setStackInSlot(in_2, input2);

                blockEntity.data.set(0, currentCharge - recipe.charge());
            }
        }
    }



    private static void tickBattery(Level level, BlockPos pos) {
        PlateApplierBlockEntity blockEntity = (PlateApplierBlockEntity) level.getBlockEntity(pos);
        if (blockEntity == null) return;

        if (blockEntity.data.get(1) > 0) {
            blockEntity.data.set(1, blockEntity.data.get(1) - 1);
            return;
        }

        ItemStack batSlot = blockEntity.inv.getStackInSlot(in_bat);
        if (batSlot.is(ModTags.Items.BATTERIES)) {
            int addedCharge = 0;
            Item batSlotItem = batSlot.getItem();

            if (batSlotItem instanceof BatteryItem batteryItem) {
                addedCharge = batteryItem.getBatteryPower();
            }
            else if (batSlotItem instanceof BlockItem blockItem && blockItem.getBlock() instanceof BatteryBlock batteryBlock) {
                addedCharge = batteryBlock.getBatteryPower();
            }

            if (addedCharge > 0) {
                blockEntity.data.set(0, blockEntity.data.get(0) + addedCharge);
                batSlot.shrink(1);
                blockEntity.inv.setStackInSlot(in_bat, batSlot);
                blockEntity.data.set(1, 20);
            }
        }
    }

}
