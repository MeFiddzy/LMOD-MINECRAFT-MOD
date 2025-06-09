package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = LMod.MOD_ID;

        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath);
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }



    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.TOUGH_POWDER.get());
        basicItem(ModItems.ENPOWERMENT_POWDER.get());
        basicItem(ModItems.ENPOWERED_GOLD_INGOT.get());
        basicItem(ModItems.DURACELL.get());
        basicItem(ModItems.DUST_EMPOWERER.get());
        basicItem(ModItems.ENPOWERED_GOLD_STICK.get());
        basicItem(ModItems.AN_ENIGMATIC_ENCOUNTER_MUSIC_DISC.get());

        handheldItem(ModItems.ENPOWERED_GOLD_PICKAXE.get());
        handheldItem(ModItems.ENPOWERED_GOLD_SHOVEL.get());
        handheldItem(ModItems.ENPOWERED_GOLD_AXE.get());
        handheldItem(ModItems.ENPOWERED_GOLD_HOE.get());
        handheldItem(ModItems.ENPOWERED_GOLD_SWORD.get());

        trimmedArmorItem(ModItems.ENPOWERED_GOLD_HELMET);
        trimmedArmorItem(ModItems.ENPOWERED_GOLD_CHESTPLATE);
        trimmedArmorItem(ModItems.ENPOWERED_GOLD_LEGGINGS);
        trimmedArmorItem(ModItems.ENPOWERED_GOLD_BOOTS);
    }

    private ItemModelBuilder handheldItem (DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0", ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
