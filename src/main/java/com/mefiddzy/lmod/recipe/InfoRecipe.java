package com.mefiddzy.lmod.recipe;

import net.minecraft.world.item.ItemStack;
import java.util.List;
import net.minecraft.network.chat.Component;

public record InfoRecipe(ItemStack displayItem, List<Component> lines) {}

