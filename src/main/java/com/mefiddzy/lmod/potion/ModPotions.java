package com.mefiddzy.lmod.potion;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, LMod.MOD_ID);

    public static final Holder<Potion> POTION_REZ_POTION = POTIONS.register("potion_rez_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.POTION_REZ_EFFECT, 3600)));

    public static void reg(IEventBus bus) {
        POTIONS.register(bus);
    }
}
