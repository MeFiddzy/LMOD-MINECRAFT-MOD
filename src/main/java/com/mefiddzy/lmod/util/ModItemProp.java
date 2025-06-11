package com.mefiddzy.lmod.util;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProp {
    public static void addCustomItemP() {
        ItemProperties.register(ModItems.DUST_EMPOWERER.get(), ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "broken_texture"),
                (stack, level, entity, seed) -> (stack.getMaxDamage() - stack.getDamageValue()) <= 100 ? 1f: 0f);

    }

}
