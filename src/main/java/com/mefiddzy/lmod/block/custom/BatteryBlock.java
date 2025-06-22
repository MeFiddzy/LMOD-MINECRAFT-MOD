package com.mefiddzy.lmod.block.custom;

import net.minecraft.world.level.block.Block;

public class BatteryBlock extends Block {
    private final int batteryPower;

    public BatteryBlock(Properties properties, int batteryPower) {
        super(properties);
        this.batteryPower = batteryPower;
    }

    public int getBatteryPower() {
        return batteryPower;
    }
}
