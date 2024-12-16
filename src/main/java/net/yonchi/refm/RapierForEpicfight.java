package net.yonchi.refm;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;

import net.yonchi.refm.gameasset.RapierSkills;
import net.yonchi.refm.gameasset.RapierSounds;
import net.yonchi.refm.skill.guard.RapierGuard;
import net.yonchi.refm.skill.guard.RapierGuardWoM;
import net.yonchi.refm.world.item.AmethystRapier;
import net.yonchi.refm.world.item.RapierTab;
import net.yonchi.refm.skill.RapierSkillDataKeys;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.item.RapierAddonItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RapierForEpicfight.MOD_ID)
public class RapierForEpicfight {
    public static RapierAnimations.IProxy proxy;
    public static final String MOD_ID = "refm";

    public RapierForEpicfight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        WeaponCategory.ENUM_MANAGER.registerEnumCls(MOD_ID, RapierWeaponCategories.class);
        RapierTab.register(bus);

        RapierAddonItems.ITEMS.register(bus);
        RapierSounds.SOUNDS.register(bus);

        RapierSkillDataKeys.DATA_KEYS.register(bus);
        bus.addListener(RapierAnimations::registerAnimations);
        bus.addListener(RapierSkills::registerRapierSkills);
        bus.addListener(RapierGuard::buildSkillEvent);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(RapierGuard::regIcon));
        bus.addListener(this::addCreative);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            proxy = new RapierAnimations.ClientProxy();
        } else {
            proxy = new RapierAnimations.ServerProxy();
        }

        if (ModList.get().isLoaded("irons_spellbooks")) {
            ICompatModule.loadCompatModule(AmethystRapier.class);
            bus.addListener(AmethystRapier::buildSkillEvent);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(AmethystRapier::regIcon));
        }
        if (ModList.get().isLoaded("wom")) {
            ICompatModule.loadCompatModule(RapierGuardWoM.class);bus.addListener(RapierGuardWoM::buildSkillEvent);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(RapierGuardWoM::regIcon));
        }
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }
}
