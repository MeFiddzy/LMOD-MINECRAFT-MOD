package com.mefiddzy.worldgen;

import com.mefiddzy.lmod.LMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifier {

    public static final ResourceKey<BiomeModifier> ADD_HARD_STONE = regK("add_hard_stone");
    public static final ResourceKey<BiomeModifier> NETHER_ADD_HARD_STONE = regK("nether_add_hard_stone");

    public static void bs(BootstrapContext<BiomeModifier> c) {
        var pf = c.lookup(Registries.PLACED_FEATURE);
        var b = c.lookup(Registries.BIOME);

        c.register(ADD_HARD_STONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                b.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(pf.getOrThrow(ModPlacedFeatures.HARD_STONE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        c.register(NETHER_ADD_HARD_STONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(b.getOrThrow(Biomes.BASALT_DELTAS)),
                HolderSet.direct(pf.getOrThrow(ModPlacedFeatures.NETHER_HARD_STONE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> regK(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
    }
}
