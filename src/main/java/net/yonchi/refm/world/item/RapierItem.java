package net.yonchi.refm.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;

public class RapierItem extends WeaponItem {
    public RapierItem(Item.Properties build, Tier materialIn) {
        super(materialIn, 1, -1.6F, build);
    }

    public RapierItem(Properties rarity) {
        super(new Item.Properties().rarity(Rarity.RARE));
    }
}
