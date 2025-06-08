package com.mefiddzy.lmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class FuelItem extends Item {

    private int bt = 0; //bt = burn time

    public FuelItem(Properties properties, int bt) {
        super(properties);
        this.bt = bt;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.bt;
    }
}
