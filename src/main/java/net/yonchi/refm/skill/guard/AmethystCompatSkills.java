package net.yonchi.refm.skill.guard;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.world.item.RapierAddonItems;

import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent.ModRegistryWorker.SkillCreateEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

import java.util.List;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AmethystCompatSkills implements ICompatModule {
    public static void forceGuard(SkillBuildEvent bus) {
    }
    @SubscribeEvent
    public static void onGuardSkillCreate(SkillCreateEvent<GuardSkill.Builder> event) {
        System.out.println("[AmethystCompatSkills] Skill being builded: " + event.getRegistryName());
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "guard"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            });
            System.out.println("[AmethystCompatSkills] Amethyst Guard animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onParrySkillCreate(SkillCreateEvent<ParryingSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addAdvancedGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_PARRY, RapierAnimations.RAPIER_GUARD_PARRY_AMETHYST);
            });
            System.out.println("[AmethystCompatSkills] Amethyst Parry animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onScapeSkillCreate(SkillCreateEvent<EmergencyEscapeSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","emergency_escape"))) {
            EmergencyEscapeSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
            System.out.println("[AmethystCompatSkills] Emergency escape has been implemented");
        }
    }

    @SubscribeEvent
    public static void onSwordSkillCreate(SkillCreateEvent<SwordmasterSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
            SwordmasterSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
            System.out.println("[AmethystCompatSkills] SwordMaster has been implemented");
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){
        icon.registerCategory(RapierWeaponCategories.AMETHYST_RAPIER, new ItemStack(RapierAddonItems.AMETHYST_RAPIER.get()));
        System.out.println("[AmethystCompatSkills] Amethyst Skill icons has been implemented");
    }

    @Override
    public void onModEventBus(IEventBus iEventBus) {
    }
    @Override
    public void onForgeEventBus(IEventBus iEventBus) {
    }
    @Override
    public void onModEventBusClient(IEventBus iEventBus) {
    }
    @Override
    public void onForgeEventBusClient(IEventBus iEventBus) {
    }
}
