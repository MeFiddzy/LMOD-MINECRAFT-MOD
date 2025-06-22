package com.mefiddzy.lmod.compat;


import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.recipe.ModRecipes;
import com.mefiddzy.lmod.recipe.PlateApplierRecipe;
import com.mefiddzy.lmod.screen.custom.PlateApplierScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEILModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PlateApplierRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()
        ));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<PlateApplierRecipe> plateApplierRecipes = recipeManager.getAllRecipesFor(ModRecipes.PLATE_APPLIER_TYPE.get())
                .stream().map(RecipeHolder::value).toList();

        registration.addRecipes(PlateApplierRecipeCategory.PLATE_APPLIER_RECIPE_RECIPE_TYPE, plateApplierRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(PlateApplierScreen.class, 63, 35, 15, 16,
                PlateApplierRecipeCategory.PLATE_APPLIER_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(PlateApplierScreen.class, 98, 35, 15, 16,
                PlateApplierRecipeCategory.PLATE_APPLIER_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PLATE_APPLIER.asItem()),
                PlateApplierRecipeCategory.PLATE_APPLIER_RECIPE_RECIPE_TYPE);
    }
}
