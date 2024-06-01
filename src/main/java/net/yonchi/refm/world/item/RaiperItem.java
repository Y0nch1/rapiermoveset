package yesman.epicfight.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class RaiperItem extends WeaponItem {
    public RaiperItem(Item.Properties build, Tier materialIn) {
        super(materialIn, 5, -3.2F, build);
    }
}
