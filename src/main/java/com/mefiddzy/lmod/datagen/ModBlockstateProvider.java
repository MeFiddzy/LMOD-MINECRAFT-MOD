package com.mefiddzy.lmod.datagen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItemAll(ModBlocks.HARD_STONE);
        blockWithItemTop(ModBlocks.PERMA_EMPOWERER);
        blockWithItemAll(ModBlocks.POLISHED_HARD_STONE);
        blockWithItemAll(ModBlocks.ENPOWERED_GOLD_BLOCK);
        blockWithItemAll(ModBlocks.ENPOWERMENT_POWDER_BLOCK);
        blockWithItemAll(ModBlocks.PINK_DIAMOND_ORE);
        blockWithItemAll(ModBlocks.DEEPSLATE_PINK_DIAMOND_ORE);
        blockWithItemAll(ModBlocks.PINK_DIAMOND_BLOCK);
        blockWithTopAndBottom(ModBlocks.DURACELL_BATTERY_PACK);
    }

    private void blockWithItemAll(DeferredBlock<?> db) {
        simpleBlockWithItem(db.get(), cubeAll(db.get()));
    }

    private void blockWithItemTop(DeferredBlock<?> db) {
        String name = db.getId().getPath();

        simpleBlockWithItem(db.get(), models().cubeTop(
                name,
                modLoc("block/" + name + "_side"),  // side & bottom texture
                modLoc("block/" + name + "_top")    // top texture
        ));
    }

    private void blockWithTopAndBottom(DeferredBlock<?> db) {
        String name = db.getId().getPath();

        simpleBlockWithItem(db.get(), models().cubeBottomTop(
                name,
                modLoc("block/" + name + "_side"),
                modLoc("block/" + name + "_bottom"),
                modLoc("block/" + name + "_top")
        ));
    }


}
