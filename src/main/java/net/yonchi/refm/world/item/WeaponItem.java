package net.yonchi.refm.world.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;

public class WeaponItem extends SwordItem {

    public WeaponItem(Tier materialIn, int i, float v, Properties build) {
        super(materialIn, 3, 1.5F, build);
    }

    public boolean m_8096_(BlockState blockIn) {
        return false;
    }

}

