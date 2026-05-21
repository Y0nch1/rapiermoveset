package net.yonchi.refm.registry.entries;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierColliderPreset;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import yesman.epicfight.api.ex_cap.provider.ProviderConditional;
import yesman.epicfight.registry.deferred.ItemPresetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredWeapon;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

public final class RapierModItemPresets
{
    private RapierModItemPresets() {}
    public static final ItemPresetRegister REGISTRY = ItemPresetRegister.create(RapierForEpicfight.MOD_ID);
    public static final DeferredWeapon RAPIER = REGISTRY.registerWeapon("rapier", () -> WeaponCapability.builder()
            .category(RapierWeaponCategories.RAPIER) // Updated to use custom category
            .addConditionals(ProviderConditional.createDefault(CapabilityItem.Styles.OCHS, false))
            .collider(RapierColliderPreset.RAPIER)
            .swingSound(RapierSounds.RAPIER_STAB)
            .hitSound(RapierSounds.RAPIER_HIT)
            .canBePlacedOffhand(true)
            .addMoveset(CapabilityItem.Styles.OCHS, RapierModMovesets.RAPIER)
    );

    public static final DeferredWeapon ENDER_RAPIER = REGISTRY.registerWeapon("ender_rapier", () -> WeaponCapability.builder()
            .parent(RAPIER)
            .swingSound(RapierSounds.RAPIER_STAB)
            .hitSound(RapierSounds.RAPIER_HIT)
            .addMoveset(CapabilityItem.Styles.OCHS, RapierModMovesets.ENDER_RAPIER));

    public static final DeferredWeapon OCEAN_RAPIER = REGISTRY.registerWeapon("ocean_rapier", () -> WeaponCapability.builder()
            .parent(RAPIER)
            .swingSound(RapierSounds.RAPIER_STAB)
            .hitSound(RapierSounds.RAPIER_HIT)
            .addMoveset(CapabilityItem.Styles.OCHS, RapierModMovesets.OCEAN_RAPIER));

    public static final DeferredWeapon WITHER_RAPIER = REGISTRY.registerWeapon("wither_rapier", () -> WeaponCapability.builder()
            .parent(RAPIER)
            .swingSound(RapierSounds.RAPIER_STAB)
            .hitSound(RapierSounds.RAPIER_HIT)
            .addMoveset(CapabilityItem.Styles.OCHS, RapierModMovesets.WITHER_RAPIER));

    public static final DeferredWeapon AMETHYST_RAPIER = REGISTRY.registerWeapon("amethyst_rapier", () -> WeaponCapability.builder()
            .parent(RAPIER)
            .swingSound(RapierSounds.RAPIER_STAB)
            .hitSound(RapierSounds.RAPIER_HIT)
            .addMoveset(CapabilityItem.Styles.OCHS, RapierModMovesets.AMETHYST_RAPIER));
}
