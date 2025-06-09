package com.mefiddzy.lmod.item.custom;

import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.sounds.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.List;

public class DustEnpowererItem extends Item {
    public DustEnpowererItem(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (player != null && !level.isClientSide()) {
            ItemStack offhandStack = player.getOffhandItem();

            if (offhandStack.getItem() == ModItems.TOUGH_POWDER.get() && player.blockPosition().getY() == 320 && clickedBlock == Blocks.OBSIDIAN) {
                level.setBlock(context.getClickedPos(), Blocks.COBBLESTONE.defaultBlockState(), 3);

                player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(ModItems.ENPOWERMENT_POWDER.get(), offhandStack.getCount()));
                context.getItemInHand().hurtAndBreak(offhandStack.getCount(), ((ServerLevel) level), player, item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), ModSounds.ENPOWERERS_USE.get(), SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }



    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (!Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.lmod.noshift"));
        }
        else {
            tooltipComponents.add(Component.translatable("tooltip.lmod.item.dust_empower"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }


}
