    package com.mefiddzy.lmod.recipe.custom.battery_encaser;

    import com.mefiddzy.lmod.recipe.ModRecipes;
    import com.mefiddzy.lmod.recipe.custom.plate_applier.PlateApplierRecipe;
    import com.mojang.serialization.MapCodec;
    import com.mojang.serialization.codecs.RecordCodecBuilder;
    import net.minecraft.core.HolderLookup;
    import net.minecraft.network.RegistryFriendlyByteBuf;
    import net.minecraft.network.codec.StreamCodec;
    import net.minecraft.world.item.ItemStack;
    import net.minecraft.world.item.crafting.Ingredient;
    import net.minecraft.world.item.crafting.Recipe;
    import net.minecraft.world.item.crafting.RecipeSerializer;
    import net.minecraft.world.item.crafting.RecipeType;
    import net.minecraft.world.level.Level;

    public record BatteryEncaserRecipe(Ingredient inputItem1, Ingredient inputItem2, ItemStack output) implements Recipe<BatteryEncaserRecipeInput> {
        @Override
        public boolean matches(BatteryEncaserRecipeInput input, Level level) {
            ItemStack stack1 = input.input1();
            ItemStack stack2 = input.input2();

            boolean normalMatch = inputItem1.test(stack1) && inputItem2.test(stack2);
            boolean reverseMatch = inputItem1.test(stack2) && inputItem2.test(stack1);

            return normalMatch || reverseMatch;
        }

        @Override
        public ItemStack assemble(BatteryEncaserRecipeInput input, HolderLookup.Provider registries) {
            return output.copy();
        }

        @Override
        public boolean canCraftInDimensions(int width, int height) {
            return true;
        }

        @Override
        public ItemStack getResultItem(HolderLookup.Provider registries) {
            return output;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return ModRecipes.BATTERY_ENCASER_SERIALIZER.get();
        }

        @Override
        public RecipeType<?> getType() {
            return ModRecipes.BATTERY_ENCASER_TYPE.get();
        }

        public static class Serializer implements RecipeSerializer<BatteryEncaserRecipe> {

            public static final MapCodec<BatteryEncaserRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Ingredient.CODEC.fieldOf("input1").forGetter(BatteryEncaserRecipe::inputItem1),
                    Ingredient.CODEC.fieldOf("input2").forGetter(BatteryEncaserRecipe::inputItem2),
                    ItemStack.CODEC.fieldOf("result").forGetter(BatteryEncaserRecipe::output)
            ).apply(instance, BatteryEncaserRecipe::new));

            public static final StreamCodec<RegistryFriendlyByteBuf, BatteryEncaserRecipe> STREAM_CODEC =
                    StreamCodec.composite(
                            Ingredient.CONTENTS_STREAM_CODEC, BatteryEncaserRecipe::inputItem1,
                            Ingredient.CONTENTS_STREAM_CODEC, BatteryEncaserRecipe::inputItem2,
                            ItemStack.OPTIONAL_STREAM_CODEC, BatteryEncaserRecipe::output,
                            BatteryEncaserRecipe::new
                    );

            @Override
            public MapCodec<BatteryEncaserRecipe> codec() {
                return CODEC;
            }

            @Override
            public StreamCodec<RegistryFriendlyByteBuf, BatteryEncaserRecipe> streamCodec() {
                return STREAM_CODEC;
            }
        }
    }
