package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlock;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockstateProvidor extends BlockStateProvider {
    public ModBlockstateProvidor(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlock.HARD_STONE);
        blockWithItem(ModBlock.PERMA_EMPOWERER);
        blockWithItem(ModBlock.POLISHED_HARD_STONE);
    }

    private void blockWithItem(DeferredBlock<?> db) {
        simpleBlockWithItem(db.get(), cubeAll(db.get()));
    }
}
