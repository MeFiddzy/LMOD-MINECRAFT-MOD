package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    protected LootTable.Builder createMultipleOreDrop(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    protected LootTable.Builder createMultipleOreDropNoFortune(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                )
        );
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.POLISHED_HARD_STONE.get());
        dropSelf(ModBlocks.ENPOWERED_GOLD_BLOCK.get());
        dropSelf(ModBlocks.ENPOWERMENT_POWDER_BLOCK.get());
        dropSelf(ModBlocks.PINK_DIAMOND_BLOCK.get());
        dropSelf(ModBlocks.PLATE_APPLIER.get());
        add(ModBlocks.HARD_STONE.get(),
                block -> createMultipleOreDropNoFortune(ModBlocks.HARD_STONE.get(), ModItems.TOUGH_POWDER.get(), 1.00f, 1.00f));
        add(ModBlocks.DEEPSLATE_PINK_DIAMOND_ORE.get(),
                block -> createMultipleOreDrop(ModBlocks.DEEPSLATE_PINK_DIAMOND_ORE.get(), ModItems.PINK_DIAMOND.get(), 1.00f, 2.00f));
        add(ModBlocks.PINK_DIAMOND_ORE.get(),
                block -> createMultipleOreDrop(ModBlocks.PINK_DIAMOND_ORE.get(), ModItems.PINK_DIAMOND.get(), 1.00f, 2.00f));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
