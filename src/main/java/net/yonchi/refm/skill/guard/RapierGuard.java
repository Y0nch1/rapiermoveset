package net.yonchi.refm.skill.guard;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;

import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID , bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)

public class RapierGuard extends GuardSkill {

    public RapierGuard(Builder builder) {
        super(builder);
    }

    public static GuardSkill.Builder createGuardBuilder() {
        return (new GuardSkill.Builder())
                .addGuardMotion(RapierWeaponCategories.RAPIER, (item, player) -> RapierAnimations.RAPIER_GUARD_HIT)
                .addGuardBreakMotion(RapierWeaponCategories.RAPIER, (item, player) -> Animations.BIPED_COMMON_NEUTRALIZED);
    }

    public static GuardSkill.Builder createActiveGuardBuilder() {
        return GuardSkill.createGuardBuilder()
                .addAdvancedGuardMotion(RapierWeaponCategories.RAPIER, (itemCap, playerpatch) ->
                        new StaticAnimation[] { RapierAnimations.RAPIER_GUARD_ACTIVE_HIT1, RapierAnimations.RAPIER_GUARD_ACTIVE_HIT2 });
    }
}
