package com.mefiddzy.lmod.enchantment;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.enchantment.custom.DiscHunterEnch;
import com.mefiddzy.lmod.enchantment.custom.GuardianAngelEnch;
import com.mefiddzy.lmod.enchantment.custom.LifeLeechEnch;
import com.mefiddzy.lmod.enchantment.custom.OreCollectorEnch;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, LMod.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> GUARDIAN_ANGEL = ENCHANTMENT_EFFECTS.register("guardian_angel",
            () -> GuardianAngelEnch.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> DISC_HUNTER = ENCHANTMENT_EFFECTS.register("disc_hunter",
            () -> DiscHunterEnch.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIFE_LEECH = ENCHANTMENT_EFFECTS.register("life_leech",
            () -> LifeLeechEnch.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ORE_COLLECTOR = ENCHANTMENT_EFFECTS.register("ore_collector",
            () -> OreCollectorEnch.CODEC);

    public static void reg(IEventBus bus) {
        ENCHANTMENT_EFFECTS.register(bus);
    }

}
