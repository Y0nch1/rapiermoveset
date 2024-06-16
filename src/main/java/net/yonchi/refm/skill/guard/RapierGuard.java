package net.yonchi.refm.skill.guard;

import java.util.UUID;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.guard.GuardSkill;

import static net.yonchi.refm.world.capabilities.item.WeaponCapabilityPresets.RAPIER;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)

public class RapierGuard extends Skill {
    public static final UUID EVENT_UUID = UUID.fromString("b422f7a0-f378-11eb-9a03-0242ac703459");
    public RapierGuard(Builder<? extends Skill> builder) {
        super(builder);
    }

    public static Builder createGuardBuilder() {
        return (new GuardSkill.Builder()).setCategory(SkillCategories.GUARD).setActivateType(ActivateType.ONE_SHOT).setResource(Resource.STAMINA)
        .addGuardMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
            return RapierAnimations.RAPIER_GUARD_HIT;
        })
        .addGuardBreakMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
            return Animations.BIPED_COMMON_NEUTRALIZED;
        });
    }
    public static void registerGuard(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"rapier"), RAPIER);
    }
}
