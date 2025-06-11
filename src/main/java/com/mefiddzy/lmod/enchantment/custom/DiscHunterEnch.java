package com.mefiddzy.lmod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record DiscHunterEnch() implements EnchantmentEntityEffect {

    public static final MapCodec<DiscHunterEnch> CODEC = MapCodec.unit(DiscHunterEnch::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse itemInUse, Entity entity, Vec3 origin) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        if (livingEntity instanceof Player) return;
        if (livingEntity.getHealth() > 0) return;

        BlockPos pos = livingEntity.getOnPos();
        ItemStack discStack = ItemStack.EMPTY;

        if (livingEntity instanceof Warden) {
            discStack = new ItemStack(Items.MUSIC_DISC_5);
        } else if (livingEntity instanceof Piglin) {
            discStack = new ItemStack(Items.MUSIC_DISC_PIGSTEP);
        } else if (livingEntity instanceof Breeze) {
            discStack = new ItemStack(Items.MUSIC_DISC_CREATOR);
        } else if (livingEntity instanceof Bogged) {
            discStack = new ItemStack(Items.MUSIC_DISC_CREATOR_MUSIC_BOX);
        } else if (livingEntity instanceof Parrot) {
            discStack = new ItemStack(Items.MUSIC_DISC_CHIRP);
        } else if (livingEntity instanceof Cat || livingEntity instanceof Ocelot) {
            discStack = new ItemStack(Items.MUSIC_DISC_CAT);
        } else if (livingEntity instanceof ZombifiedPiglin) {
            discStack = new ItemStack(Items.MUSIC_DISC_RELIC);
        } else if (livingEntity instanceof Skeleton || livingEntity instanceof Creeper) {
            Item[] possibleDiscs = {
                    Items.MUSIC_DISC_13, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_BLOCKS,
                    Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL,
                    Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD,
                    Items.MUSIC_DISC_WARD, Items.MUSIC_DISC_WAIT, Items.MUSIC_DISC_OTHERSIDE
            };
            discStack = new ItemStack(possibleDiscs[level.getRandom().nextInt(possibleDiscs.length)]);
        }

        if (!discStack.isEmpty()) {
            ItemEntity discEntity = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), discStack);
            level.addFreshEntity(discEntity);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
