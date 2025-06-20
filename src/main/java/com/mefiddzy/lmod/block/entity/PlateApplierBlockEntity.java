package com.mefiddzy.lmod.block.entity;

import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.screen.custom.PlateApplierMenu;
import com.mefiddzy.lmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;

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
            if (slot == 2)
                return stack.getItem() == ModItems.DURACELL.get();
            return true;
        }
    };

    public static final int in_1 = 0, in_2 = 1, in_bat = 2, out = 3;

    protected final ContainerData data;
    private int charge = 0;

    public PlateApplierBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PLATE_APPLIER_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                return PlateApplierBlockEntity.this.charge;
            }

            @Override
            public void set(int index, int value) {
                PlateApplierBlockEntity.this.charge = value;
            }

            @Override
            public int getCount() {
                return 1;
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

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {

        tag.putInt("plate_applier.charge", charge);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        charge = tag.getInt("plate_applier.charge");
    }

    public void tick(Level lv, BlockPos pos, BlockState bstate) {
        if (lv.getBlockEntity(pos) instanceof PlateApplierBlockEntity blockEntity) {
            ItemStackHandler inv = blockEntity.inv;

            if (inv.getStackInSlot(in_bat).is(ModTags.Items.BATTERIES)) {

            }
        }
    }
}
