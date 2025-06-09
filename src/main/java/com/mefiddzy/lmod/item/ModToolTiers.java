package com.mefiddzy.lmod.item;

import com.mefiddzy.lmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier ENPOWERED_GOLD = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ENPOWERED_GOLD_TOOL,
            2500, 11.5f, 5.6f, 34, () -> Ingredient.of(ModItems.ENPOWERED_GOLD_INGOT));
}
