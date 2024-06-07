package net.yonchi.refm.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yonchi.refm.RapierForEpicfight;

public class RapierAddonItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RapierForEpicfight.MOD_ID);
    public static final RegistryObject<SwordItem> RAIPER = ITEMS.register("rapier", () -> new RapierItem(new Item.Properties().rarity(Rarity.RARE),Tiers.IRON));

}