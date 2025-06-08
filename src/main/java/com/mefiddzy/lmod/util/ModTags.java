package com.mefiddzy.lmod.util;

import com.mefiddzy.lmod.LMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        //public static final TagKey<Block> TAG_NAME = createTag("tag_id");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
        }
    }

    public static class Items {
        //public static final TagKey<Item> TAG_NAME = createTag("tag_id");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
        }

    }

}
