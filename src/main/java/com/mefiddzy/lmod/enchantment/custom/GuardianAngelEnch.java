package com.mefiddzy.lmod.enchantment.custom;

import com.mefiddzy.lmod.effect.ModMobEffects;
import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record GuardianAngelEnch() implements EnchantmentEntityEffect {

    public static final MapCodec<GuardianAngelEnch> CODEC = MapCodec.unit(GuardianAngelEnch::new);

    @Override
    public void apply(ServerLevel l, int el, EnchantedItemInUse i, Entity e, Vec3 o) {
        if (e instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 0));
            if (el > 1) {
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1));
            }
            if (el == 3) {
                living.addEffect(new MobEffectInstance(ModMobEffects.POTION_REZ_EFFECT, 400));
            }
            living.setHealth(living.getMaxHealth());
        }
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
