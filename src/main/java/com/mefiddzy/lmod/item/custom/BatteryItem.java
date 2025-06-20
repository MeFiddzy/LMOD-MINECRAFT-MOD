package com.mefiddzy.lmod.item.custom;

import net.minecraft.world.item.Item;

public class BatteryItem extends Item {
    private int batteryPower;

    public BatteryItem(Properties properties, int batteryPower) {
        super(properties);
        this.batteryPower = batteryPower;
    }

    public int getBatteryPower() {
        return batteryPower;
    }
}
