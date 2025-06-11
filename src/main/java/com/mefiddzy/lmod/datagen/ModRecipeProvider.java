package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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
                .unlockedBy("_has_diamond", has(Items.DIAMOND))
                .unlockedBy("_has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PERMA_EMPOWERER.get())
                .pattern("EEE")
                .pattern("ENE")
                .pattern("EEE")
                .define('E', ModBlocks.ENPOWERMENT_POWDER_BLOCK)
                .define('N', Items.NETHER_STAR)
                .unlockedBy("_has_enpower_powder_block", has(ModBlocks.ENPOWERMENT_POWDER_BLOCK))
                .unlockedBy("_has_nether_star", has(Items.NETHER_STAR)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_INGOT.get())
                .requires(ModItems.ENPOWERMENT_POWDER)
                .requires(ModItems.ENPOWERMENT_POWDER)
                .requires(ModItems.ENPOWERMENT_POWDER)
                .requires(ModItems.ENPOWERMENT_POWDER)
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("_has_enpower_powder", has(ModItems.ENPOWERMENT_POWDER)).save(recipeOutput);

        oreSmelting(recipeOutput, List.of(Items.NETHERITE_INGOT), RecipeCategory.MISC ,ModItems.DURACELL, 1.5f, 200, "duracell");
        oreBlasting(recipeOutput, List.of(Items.NETHERITE_INGOT), RecipeCategory.MISC ,ModItems.DURACELL, 1.5f, 100, "duracell");

        stonecutterResultFromBase(recipeOutput, RecipeCategory.MISC, ModBlocks.POLISHED_HARD_STONE,  ModBlocks.HARD_STONE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_STICK.get(), 4)
                .pattern("   ")
                .pattern("  E")
                .pattern("  E")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_PICKAXE.get())
                .pattern("EEE")
                .pattern(" S ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_AXE.get())
                .pattern(" EE")
                .pattern(" SE")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_SWORD.get())
                .pattern(" E ")
                .pattern(" E ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_HOE.get())
                .pattern(" EE")
                .pattern(" S ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_SHOVEL.get())
                .pattern(" E ")
                .pattern(" S ")
                .pattern(" S ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .define('S', ModItems.ENPOWERED_GOLD_STICK)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_HELMET.get())
                .pattern("EEE")
                .pattern("E E")
                .pattern("   ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_CHESTPLATE.get())
                .pattern("E E")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_LEGGINGS.get())
                .pattern("EEE")
                .pattern("E E")
                .pattern("E E")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_BOOTS.get())
                .pattern("E E")
                .pattern("E E")
                .pattern("   ")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENPOWERED_GOLD_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModItems.ENPOWERED_GOLD_INGOT)
                .unlockedBy("_has_enpowered_gold_ingot", has(ModItems.ENPOWERED_GOLD_INGOT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ENPOWERED_GOLD_INGOT.get(), 9)
                .requires(ModBlocks.ENPOWERED_GOLD_BLOCK)
                .unlockedBy("_has_enpowered_gold_block", has(ModBlocks.ENPOWERED_GOLD_BLOCK))
                .save(recipeOutput, "enpowered_gold_ingot_from_unpacking");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AN_ENIGMATIC_ENCOUNTER_MUSIC_DISC.get())
                .pattern("BHB")
                .pattern("RDR")
                .pattern("BPB")
                .define('B', Items.BONE)
                .define('H', Items.SKELETON_SKULL)
                .define('R', Items.REDSTONE)
                .define('P', Items.PHANTOM_MEMBRANE)
                .define('D', Items.MUSIC_DISC_5)
                .unlockedBy("_has_music_disc_5", has(Items.MUSIC_DISC_5))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENPOWERMENT_POWDER_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModItems.ENPOWERMENT_POWDER)
                .unlockedBy("_has_enpowerment_poweder", has(ModItems.ENPOWERMENT_POWDER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ENPOWERMENT_POWDER.get(), 9)
                .requires(ModBlocks.ENPOWERMENT_POWDER_BLOCK)
                .unlockedBy("_has_enpowerment_poweder_block", has(ModBlocks.ENPOWERMENT_POWDER_BLOCK))
                .save(recipeOutput, "enpowerment_poweder_from_unpacking");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PINK_DIAMOND_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModItems.PINK_DIAMOND)
                .unlockedBy("_has_pink_diamond", has(ModItems.PINK_DIAMOND))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PINK_DIAMOND.get(), 9)
                .requires(ModBlocks.PINK_DIAMOND_BLOCK)
                .unlockedBy("_has_pink_diaond_block", has(ModBlocks.PINK_DIAMOND_BLOCK))
                .save(recipeOutput, "pink_diamond_from_unpacking");

        trimSmithing(recipeOutput, ModItems.ENPOWERED_GOLD_INGOT.get(), ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "enpowred_gold"));
    }

}
