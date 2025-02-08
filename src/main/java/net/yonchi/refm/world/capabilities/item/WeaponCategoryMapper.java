package net.yonchi.refm.world.capabilities.item;

import net.minecraft.world.item.Item;

import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class WeaponCategoryMapper {
    private static final Map<RapierWeaponCategories, WeaponCategory> categoryMap = new HashMap<>();

    static {
        categoryMap.put(RapierWeaponCategories.RAPIER, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(RapierWeaponCategories.ENDER_RAPIER, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(RapierWeaponCategories.OCEAN_RAPIER, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(RapierWeaponCategories.WITHER_RAPIER, CapabilityItem.WeaponCategories.SWORD);
        categoryMap.put(RapierWeaponCategories.AMETHYST_RAPIER, CapabilityItem.WeaponCategories.SWORD);
    }

    public static CapabilityItem.Builder apply(Item item, RapierWeaponCategories category) {
        WeaponCategory mappedCategory = categoryMap.getOrDefault(category, category);
        try {
            Method applyMethod = mappedCategory.getClass().getMethod("apply", Item.class);
            return (CapabilityItem.Builder) applyMethod.invoke(mappedCategory, item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
