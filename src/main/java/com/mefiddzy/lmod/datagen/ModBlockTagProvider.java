package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Mineable
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.HARD_STONE.get())
                .add(ModBlocks.PINK_DIAMOND_ORE.get())
                .add(ModBlocks.DEEPSLATE_PINK_DIAMOND_ORE.get())
                .add(ModBlocks.PINK_DIAMOND_BLOCK.get())
                .add(ModBlocks.ENPOWERED_GOLD_BLOCK.get())
                .add(ModBlocks.POLISHED_HARD_STONE.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.ENPOWERMENT_POWDER_BLOCK.get());

        //Tool level
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PINK_DIAMOND_ORE.get())
                .add(ModBlocks.DEEPSLATE_PINK_DIAMOND_ORE.get())
                .add(ModBlocks.POLISHED_HARD_STONE.get())
                .add(ModBlocks.ENPOWERMENT_POWDER_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PINK_DIAMOND_BLOCK.get())
                .add(ModBlocks.HARD_STONE.get());

        tag(ModTags.Blocks.NEEDS_ENPOWERED_GOLD_TOOL)
                .add(ModBlocks.ENPOWERED_GOLD_BLOCK.get())
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_ENPOWERED_GOLD_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_ENPOWERED_GOLD_TOOL);


        //other
        tag(ModTags.Blocks.ORE_COLLECTOR_DROP_BLOCKS)
                .addTag(BlockTags.BASE_STONE_OVERWORLD);

    }
}
