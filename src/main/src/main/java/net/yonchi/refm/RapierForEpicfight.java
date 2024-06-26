package net.yonchi.refm;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yonchi.refm.skill.RapierSkillDataKeys;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.gameasset.RapierSkills;
import net.yonchi.refm.world.item.RapierAddonItems;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RapierForEpicfight.MOD_ID)
public class RapierForEpicfight
{
    public static final String MOD_ID = "refm";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RapierForEpicfight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        WeaponCategory.ENUM_MANAGER.registerEnumCls(MOD_ID, RapierWeaponCategories.class);
        RapierSkills.registerSkills();

        RapierAddonItems.ITEMS.register(bus);

        RapierSkillDataKeys.DATA_KEYS.register(bus);

        bus.addListener(RapierAnimations::registerAnimations);
        bus.addListener(this::addCreative);

    }


    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(Event event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(RapierAddonItems.IRON_RAPIER);
            event.accept(RapierAddonItems.GOLD_RAPIER);
            event.accept(RapierAddonItems.DIAMOND_RAPIER);
            event.accept(RapierAddonItems.NETHERITE_RAPIER);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
