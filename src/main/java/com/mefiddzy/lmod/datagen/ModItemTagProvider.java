package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, LMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        /*tag(TAG)
        * .add()
        * .add();*/

        tag(ItemTags.SWORDS)
                .add(ModItems.ENPOWERED_GOLD_SWORD.get());
        tag(ItemTags.AXES)
                .add(ModItems.ENPOWERED_GOLD_AXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.ENPOWERED_GOLD_SHOVEL.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.ENPOWERED_GOLD_PICKAXE.get());
        tag(ItemTags.HOES)
                .add(ModItems.ENPOWERED_GOLD_HOE.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ENPOWERED_GOLD_HELMET.get())
                .add(ModItems.ENPOWERED_GOLD_CHESTPLATE.get())
                .add(ModItems.ENPOWERED_GOLD_LEGGINGS.get())
                .add(ModItems.ENPOWERED_GOLD_BOOTS.get());
    }
}
