package net.yonchi.refm.registry.entries;

import net.minecraft.world.item.*;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.world.item.RapierItem;

public final class RapierAddonItems {
    private RapierAddonItems() {}
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(RapierForEpicfight.MOD_ID);

    public static final DeferredItem<Item> IRON_RAPIER = REGISTRY.register("iron_rapier", () ->
            new RapierItem(Tiers.IRON, new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.IRON))));
    public static final DeferredItem<Item> GOLDEN_RAPIER = REGISTRY.register("golden_rapier", () ->
            new RapierItem(Tiers.GOLD, new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.GOLD))));
    public static final DeferredItem<Item> DIAMOND_RAPIER = REGISTRY.register("diamond_rapier", () ->
            new RapierItem(Tiers.DIAMOND, new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.DIAMOND))));
    public static final DeferredItem<Item> NETHERITE_RAPIER = REGISTRY.register("netherite_rapier", () ->
            new RapierItem(Tiers.NETHERITE, new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE))));
    public static final DeferredItem<Item> ENDERITE_RAPIER = REGISTRY.register("enderite_rapier", () ->
            new RapierItem(Tiers.NETHERITE, new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE)).fireResistant().rarity(Rarity.RARE).durability(2851)));
    public static final DeferredItem<Item> WITHERITE_RAPIER = REGISTRY.register("witherite_rapier", () ->
            new RapierItem(Tiers.NETHERITE, new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE)).fireResistant().rarity(Rarity.RARE).durability(2851)));
    public static final DeferredItem<Item> OCEANITE_RAPIER = REGISTRY.register("oceanite_rapier", () ->
            new RapierItem(Tiers.NETHERITE, new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE)).fireResistant().rarity(Rarity.RARE).durability(2851)));

    public static final DeferredItem<Item> END_UPGRADE = REGISTRY.register("end_upgrade", () ->
            new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final DeferredItem<Item> WITHER_UPGRADE = REGISTRY.register("wither_upgrade", () ->
            new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final DeferredItem<Item> OCEAN_UPGRADE = REGISTRY.register("ocean_upgrade", () ->
            new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));

    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
}