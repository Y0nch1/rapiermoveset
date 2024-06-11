package net.yonchi.refm.gameasset;

import java.util.Set;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.skill.weaponinnate.DeadlyBackflipSkill;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.data.reloader.SkillManager;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class RapierSkills {

    public static Skill DEADLYBACKFLIP;

    public static void registerSkills(){
        SkillManager.register(DeadlyBackflipSkill::new, WeaponInnateSkill.createWeaponInnateBuilder().setActivateType(Skill.ActivateType.DURATION_INFINITE), "refm", "deadlybackflip");
    }

    public static void buildSkillEvent(SkillBuildEvent onBuild) {
        //DeadlyBackflip Skill Properties
        WeaponInnateSkill deadlybackflip = onBuild.build(RapierForEpicfight.MOD_ID, "deadlybackflip");
        deadlybackflip.newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(2.0F))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD)

                .newProperty()
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(2))
                .addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(), ExtraDamageInstance.TARGET_LOST_HEALTH.create(0.5F)))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE))
                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(30.0F))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.FALL)
                .registerPropertiesToAnimation();
        DEADLYBACKFLIP = deadlybackflip;
    }
}

    //https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/gameasset/EpicFightSkills.java#L108
