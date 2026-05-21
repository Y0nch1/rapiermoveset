package net.yonchi.refm.registry;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.yonchi.refm.registry.entries.*;

import java.util.List;

public class RapierModRegistries {

    public static List<DeferredRegister<?>> REGISTRIES = List.of(
            RapierModItemPresets.REGISTRY,
            RapierModMovesets.REGISTRY,
            RapierAddonItems.REGISTRY,
            RapierModCreativeTabs.REGISTRY,
            RapierSkills.REGISTRY,
            RapierSounds.REGISTRY
    );
}
