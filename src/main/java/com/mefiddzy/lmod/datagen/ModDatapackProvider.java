package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.enchantment.ModEnchantments;
import com.mefiddzy.lmod.trim.ModTrimMat;
import com.mefiddzy.lmod.worldgen.ModBiomeModifier;
import com.mefiddzy.lmod.worldgen.ModConfigFeatures;
import com.mefiddzy.lmod.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bs)
            .add(Registries.TRIM_MATERIAL, ModTrimMat::bs)

            //world gen
            .add(Registries.CONFIGURED_FEATURE, ModConfigFeatures::bs)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bs)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifier::bs);

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(LMod.MOD_ID));
    }
}
