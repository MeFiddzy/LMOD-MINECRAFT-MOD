package com.mefiddzy.lmod.item;

import com.mefiddzy.lmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class  ModToolTiers {
    public static final Tier ENPOWERED_GOLD = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ENPOWERED_GOLD_TOOL,
            3019, 12.3f, 5.0f, 29, () -> Ingredient.of(ModItems.ENPOWERED_GOLD_INGOT));

    public static final Tier KILLSTREAK = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_KILLSTREAK,
            3505, 12.3f, 5.0f, 0, () -> Ingredient.of(ModItems.ENPOWERED_GOLD_INGOT));
}
