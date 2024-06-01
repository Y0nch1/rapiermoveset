package yesman.epicfight.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EpicFightItems {
    public static DeferredRegister<Item> ITEMS;
    public static RegistryObject<SwordItem> RAIPER;

    public EpicFightItems() {
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "epicfight");
        RAIPER = ITEMS.register("uchigatana", () -> get());
    }

    private static SwordItem get() {
        return new RaiperItem((new Item.Properties()).rarity(Rarity.RARE));
    }
}