package net.yonchi.refm.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import net.yonchi.refm.RapierForEpicfight;

public class RapierTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RapierForEpicfight.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RAPIERS_TAB = CREATIVE_MODE_TABS.register("rapiers_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(RapierAddonItems.IRON_RAPIER.get()))
                    .title(Component.translatable("creativetab.rapiers_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(RapierAddonItems.IRON_RAPIER.get());
                        output.accept(RapierAddonItems.GOLD_RAPIER.get());
                        output.accept(RapierAddonItems.DIAMOND_RAPIER.get());
                        output.accept(RapierAddonItems.NETHERITE_RAPIER.get());
                        output.accept(RapierAddonItems.ENDERITE_RAPIER.get());
                        output.accept(RapierAddonItems.WITHERITE_RAPIER.get());
                        output.accept(RapierAddonItems.OCEANITE_RAPIER.get());
                        output.accept(RapierAddonItems.END_UPGRADE.get());
                        output.accept(RapierAddonItems.WITHER_UPGRADE.get());
                        output.accept(RapierAddonItems.OCEAN_UPGRADE.get());
                    })
                    .build()
    );

    public static void register (IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}