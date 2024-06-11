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

import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.ONE_HAND)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(EpicFightSounds.WHOOSH_SMALL.get())
                .hitSound(EpicFightSounds.BLADE_HIT.get())
                .canBePlacedOffhand(false)
                .newStyleCombo(Styles.ONE_HAND, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3, RapierAnimations.RAPIER_DASH, RapierAnimations.RAPIER_AIR_SLASH)
                .innateSkill(Styles.ONE_HAND, (itemstack) -> RapierSkills.DEADLYBACKFLIP)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.KNEEL, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SNEAK, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };

    private static boolean CheckPlayer(LivingEntityPatch<?> playerPatch) {
        return playerPatch.getOriginal().getType() != EntityType.PLAYER;
    }

    private static final Map<String, Function<Item, CapabilityItem.Builder>> PRESETS = Maps.newHashMap();

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"rapier"), RAPIER);
    }
}


//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/world/capabilities/item/WeaponCapabilityPresets.java