package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
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
        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ENPOWERED_GOLD_INGOT.get());

        tag(ModTags.Items.POSSIBLE_FOR_ORE_COLLECTOR)
                .add(Items.COAL)
                .add(Items.GOLD_INGOT)
                .add(Items.DIAMOND)
                .add(Items.NETHERITE_SCRAP)
                .add(Items.COPPER_INGOT)
                .add(ModItems.PINK_DIAMOND.get())
                .add(ModItems.TOUGH_POWDER.get())
                .add(Items.IRON_INGOT);

        tag(ModTags.Items.BATTERIES)
                .add(ModItems.DURACELL.get());
    }
}
