package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvidor extends ItemModelProvider {
    public ModItemModelProvidor(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.TOUGH_POWDER.get());
        basicItem(ModItems.ENPOWERMENT_POWDER.get());
        basicItem(ModItems.ENPOWERED_GOLD_INGOT.get());
        basicItem(ModItems.DURACELL.get());
        basicItem(ModItems.DUST_EMPOWERER.get());
    }
}
