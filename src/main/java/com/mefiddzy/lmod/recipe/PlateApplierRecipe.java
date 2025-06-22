package com.mefiddzy.lmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record PlateApplierRecipe(Ingredient inputItem1, Ingredient inputItem2, ItemStack output, int charge) implements Recipe<PlateApplierRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem1);
        list.add(inputItem2);
        return list;
    }

    public int getCharges() {
        return charge;
    }

    @Override
    public boolean matches(PlateApplierRecipeInput input, Level level) {
        ItemStack stack1 = input.input1();
        ItemStack stack2 = input.input2();



        boolean matchNormal = inputItem1.test(stack1) && inputItem2.test(stack2);
        boolean matchReverse = inputItem1.test(stack2) && inputItem2.test(stack1);

        return matchNormal || matchReverse;
    }


    @Override
    public ItemStack assemble(PlateApplierRecipeInput input, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PLATE_APPLIER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PLATE_APPLIER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<PlateApplierRecipe> {

        public static final MapCodec<PlateApplierRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("input1").forGetter(PlateApplierRecipe::inputItem1),
                Ingredient.CODEC.fieldOf("input2").forGetter(PlateApplierRecipe::inputItem2),
                ItemStack.CODEC.fieldOf("result").forGetter(PlateApplierRecipe::output),
                Codec.INT.fieldOf("charge").forGetter(PlateApplierRecipe::charge)
        ).apply(instance, PlateApplierRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, Integer> INT_STREAM_CODEC =
                StreamCodec.of(
                        (buf, value) -> buf.writeVarInt(value),
                        RegistryFriendlyByteBuf::readVarInt
                );

        public static final StreamCodec<RegistryFriendlyByteBuf, PlateApplierRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, PlateApplierRecipe::inputItem1,
                        Ingredient.CONTENTS_STREAM_CODEC, PlateApplierRecipe::inputItem2,
                        ItemStack.OPTIONAL_STREAM_CODEC, PlateApplierRecipe::output,
                        INT_STREAM_CODEC, PlateApplierRecipe::charge,
                        PlateApplierRecipe::new
                );

        @Override
        public MapCodec<PlateApplierRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PlateApplierRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
