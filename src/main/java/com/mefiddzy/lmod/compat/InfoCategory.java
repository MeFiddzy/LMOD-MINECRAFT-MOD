package com.mefiddzy.lmod.compat;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.recipe.InfoRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class InfoCategory implements IRecipeCategory<InfoRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "info");
    private final IDrawable background;

    public InfoCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(150, 60);
    }

    @Override
    public RecipeType<InfoRecipe> getRecipeType() {
        return new RecipeType<>(UID, InfoRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.literal("Info");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InfoRecipe recipe, IFocusGroup focuses) {

    }

    @Override
    public void draw(InfoRecipe recipe, IRecipeSlotsView slotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;
        int y = 5;
        for (Component line : recipe.lines()) {
            guiGraphics.drawString(font, line, 5, y, 0x404040, false);
            y += 10;
        }
    }
}

