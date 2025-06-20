package com.mefiddzy.lmod.effect;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.effect.effects.PotionResistaceClass;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, LMod.MOD_ID);

    public static final Holder<MobEffect> POTION_REZ_EFFECT = MOB_EFFECTS.register("potion_rez",
            () -> new PotionResistaceClass(MobEffectCategory.BENEFICIAL, 0x576354));

    public static void reg(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }

}
