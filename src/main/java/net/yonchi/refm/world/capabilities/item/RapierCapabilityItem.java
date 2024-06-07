package net.yonchi.refm.world.capabilities.item;

import yesman.epicfight.world.capabilities.item.WeaponCategory;

public class RapierCapabilityItem {
    public enum RapierWeaponCategories implements WeaponCategory {
        RAPIER;

        final int id;

        RapierWeaponCategories() {
            this.id = WeaponCategory.ENUM_MANAGER.assign(this);
        }

        @Override
        public int universalOrdinal() {
            return this.id;
        }
    }

    public static class Builder {
    }
}

//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/world/capabilities/item/CapabilityItem.java#L348