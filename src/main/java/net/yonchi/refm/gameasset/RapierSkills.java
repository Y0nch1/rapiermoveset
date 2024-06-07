package net.yonchi.refm.gameasset;

import java.util.Set;

import net.yonchi.refm.RapierForEpicfight;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.weaponinnate.*;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;


import static net.yonchi.refm.world.capabilities.item.RapierWeaponCapabilityPresets.RapierAnimations;


public class RapierSkills {
    public static WeaponInnateSkill DEADLYBACKFLIP;

    public static void buildSkillEvent(SkillBuildEvent build) {
        ModRegistryWorker modRegistry = build.createRegistryWorker(RapierForEpicfight.MOD_ID);

        WeaponInnateSkill deadlybackflip = modRegistry.build("deadlybackflip", SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(() -> (AttackAnimation)RapierAnimations.DEADLYBACKFLIP));
        deadlybackflip.newProperty()
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(10.0F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE));
        DEADLYBACKFLIP = deadlybackflip;

    }

    private static class ModRegistryWorker {
    }

}

//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/gameasset/EpicFightSkills.java#L108
