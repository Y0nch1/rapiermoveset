package net.yonchi.refm;

import net.minecraft.resources.ResourceLocation;
import net.yonchi.refm.registry.RapierModRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.yonchi.refm.registry.entries.RapierSkills;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.registry.entries.RapierSounds;
import net.yonchi.refm.skill.guard.*;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.registry.entries.RapierAddonItems;

import yesman.epicfight.api.client.event.EpicFightClientEventHooks;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.main.EpicFightSharedConstants;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.nio.file.Path;
import java.util.Optional;

@Mod(RapierForEpicfight.MOD_ID)
public class RapierForEpicfight {
    public static final String MOD_ID = "refm";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static ResourceLocation identifier(String name)
    {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public RapierForEpicfight(IEventBus bus) {
        WeaponCategory.ENUM_MANAGER.registerEnumCls(MOD_ID, RapierWeaponCategories.class);

        RapierModRegistries.REGISTRIES.forEach(value -> value.register(bus));

        bus.addListener(RapierAnimations::registerAnimations);
        bus.addListener(this::addPackFindersEvent);
        bus.addListener(this::addCreative);

        //EVENT HOOKS
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onEmergencyEscapeSkillCreation, 1);
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onSwordMasterSkillCreation, 1);
        if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("efn")) {
            EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onEFNParrySkillCreation, 2);
        }
        EpicFightClientEventHooks.Registry.WEAPON_CATEGORY_ICON.registerEvent(RapierCompatSkills::onWeaponCategoryIconCreation, 1);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    public void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(RapierForEpicfight.MOD_ID).getFile().findResource("packs/all_rapiers_3D");

            PackLocationInfo packLocation = new PackLocationInfo("all_rapiers_3D", Component.translatable("pack.all_rapiers_3D.title"), PackSource.BUILT_IN, Optional.empty());
            addPack(event, resourcePath, packLocation);

            Path forixaimRapiersPath = ModList.get().getModFileById(RapierForEpicfight.MOD_ID).getFile().findResource("packs/forixaim_rapiers");

            PackLocationInfo forixaimRapiers = new PackLocationInfo("forixaim_rapiers", Component.translatable("pack.forixaim_rapiers.title"), PackSource.BUILT_IN, Optional.empty());
            addPack(event, forixaimRapiersPath, forixaimRapiers);
        }
    }

    private void addPack(AddPackFindersEvent event, Path forixaimRapiersPath, PackLocationInfo forixaimRapiers) {
        Pack.ResourcesSupplier forixaimRapiersSupplier = new PathPackResources.PathResourcesSupplier(forixaimRapiersPath);
        Pack forixaimRapiersPack = Pack.readMetaAndCreate(forixaimRapiers, forixaimRapiersSupplier, PackType.CLIENT_RESOURCES, new PackSelectionConfig(false, Pack.Position.TOP, false));

        if (forixaimRapiersPack != null) {
            event.addRepositorySource(source -> source.accept(forixaimRapiersPack));
        }
    }
}
