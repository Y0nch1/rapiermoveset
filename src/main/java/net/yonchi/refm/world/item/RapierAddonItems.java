package net.yonchi.refm.world.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.yonchi.refm.RapierForEpicfight;

public class RapierAddonItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RapierForEpicfight.MOD_ID);

    public static final RegistryObject<Item> IRON_RAPIER =
            ITEMS.register("iron_rapier", () -> new RapierItem(new Item.Properties(),Tiers.IRON));
    public static final RegistryObject<Item> GOLD_RAPIER =
            ITEMS.register("gold_rapier", () -> new RapierItem(new Item.Properties(),Tiers.GOLD));
    public static final RegistryObject<Item> DIAMOND_RAPIER =
            ITEMS.register("diamond_rapier", () -> new RapierItem(new Item.Properties(),Tiers.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_RAPIER =
            ITEMS.register("netherite_rapier", () -> new RapierItem(new Item.Properties().fireResistant(),Tiers.NETHERITE));
    public static final RegistryObject<Item> ENDERITE_RAPIER =
            ITEMS.register("enderite_rapier", () -> new RapierItem(new Item.Properties().fireResistant().rarity(Rarity.RARE).defaultDurability(2851),Tiers.NETHERITE));
    public static final RegistryObject<Item> WITHERITE_RAPIER =
            ITEMS.register("witherite_rapier", () -> new RapierItem(new Item.Properties().fireResistant().rarity(Rarity.RARE).defaultDurability(2851),Tiers.NETHERITE));
    public static final RegistryObject<Item> OCEANITE_RAPIER =
            ITEMS.register("oceanite_rapier", () -> new RapierItem(new Item.Properties().fireResistant().rarity(Rarity.RARE).defaultDurability(2851),Tiers.DIAMOND));
    public static final RegistryObject<Item> END_UPGRADE =
            ITEMS.register("end_upgrade", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final RegistryObject<Item> WITHER_UPGRADE =
            ITEMS.register("wither_upgrade", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final RegistryObject<Item> OCEAN_UPGRADE =
            ITEMS.register("ocean_upgrade", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}