package com.mefiddzy.lmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodPropr {
    public static final FoodProperties DURACELL = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(2.5f)
            .alwaysEdible()
            .fast()
            .usingConvertsTo(Items.IRON_INGOT)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 400, 9), 0.1f)
            .build();

}
