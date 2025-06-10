package com.mefiddzy.lmod.block.custom;

import com.mefiddzy.lmod.effect.ModEffects;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

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
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (stack.getItem() == ModItems.TOUGH_POWDER.get()) {
            player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.ENPOWERMENT_POWDER.get(), stack.getCount()));
            level.playSound(null, pos, ModSounds.ENPOWERERS_USE.get(), SoundSource.BLOCKS);
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
                noEffect(entity, level, pos);
        }
        else {
            noEffect(entity, level, pos);
        }
    }

    private void noEffect(Entity entity, Level l, BlockPos pos) {
        entity.hurt(entity.level().damageSources().magic(), Float.MAX_VALUE);
        if (entity instanceof LivingEntity living && living.hasEffect(ModEffects.POTION_REZ_EFFECT)) {
            living.removeEffect(ModEffects.POTION_REZ_EFFECT);
            living.removeEffect(MobEffects.ABSORPTION);
            living.removeEffect(MobEffects.HEALTH_BOOST);
            living.removeEffect(MobEffects.REGENERATION);
            living.removeEffect(MobEffects.DAMAGE_RESISTANCE);
            living.removeEffect(MobEffects.FIRE_RESISTANCE);
            living.removeEffect(MobEffects.WATER_BREATHING);
            living.removeEffect(MobEffects.SATURATION);
            l.playSound(null, pos, SoundEvents.WARDEN_DEATH, SoundSource.BLOCKS);
            if (living instanceof ServerPlayer p) {
                living.setHealth(1);
                p.getFoodData().setFoodLevel(0);
                p.getFoodData().setSaturation(0.0f);
                p.connection.disconnect(Component.literal(""));
            }
            else {
                if (living.getHealth() <= 20) {
                    living.setHealth(1);
                }
                else
                    living.hurt(entity.level().damageSources().magic(), 20);
                living.getBrain().clearMemories();
                living.getBrain().removeAllBehaviors();
            }
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
