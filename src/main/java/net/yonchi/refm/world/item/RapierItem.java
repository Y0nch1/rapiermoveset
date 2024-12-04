package net.yonchi.refm.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import yesman.epicfight.world.item.WeaponItem;

public class RapierItem extends WeaponItem {
    public RapierItem(Item.Properties build, Tier materialIn) {
        super(materialIn, 3, -1.6F, build);
    }
}
