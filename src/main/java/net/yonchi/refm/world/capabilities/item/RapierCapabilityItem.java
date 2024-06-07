package net.yonchi.refm.world.capabilities.item;

import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

public class RapierCapabilityItem {
    public enum WeaponCategories implements WeaponCategory {
        RAPIER;

        final int id;

        WeaponCategories() {
            this.id = WeaponCategory.ENUM_MANAGER.assign(this);
        }

        @Override
        public int universalOrdinal() {
            return this.id;
        }
    }
}
