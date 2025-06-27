package com.mefiddzy.lmod.compat.categories;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.recipe.custom.plate_applier.PlateApplierRecipe;
import com.mefiddzy.lmod.util.ModTags;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

public class PlateApplierRecipeCategory implements IRecipeCategory<PlateApplierRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "plate_applier");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID,
            "textures/gui/jei/plate_applier_gui.png");

    public static final RecipeType<PlateApplierRecipe> PLATE_APPLIER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, PlateApplierRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PlateApplierRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 91);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.PLATE_APPLIER.asItem()));
    }

    @Override
    public RecipeType<PlateApplierRecipe> getRecipeType() {
        return PLATE_APPLIER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("PLate Applier");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PlateApplierRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 45, 35).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 115, 35).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 5, 5).addIngredients(Ingredient.of(ModTags.Items.BATTERIES));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 35).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(PlateApplierRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        Font font = Minecraft.getInstance().font;
        guiGraphics.drawString(font, "Needed Charges: " + recipe.getCharges(), 44, 55, 0x000000, false);
        background.draw(guiGraphics);
    }

}
