package com.mefiddzy.lmod.datagen;


import com.mefiddzy.lmod.LMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = LMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //loot_tables & recipes
        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        //tags
        BlockTagsProvider bt = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), bt);
        gen.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, bt.contentsGetter(), existingFileHelper));


        //Models and BlockStates
        gen.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeClient(), new ModBlockstateProvider(packOutput, existingFileHelper));
    }
}
