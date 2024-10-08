package net.yonchi.refm.world.capabilities.item;


import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.gameasset.RapierColliderPreset;
import net.yonchi.refm.gameasset.RapierSkills;

import net.yonchi.refm.gameasset.RapierSounds;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(CapabilityItem.WeaponCategories.SWORD) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_HIT.get())
                .canBePlacedOffhand(false)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3, RapierAnimations.RAPIER_DASH, RapierAnimations.RAPIER_AIR_SLASH)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP)
                .livingMotionModifier(Styles.OCHS, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> ENDER_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(CapabilityItem.WeaponCategories.SWORD) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_HIT.get())
                .canBePlacedOffhand(false)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3, RapierAnimations.RAPIER_DASH_ENDER, RapierAnimations.RAPIER_AIR_SLASH_ENDER)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_ENDER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };

    public WeaponCapabilityPresets() {
    }

    private static boolean CheckPlayer(LivingEntityPatch<?> playerPatch) {
        return playerPatch.getOriginal().getType() != EntityType.PLAYER;
    }

    private static final Map<String, Function<Item, CapabilityItem.Builder>> PRESETS = Maps.newHashMap();

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"rapier"), RAPIER);
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"ender_rapier"), ENDER_RAPIER);
    }
}


//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/world/capabilities/item/WeaponCapabilityPresets.java