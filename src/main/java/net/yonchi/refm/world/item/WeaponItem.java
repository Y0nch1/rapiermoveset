package yesman.epicfight.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;

public class WeaponItem extends SwordItem {

    public WeaponItem(Tier tier, int damageIn, float speedIn, Item.Properties builder) {
        super(tier, damageIn, speedIn, builder);
    }

    public WeaponItem(Properties rarity, Tier tier) {
        super(tier, 3, 1.5f, rarity);
    }

    public boolean m_8096_(BlockState blockIn) {
        return false;
    }

    public void main() {
    }
}

