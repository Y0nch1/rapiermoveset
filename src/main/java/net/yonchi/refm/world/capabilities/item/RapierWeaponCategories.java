package net.yonchi.refm.world.capabilities.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.item.Item;

import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.List;
import java.util.function.Function;

public enum RapierWeaponCategories implements WeaponCategory {
    RAPIER(CapabilityItem.WeaponCategories.LONGSWORD),
    ENDER_RAPIER(RAPIER),
    WITHER_RAPIER(RAPIER),
    OCEAN_RAPIER(RAPIER),
    AMETHYST_RAPIER(RAPIER),
    ;

    final List<WeaponCategory> parents;
    final int id;

    RapierWeaponCategories() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
        this.parents = ImmutableList.of();
    }

    RapierWeaponCategories(WeaponCategory... parents) {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
        this.parents = ImmutableList.copyOf(parents);
    }

    @Override
    public List<WeaponCategory> getParents() {
        return WeaponCategory.super.getParents();
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }
}