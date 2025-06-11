package com.mefiddzy.lmod.trim;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class ModTrimMat {
    public static final ResourceKey<TrimMaterial> EPOWERED_GOLD =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "enpowred_gold"));

    public static void bs(BootstrapContext<TrimMaterial> context) {
        reg(context, EPOWERED_GOLD, ModItems.ENPOWERED_GOLD_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#dfbce6").getOrThrow()), 1f);
    }

    private static void reg(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item,
                                 Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimmaterial);
    }
}
