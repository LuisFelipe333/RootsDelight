package com.roots.roots_delight.config;
import net.minecraftforge.common.ForgeConfigSpec;


public class RootsDelightConfig {

    public static final ForgeConfigSpec COMMON_SPEC;

    public static ForgeConfigSpec.DoubleValue PB_JUMP;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("pozol_blanco");

        PB_JUMP = builder.defineInRange("jump", 0.20, 0.0, 1.0);

        builder.pop();

        COMMON_SPEC = builder.build();
    }
}
