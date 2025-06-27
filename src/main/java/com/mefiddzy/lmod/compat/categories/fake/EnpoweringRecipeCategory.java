package com.mefiddzy.lmod.compat.categories.fake;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.recipe.custom.fake.FakeInOutRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class EnpoweringRecipeCategory implements IRecipeCategory<FakeInOutRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "enpowering");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "textures/gui/jei/in_out_gui.png");

    public static final RecipeType<FakeInOutRecipe> ENPOWERING_RECIPE_TYPE =
            new RecipeType<>(UID, FakeInOutRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EnpoweringRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 91);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.DUST_EMPOWERER.get()));
    }

    @Override
    public RecipeType<FakeInOutRecipe> getRecipeType() {
        return ENPOWERING_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Enpowering");
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
    public void setRecipe(IRecipeLayoutBuilder builder, FakeInOutRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 35).addIngredients(recipe.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 105, 35).addItemStack(recipe.output());
    }

    @Override
    public void draw(FakeInOutRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {}
}
