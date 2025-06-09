package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.block.ModBlock;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DUST_EMPOWERER.get())
                .pattern(" G ")
                .pattern(" DG")
                .pattern("  G")
                .define('G', Items.GOLD_INGOT)
                .define('D', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .unlockedBy("has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.PERMA_EMPOWERER.get())
                .pattern("EEE")
                .pattern("ENE")
                .pattern("EEE")
                .define('E', ModItems.ENPOWERMENT_POWDER)
                .define('N', Items.NETHER_STAR)
                .unlockedBy("has_enpower_powder", has(ModItems.ENPOWERMENT_POWDER))
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_INGOT.get())
                .requires(ModItems.ENPOWERMENT_POWDER)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_enpower_powder", has(ModItems.ENPOWERMENT_POWDER)).save(recipeOutput);

        oreSmelting(recipeOutput, List.of(Items.NETHERITE_INGOT), RecipeCategory.MISC ,ModItems.DURACELL, 1.5f, 200, "duracell");
        oreBlasting(recipeOutput, List.of(Items.NETHERITE_INGOT), RecipeCategory.MISC ,ModItems.DURACELL, 1.5f, 100, "duracell");

        stonecutterResultFromBase(recipeOutput, RecipeCategory.MISC, ModBlock.POLISHED_HARD_STONE,  ModBlock.HARD_STONE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_STICK.get(), 4)
                .pattern("   ")
                .pattern("  E")
                .pattern("  E")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .unlockedBy("has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_PICKAXE.get())
                .pattern("EEE")
                .pattern(" S ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_AXE.get())
                .pattern(" EE")
                .pattern(" SE")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_SWORD.get())
                .pattern(" E ")
                .pattern(" E ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_HOE.get())
                .pattern(" EE")
                .pattern(" S ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_SHOVEL.get())
                .pattern(" E ")
                .pattern(" S ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);
    }

}
