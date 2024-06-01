package net.yonchi.refm.gameasset;

import java.util.Random;
import java.util.Set;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.damagesource.EpicFightDamageType;

public static StaticAnimation RAPIER_AIR_SLASH;
public static StaticAnimation RAPIER_AUTO1;
public static StaticAnimation RAPIER_AUTO2;
public static StaticAnimation RAPIER_AUTO3;
public static StaticAnimation RAPIER_DASH;
public static StaticAnimation BIPED_HOLD_RAPIER;
public static StaticAnimation BIPED_WALK_RAPIER;
public static StaticAnimation BIPED_RUN_RAPIER;
public static StaticAnimation DEADLYBACKFLIPSKILL;

public class RapierAnimations {
    RAPIER_AIR_SLASH = new AirSlashAnimation(0.1F, 0.15F, 0.26F, 0.5F, null, biped.toolR, "biped/combat/rapier_airslash", biped);

    RAPIER_AUTO1 = new BasicAttackAnimation(0.1F, 0.0F, 0.1F, 0.4F, null, biped.toolR, "biped/combat/rapier_auto1", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);
    RAPIER_AUTO2 = new BasicAttackAnimation(0.1F, 0.05F, 0.15F, 0.4F, null, biped.toolR, "biped/combat/rapier_auto2", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);
    RAPIER_AUTO3 = new BasicAttackAnimation(0.1F, 0.05F, 0.15F, 0.6F, null, biped.toolR, "biped/combat/rapier_auto3", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);
    RAPIER_DASH = new DashAttackAnimation(0.1F, 0.1F, 0.1F, 0.2F, 0.65F, null, biped.toolR, "biped/combat/rapier_dash", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);

    BIPED_HOLD_RAPIER = new StaticAnimation(true, "biped/living/hold_rapier", biped);
    BIPED_WALK_RAPIER = new MovementAnimation(true, "biped/living/walk_rapier", biped);
    BIPED_RUN_RAPIER = new MovementAnimation(true, "biped/living/run_rapier", biped);



    DEADLYBACKFLIPSKILL = new AttackAnimation(0.11F, "biped/skill/rapier_deadly_backflip", biped,
				new Phase(0.0F, 0.3F, 0.36F, 0.5F, 0.5F, biped.toolR, null), new Phase(0.5F, 0.5F, 0.56F, 0.75F, 0.75F, biped.toolR, null),
				new Phase(0.75F, 0.75F, 0.81F, 1.05F, Float.MAX_VALUE, biped.toolR, null))
            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
				.addProperty(StaticAnimationProperty.POSE_MODIFIER, Animations.ReusableSources.COMBO_ATTACK_DIRECTION_MODIFIER);
}