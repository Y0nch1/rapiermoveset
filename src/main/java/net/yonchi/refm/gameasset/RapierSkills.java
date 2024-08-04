package net.yonchi.refm.gameasset;

import java.util.Set;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.skill.weaponinnate.DeadlyBackflipSkill;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import static yesman.epicfight.api.data.reloader.SkillManager.SKILL_REGISTRY_KEY;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class RapierSkills {
    public static Skill DEADLYBACKFLIP;

    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent build) {
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(RapierForEpicfight.MOD_ID);

        WeaponInnateSkill deadlybackflip = modRegistry.build("deadlybackflip", DeadlyBackflipSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        deadlybackflip.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(2))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE))
                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(2))
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(24))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE))
                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get());
        DEADLYBACKFLIP = deadlybackflip;
    }

    public RapierSkills(){}
}

    //https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/gameasset/EpicFightSkills.java#L108
