package net.yonchi.refm.registry.entries;

import java.util.Set;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.skill.weaponinnate.*;
import net.yonchi.refm.skill.weaponpassive.EnderRapierPassive;
import net.yonchi.refm.skill.weaponpassive.OceanRapierPassive;
import net.yonchi.refm.skill.weaponpassive.WitherRapierPassive;

import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.registry.EpicFightRegistries;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

public final class RapierSkills {
    private RapierSkills() {}

    public static final DeferredRegister<Skill> REGISTRY = DeferredRegister.create(EpicFightRegistries.Keys.SKILL, RapierForEpicfight.MOD_ID);

    public static final DeferredHolder <Skill, DeadlyBackflipSkill> DEADLYBACKFLIP = REGISTRY.register("deadlybackflip", key ->
            WeaponInnateSkill.createWeaponInnateBuilder(DeadlyBackflipSkill::new).setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE))
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(12F))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(8))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER))
                    .build(key)
    );

    public static final DeferredHolder <Skill, DeadlyBackflipSkill_Ender> DEADLYBACKFLIP_ENDER = REGISTRY.register("deadlybackflip_ender", key ->
            WeaponInnateSkill.createWeaponInnateBuilder(DeadlyBackflipSkill_Ender::new).setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE))
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(4))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(-3))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.FINISHER))
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(1))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(32))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.BYPASS_DODGE, EpicFightDamageTypeTags.FINISHER))
                    .build(key)
    );


    public static final DeferredHolder <Skill, DeadlyBackflipSkill_Ocean> DEADLYBACKFLIP_OCEAN = REGISTRY.register("deadlybackflip_ocean", key ->
            WeaponInnateSkill.createWeaponInnateBuilder(DeadlyBackflipSkill_Ocean::new).setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE))
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(12))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(42))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER))
                    .build(key)
    );

    public static final DeferredHolder <Skill, DeadlyBackflipSkill_Wither> DEADLYBACKFLIP_WITHER = REGISTRY.register("deadlybackflip_wither", key ->
            WeaponInnateSkill.createWeaponInnateBuilder(DeadlyBackflipSkill_Wither::new).setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE))
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(-6))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.BYPASS_DODGE, EpicFightDamageTypeTags.FINISHER))
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(0))
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(36))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.BYPASS_DODGE, EpicFightDamageTypeTags.FINISHER))
                    .build(key)
    );

    public static final DeferredHolder <Skill, DeadlyBackflipSkill_Amethyst> DEADLYBACKFLIP_AMETHYST = REGISTRY.register("deadlybackflip_amethyst", key ->
            WeaponInnateSkill.createWeaponInnateBuilder(DeadlyBackflipSkill_Amethyst::new).setCategory(SkillCategories.WEAPON_INNATE)
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(6))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE))
            .newProperty()
                    .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                    .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.adder(12))
                    .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                    .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(32))
                    .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                    .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageTypeTags.WEAPON_INNATE, EpicFightDamageTypeTags.GUARD_PUNCTURE, EpicFightDamageTypeTags.FINISHER))
                    .build(key)
    );
/*
Builder fixed by M6FGR
*/
    public static final DeferredHolder <Skill, EnderRapierPassive> ENDER_PASSIVE = REGISTRY.register("ender_passive", key ->
            EnderRapierPassive.createBuilder(EnderRapierPassive::new).setCategory(SkillCategories.WEAPON_PASSIVE).setActivateType(Skill.ActivateType.TOGGLE).build(key)
    );
    public static final DeferredHolder <Skill, OceanRapierPassive> OCEAN_PASSIVE = REGISTRY.register("ocean_passive", key ->
            OceanRapierPassive.createBuilder(OceanRapierPassive::new).setCategory(SkillCategories.WEAPON_PASSIVE).setActivateType(Skill.ActivateType.DURATION_INFINITE).build(key)
    );
    public static final DeferredHolder <Skill, WitherRapierPassive> WITHER_PASSIVE = REGISTRY.register("wither_passive", key ->
            WitherRapierPassive.createBuilder(WitherRapierPassive::new).setCategory(SkillCategories.WEAPON_PASSIVE).setActivateType(Skill.ActivateType.ONE_SHOT).build(key)
    );
}
//https://github.com/Epic-Fight/epicfight/blob/1.21.1/src/main/java/yesman/epicfight/registry/entries/EpicFightSkills.java