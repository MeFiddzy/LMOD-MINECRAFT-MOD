package com.mefiddzy.worldgen;

import com.mefiddzy.lmod.LMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> HARD_STONE_PLACED_KEY = regK("hard_stone_placed");
    public static final ResourceKey<PlacedFeature> NETHER_HARD_STONE_PLACED_KEY = regK("nether_hard_stone_placed");

    public static void bs(BootstrapContext<PlacedFeature> c) {
        var cf = c.lookup(Registries.CONFIGURED_FEATURE);

        reg(c, HARD_STONE_PLACED_KEY, cf.getOrThrow(ModConfigFeatures.HARD_STONE_KEY_OVERWORLD), ModOrePlacement.rareOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-10))));
        reg(c, NETHER_HARD_STONE_PLACED_KEY, cf.getOrThrow(ModConfigFeatures.HARD_STONE_KEY_NETHER), ModOrePlacement.rareOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(100))));
    }

    private static ResourceKey<PlacedFeature> regK(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
    }

    private static void reg(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
