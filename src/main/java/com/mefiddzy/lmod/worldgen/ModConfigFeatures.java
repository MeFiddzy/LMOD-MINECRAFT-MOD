package com.mefiddzy.lmod.worldgen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfigFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> HARD_STONE_KEY_OVERWORLD = regK("hard_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HARD_STONE_KEY_NETHER = regK("nether_hard_stone");

    public static void bs(BootstrapContext<ConfiguredFeature<?, ?>> c) {
        RuleTest deepslateReplace = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplace = new BlockMatchTest(Blocks.NETHERRACK);

        reg(c, HARD_STONE_KEY_OVERWORLD, Feature.ORE, new OreConfiguration(
                deepslateReplace,
                ModBlocks.HARD_STONE.get().defaultBlockState(),
                5, // Vein size
                0.2f // Discard chance if exposed to air
        ));

        // Nether: Larger veins (20 blocks), 20% discard chance
        reg(c, HARD_STONE_KEY_NETHER, Feature.ORE, new OreConfiguration(
                netherrackReplace,
                ModBlocks.HARD_STONE.get().defaultBlockState(),
                5, // Vein size
                0.2f // Discard chance if exposed to air
        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> regK(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void reg(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}