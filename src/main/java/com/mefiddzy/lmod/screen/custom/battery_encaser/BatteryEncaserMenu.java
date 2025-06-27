package com.mefiddzy.lmod.screen.custom.battery_encaser;

import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.block.entity.BatteryEncaserBlockEntity;
import com.mefiddzy.lmod.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class BatteryEncaserMenu extends AbstractContainerMenu {

    private final Level level;
    private final BatteryEncaserBlockEntity BEBlockEntity;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 3;

    private static int in_1 = 0, in_2 = 1, out = 2;

    public BatteryEncaserMenu(int coId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.BATTERY_ENCASER_MENU.get(), coId);
        this.BEBlockEntity = ((BatteryEncaserBlockEntity) blockEntity);
        this.level = inv.player.level();
        addPlayerHotbar(inv);
        addPlayerInventory(inv);

        this.addSlot(new SlotItemHandler(BEBlockEntity.inv, in_1, 55, 23));
        this.addSlot(new SlotItemHandler(BEBlockEntity.inv, in_2, 55, 48));

        this.addSlot(new SlotItemHandler(BEBlockEntity.inv, out, 109, 35));
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    public BatteryEncaserMenu(int coId, Inventory inv, FriendlyByteBuf extra) {
        this(coId, inv, inv.player.level().getBlockEntity(extra.readBlockPos()));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, BEBlockEntity.getBlockPos()), player, ModBlocks.BATTERY_ENCASER.get());
    }

    private void addPlayerInventory(Inventory pli) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(pli, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory pli) {
        for (int i = 0; i< 9; i++) {
            this.addSlot(new Slot(pli, i, 8 + i * 18, 142));
        }
    }
}
