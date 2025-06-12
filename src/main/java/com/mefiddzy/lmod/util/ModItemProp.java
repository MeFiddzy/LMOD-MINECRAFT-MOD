package com.mefiddzy.lmod.util;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.util.classVariables.Interval;
import com.mefiddzy.lmod.util.component.ModDataComp;
import com.mefiddzy.lmod.util.enums.KillstreakPhases;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProp {


    public static void addCustomItemP() {
        ItemProperties.register(ModItems.DUST_EMPOWERER.get(), ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "broken_texture"),
                (stack, level, entity, seed) -> (stack.getMaxDamage() - stack.getDamageValue()) <= 100 ? 1f: 0f);

        ItemProperties.register(ModItems.KILLSTREAK_SWORD.get(), ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "phase"),
                (stack, level, entity, seed) -> {
                    int kills = stack.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0);
                    KillstreakPhases curPhase = KillstreakPhases.getType(kills);
                    return curPhase.getPhaseNumber();
                });

    }

}
