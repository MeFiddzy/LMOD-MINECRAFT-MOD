package com.mefiddzy.lmod.screen.custom;

import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.block.entity.PlateApplierBlockEntity;
import com.mefiddzy.lmod.screen.ModMenuTypes;
import com.mefiddzy.lmod.util.ModTags;
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

public class PlateApplierMenu extends AbstractContainerMenu {

    private final Level level;
    private final PlateApplierBlockEntity be;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 4;
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (sourceStack.is(ModTags.Items.BATTERIES)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + PlateApplierBlockEntity.in_bat,
                        TE_INVENTORY_FIRST_SLOT_INDEX + PlateApplierBlockEntity.in_bat + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            }
        }
        else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }


    public PlateApplierMenu(int coId, Inventory inv, FriendlyByteBuf extra) {
        this(coId, inv, inv.player.level().getBlockEntity(extra.readBlockPos()));
    }

    public static final int in_1 = 0, in_2 = 1, in_bat = 2, out = 3;

    public PlateApplierMenu(int containerId, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.PLATE_APPLIER_MENU.get(), containerId);
        this.be = ((PlateApplierBlockEntity) entity);
        this.level = inv.player.level();
        addPlayerHotbar(inv);
        addPlayerInventory(inv);

        this.addSlot(new SlotItemHandler(be.inv, in_1, 45, 35)); // input 1
        this.addSlot(new SlotItemHandler(be.inv, in_2, 115, 35)); // input 2
        this.addSlot(new SlotItemHandler(be.inv, in_bat, 5, 5) { //battery
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.BATTERIES);
            }
        });
        this.addSlot(new SlotItemHandler(be.inv, out, 80, 35)); // output
    }

    private static void giveCommand(ItemStack stack, Player player) {
        boolean added = player.getInventory().add(stack);

        if (!added) {
            player.drop(stack, false);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        boolean valid = stillValid(ContainerLevelAccess.create(level, be.getBlockPos()), player, ModBlocks.PLATE_APPLIER.get());
        return valid;
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
