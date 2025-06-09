package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlock;
import com.mefiddzy.lmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
                .add(ModBlock.HARD_STONE.get())
                .add(ModBlock.POLISHED_HARD_STONE.get());

        //Tool level
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlock.POLISHED_HARD_STONE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlock.HARD_STONE.get());

        tag(ModTags.Blocks.NEEDS_ENPOWERED_GOLD_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_ENPOWERED_GOLD_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_ENPOWERED_GOLD_TOOL);
    }
}
