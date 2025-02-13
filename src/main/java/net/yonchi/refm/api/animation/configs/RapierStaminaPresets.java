package net.yonchi.refm.api.animation.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RapierStaminaPresets {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.DoubleValue RAPIER_STAMINA_COST = BUILDER
            .comment("Stamina cost for using a rapier (Default: 1.8)")
            .defineInRange("rapierStaminaCost", 1.8D, 0.1D, 10.0D);
}
