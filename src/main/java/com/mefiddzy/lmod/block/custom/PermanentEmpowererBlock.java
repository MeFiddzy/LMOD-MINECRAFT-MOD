package com.mefiddzy.lmod.block.custom;

import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class PermanentEmpowererBlock extends Block {
    public PermanentEmpowererBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return ItemInteractionResult.FAIL;
        }
        if (hand == InteractionHand.OFF_HAND)
            return ItemInteractionResult.SUCCESS;

        if (stack.getItem() == ModItems.TOUGH_POWDER.get()) {
            player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.ENPOWERMENT_POWDER.get(), stack.getCount()));
            level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity)
            return;
        level.setBlock(pos, Blocks.JUKEBOX.defaultBlockState(), 3);
        level.playSound(null, pos, SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS);
        if (entity instanceof Player pl) {
            if (!pl.isCreative())
                entity.kill();
        }
        else {
            entity.kill();
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (!Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.lmod.noshift"));
        }
        else {
            tooltipComponents.add(Component.translatable("tooltip.lmod.block.permanent_empower"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
