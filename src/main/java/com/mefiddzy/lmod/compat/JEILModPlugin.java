package com.mefiddzy.lmod.compat;


import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.compat.categories.fake.BedrockingRecipeCategory;
import com.mefiddzy.lmod.compat.categories.fake.EnpoweringRecipeCategory;
import com.mefiddzy.lmod.compat.categories.PlateApplierRecipeCategory;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.recipe.ModRecipes;
import com.mefiddzy.lmod.recipe.custom.plate_applier.PlateApplierRecipe;
import com.mefiddzy.lmod.recipe.custom.fake.FakeInOutRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;

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

        registration.addRecipeCategories(new EnpoweringRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()
        ));

        registration.addRecipeCategories(new BedrockingRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()
        ));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        //plate applier
        List<PlateApplierRecipe> plateApplierRecipes = recipeManager.getAllRecipesFor(ModRecipes.PLATE_APPLIER_TYPE.get())
                .stream().map(RecipeHolder::value).toList();

        registration.addRecipes(PlateApplierRecipeCategory.PLATE_APPLIER_RECIPE_RECIPE_TYPE, plateApplierRecipes);

        ///enpowering
        registration.addRecipes(
                EnpoweringRecipeCategory.ENPOWERING_RECIPE_TYPE,
                List.of(new FakeInOutRecipe(
                        Ingredient.of(ModItems.TOUGH_POWDER.get()),
                        new ItemStack(ModItems.ENPOWERMENT_POWDER.get())
                ))
        );

        //bedrocking
        registration.addRecipes(
                BedrockingRecipeCategory.BEDROCKING_RECIPE_TYPE,
                List.of(new FakeInOutRecipe(
                        Ingredient.of(ModBlocks.ENPOWERED_GOLD_BLOCK.get()),
                        new ItemStack(ModItems.ENPOWERED_GOLD_HEART.get())
                ))
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PLATE_APPLIER.asItem()),
                PlateApplierRecipeCategory.PLATE_APPLIER_RECIPE_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModItems.DUST_EMPOWERER.get()),
                EnpoweringRecipeCategory.ENPOWERING_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PERMA_EMPOWERER.get()),
                EnpoweringRecipeCategory.ENPOWERING_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(Blocks.BEDROCK),
                BedrockingRecipeCategory.BEDROCKING_RECIPE_TYPE);
    }
}
