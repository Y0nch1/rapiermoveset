import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation.Phase;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.StaticAnimationProperty;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.gameasset.Animations;

public static void setBiped(Armature biped) {
    RapierAnimations.biped = biped;
}

public static class RapierAnimations {
    public static StaticAnimation RAPIER_AIR_SLASH = new AirSlashAnimation(0.1F, 0.15F, 0.26F, 0.5F, null, biped.toolR, "biped/combat/rapier_airslash", biped);

    public static StaticAnimation RAPIER_AUTO1 = new BasicAttackAnimation(0.1F, 0.0F, 0.1F, 0.4F, null, biped.toolR, "biped/combat/rapier_auto1", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);
    public static StaticAnimation RAPIER_AUTO2 = new BasicAttackAnimation(0.1F, 0.05F, 0.15F, 0.4F, null, biped.toolR, "biped/combat/rapier_auto2", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);
    public static StaticAnimation RAPIER_AUTO3 = new BasicAttackAnimation(0.1F, 0.05F, 0.15F, 0.6F, null, biped.toolR, "biped/combat/rapier_auto3", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);
    public static StaticAnimation RAPIER_DASH = new DashAttackAnimation(0.1F, 0.1F, 0.1F, 0.2F, 0.65F, null, biped.toolR, "biped/combat/rapier_dash", biped)
				.addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F);

    public static StaticAnimation BIPED_HOLD_RAPIER = new StaticAnimation(true, "biped/living/hold_rapier", biped);
    public static StaticAnimation BIPED_WALK_RAPIER = new MovementAnimation(true, "biped/living/walk_rapier", biped);
    public static StaticAnimation BIPED_RUN_RAPIER = new MovementAnimation(true, "biped/living/run_rapier", biped);


    public static StaticAnimation DEADLYBACKFLIP = new AttackAnimation(0.11F, "biped/skill/rapier_deadly_backflip", biped,
				new Phase(0.0F, 0.3F, 0.36F, 0.5F, 0.5F, biped.toolR, null), new Phase(0.5F, 0.5F, 0.56F, 0.75F, 0.75F, biped.toolR, null),
				new Phase(0.75F, 0.75F, 0.81F, 1.05F, Float.MAX_VALUE, biped.toolR, null))
            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
				.addProperty(StaticAnimationProperty.POSE_MODIFIER, Animations.ReusableSources.COMBO_ATTACK_DIRECTION_MODIFIER);
    protected static Armature biped;
}

public void main() {
}
