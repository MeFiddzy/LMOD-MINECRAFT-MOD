package com.mefiddzy.lmod.item;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.custom.DustEnpowererItem;
import com.mefiddzy.lmod.item.custom.FuelItem;
import com.mefiddzy.lmod.sounds.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LMod.MOD_ID);

    public static final DeferredItem<Item> ENPOWERED_GOLD_INGOT = ITEMS.register("enpowered_gold_ingot",
            () -> new Item(new Item.Properties()));

 public static final DeferredItem<Item> PINK_DIAMOND = ITEMS.register("pink_diamond",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ENPOWERMENT_POWDER = ITEMS.register("enpowerment_powder",
            () -> new FuelItem(new Item.Properties(), 6400)); //200 - 1 item

    public static final DeferredItem<Item> TOUGH_POWDER = ITEMS.register("tough_powder",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DUST_EMPOWERER = ITEMS.register("dust_enpowerer",
            () -> new DustEnpowererItem(new Item.Properties().durability(150)));

    public static final DeferredItem<Item> ENPOWERED_GOLD_STICK = ITEMS.register("enpowered_gold_stick",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AN_ENIGMATIC_ENCOUNTER_MUSIC_DISC = ITEMS.register("an_enigmatic_encounter_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.AN_ENIGMATIC_ENCOUNTER_KEY).stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.lmod.item.an_enigmatic_encounter"));
                }
            });


    public static final DeferredItem<Item> DURACELL = ITEMS.register("duracell",
            () -> new Item(new Item.Properties().food(ModFoodPropr.DURACELL)){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if (!Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.lmod.noshift"));
                    }
                    else {
                        tooltipComponents.add(Component.translatable("tooltip.lmod.item.duracell"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }
            });

    public static final DeferredItem<SwordItem> ENPOWERED_GOLD_SWORD = ITEMS.register("enpowered_gold_sword",
            () -> new SwordItem(ModToolTiers.ENPOWERED_GOLD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ENPOWERED_GOLD, 5f, -2f))));
    public static final DeferredItem<AxeItem> ENPOWERED_GOLD_AXE = ITEMS.register("enpowered_gold_axe",
            () -> new AxeItem(ModToolTiers.ENPOWERED_GOLD, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.ENPOWERED_GOLD, 6f,  -2.4f))));
    public static final DeferredItem<PickaxeItem> ENPOWERED_GOLD_PICKAXE = ITEMS.register("enpowered_gold_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ENPOWERED_GOLD, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ENPOWERED_GOLD, 0.7f, -2.3f))));
    public static final DeferredItem<ShovelItem> ENPOWERED_GOLD_SHOVEL = ITEMS.register("enpowered_gold_shovel",
            () -> new ShovelItem(ModToolTiers.ENPOWERED_GOLD, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.ENPOWERED_GOLD, 2.4f, -3f))));
    public static final DeferredItem<HoeItem> ENPOWERED_GOLD_HOE = ITEMS.register("enpowered_gold_hoe",
            () -> new HoeItem(ModToolTiers.ENPOWERED_GOLD, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.ENPOWERED_GOLD, -4f, 0f))));

    public static final DeferredItem<ArmorItem> ENPOWERED_GOLD_HELMET = ITEMS.register("enpowered_gold_helmet",
            () -> new ArmorItem(ModArmorMateterial.ENPOWERED_GOLD_ARMOR_MAT, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40))));
    public static final DeferredItem<ArmorItem> ENPOWERED_GOLD_CHESTPLATE = ITEMS.register("enpowered_gold_chestplate",
            () -> new ArmorItem(ModArmorMateterial.ENPOWERED_GOLD_ARMOR_MAT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
    public static final DeferredItem<ArmorItem> ENPOWERED_GOLD_LEGGINGS = ITEMS.register("enpowered_gold_leggings",
            () -> new ArmorItem(ModArmorMateterial.ENPOWERED_GOLD_ARMOR_MAT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
    public static final DeferredItem<ArmorItem> ENPOWERED_GOLD_BOOTS = ITEMS.register("enpowered_gold_boots",
            () -> new ArmorItem(ModArmorMateterial.ENPOWERED_GOLD_ARMOR_MAT, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40))));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
