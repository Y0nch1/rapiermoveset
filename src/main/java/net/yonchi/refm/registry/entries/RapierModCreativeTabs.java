package net.yonchi.refm.registry.entries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.yonchi.refm.RapierForEpicfight;

import java.util.function.Supplier;

public final class RapierModCreativeTabs {

    private RapierModCreativeTabs() {}
    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RapierForEpicfight.MOD_ID);

    public static final Supplier<CreativeModeTab> RAPIERS_TAB = REGISTRY.register("rapiers_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(RapierAddonItems.IRON_RAPIER.get()))
                    .title(Component.translatable("creativetab.rapiers_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(RapierAddonItems.IRON_RAPIER.get());
                        output.accept(RapierAddonItems.GOLDEN_RAPIER.get());
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
        REGISTRY.register(eventBus);
    }
}