package net.yonchi.refm.gameasset;

import java.util.Set;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.skill.weaponinnate.*;
import net.yonchi.refm.skill.weaponpassive.OceanRapierPassive;
import net.yonchi.refm.skill.weaponpassive.WitherRapierPassive;

import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class RapierSkills {
    public static Skill DEADLYBACKFLIP;
    public static Skill DEADLYBACKFLIP_ENDER;
    public static Skill DEADLYBACKFLIP_OCEAN;
    public static Skill DEADLYBACKFLIP_WITHER;
    public static Skill DEADLYBACKFLIP_AMETHYST;
    public static Skill WITHER_PASSIVE;
    public static Skill OCEAN_PASSIVE;

    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent build) {
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(RapierForEpicfight.MOD_ID);

        WeaponInnateSkill deadlybackflip = modRegistry.build("deadlybackflip", DeadlyBackflipSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        deadlybackflip
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(8))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(12))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(8))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE, EpicFightDamageType.FINISHER));
        DEADLYBACKFLIP = deadlybackflip;

        WeaponInnateSkill deadlybackflip_ender = modRegistry.build("deadlybackflip_ender", DeadlyBackflip_EnderSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        deadlybackflip_ender
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(8))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(4))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(-3))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.BYPASS_DODGE, EpicFightDamageType.FINISHER))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(0))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(32))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.BYPASS_DODGE, EpicFightDamageType.FINISHER));
        DEADLYBACKFLIP_ENDER = deadlybackflip_ender;

        WeaponInnateSkill deadlybackflip_ocean = modRegistry.build("deadlybackflip_ocean", DeadlyBackflipSkill_OceanSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        deadlybackflip_ocean
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(8))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(11))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.FALL)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(42))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE, EpicFightDamageType.FINISHER));
        DEADLYBACKFLIP_OCEAN = deadlybackflip_ocean;

        WeaponInnateSkill deadlybackflip_wither = modRegistry.build("deadlybackflip_wither", DeadlyBackflipSkill_WitherSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        deadlybackflip_wither
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(8))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(-6))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.BYPASS_DODGE, EpicFightDamageType.FINISHER))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(-1))
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(36))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.BYPASS_DODGE, EpicFightDamageType.FINISHER));
        DEADLYBACKFLIP_WITHER = deadlybackflip_wither;

        WeaponInnateSkill deadlybackflip_amethyst = modRegistry.build("deadlybackflip_amethyst", DeadlyBackflipSkill_AmethystSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        deadlybackflip_amethyst
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(8))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(12))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(32))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE, EpicFightDamageType.FINISHER));
        DEADLYBACKFLIP_AMETHYST = deadlybackflip_amethyst;

        WITHER_PASSIVE = modRegistry.build("wither_passive", WitherRapierPassive::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE).setActivateType(Skill.ActivateType.ONE_SHOT));
        OCEAN_PASSIVE = modRegistry.build("ocean_passive", OceanRapierPassive::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE).setActivateType(Skill.ActivateType.DURATION_INFINITE));
    }

    public RapierSkills(){
    }
    public static void registerRapierSkills(RegisterEvent bus) {
    }
}

    //https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/gameasset/EpicFightSkills.java#L108
