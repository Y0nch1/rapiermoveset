package net.yonchi.refm.world.capabilities.item;

import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Function;

public enum RapierWeaponCategories implements WeaponCategory, Function<Item, CapabilityItem.Builder> {
    RAPIER;

        final int id;

        RapierWeaponCategories() {
            this.id = WeaponCategory.ENUM_MANAGER.assign(this);
        }

    @Override
        public int universalOrdinal() {
            return this.id;
        }

    @Override
    public CapabilityItem.Builder apply(Item item) {
        return null;
    }

    public static class Builder {
        }
    }

//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/world/capabilities/item/CapabilityItem.java#L348