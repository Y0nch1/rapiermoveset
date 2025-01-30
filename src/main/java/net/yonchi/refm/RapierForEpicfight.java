package net.yonchi.refm;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;

import net.yonchi.refm.gameasset.RapierSkills;
import net.yonchi.refm.gameasset.RapierSounds;
import net.yonchi.refm.skill.guard.RapierGuard;
import net.yonchi.refm.skill.guard.RapierGuardWoM;
import net.yonchi.refm.skill.guard.AmethystGuard;
import net.yonchi.refm.skill.guard.AmethystGuardWoM;
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

import java.nio.file.Path;

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
        bus.addListener(this::addPackFindersEvent);
        bus.addListener(this::addCreative);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            proxy = new RapierAnimations.ClientProxy();
        } else {
            proxy = new RapierAnimations.ServerProxy();
        }

        if (ModList.get().isLoaded("irons_spellbooks")) {
            ICompatModule.loadCompatModule(AmethystGuard.class);
            bus.addListener(AmethystGuard::buildSkillEvent);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(AmethystGuard::regIcon));
        }
        if (ModList.get().isLoaded("wom")) {
            ICompatModule.loadCompatModule(RapierGuardWoM.class);
            bus.addListener(RapierGuardWoM::buildSkillEvent);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(RapierGuardWoM::regIcon));
        }
        if (ModList.get().isLoaded("wom")) {
            if (ModList.get().isLoaded("irons_spellbooks")) {
                ICompatModule.loadCompatModule(AmethystGuardWoM.class);
                bus.addListener(AmethystGuardWoM::buildSkillEvent);
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(AmethystGuardWoM::regIcon));
            }
        }
    }
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    public void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(RapierForEpicfight.MOD_ID).getFile().findResource("packs/all_rapiers_3D");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(RapierForEpicfight.MOD_ID).getFile().getFileName() + ":" + resourcePath, resourcePath, false);
            Pack.ResourcesSupplier resourcesSupplier = (string) -> pack;
            Pack.Info info = Pack.readPackInfo("all_rapiers_3D", resourcesSupplier);

            if (info != null) {
                event.addRepositorySource((source) ->
                        source.accept(Pack.create("all_rapiers_3D", Component.translatable("pack.all_rapiers_3D.title"), false, resourcesSupplier, info, PackType.CLIENT_RESOURCES, Pack.Position.TOP, false, PackSource.BUILT_IN)));
            }
        }
    }
}
