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

        public static final TagKey<Block> NEEDS_ENPOWERED_GOLD_TOOL = createTag("needs_enpowered_gold_tool");
        public static final TagKey<Block> INCORRECT_FOR_ENPOWERED_GOLD_TOOL = createTag("incorrect_for_enpowered_gold_tool");

        public static final TagKey<Block> NEEDS_PINK_DIAMOND_TOOL = createTag("needs_enpowered_gold_tool");
        public static final TagKey<Block> INCORRECT_FOR_PINK_DIAMOND_TOOL = createTag("incorrect_for_enpowered_gold_tool");

        public static final TagKey<Block> ORE_COLLECTOR_DROP_BLOCKS = createTag("ore_collector_drop_blocks");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> POSSIBLE_FOR_ORE_COLLECTOR = createTag("possible_for_ore_collector");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
        }

    }

}
