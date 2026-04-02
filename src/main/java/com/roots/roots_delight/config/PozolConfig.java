package com.roots.roots_delight.config;
import net.minecraftforge.common.ForgeConfigSpec;

public class PozolConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue POZOL_CACAO_CHANCE;
    public static final ForgeConfigSpec.DoubleValue POZOL_BLANCO_CHANCE;


    static {
        BUILDER.push("Pozol Cacao Settings");
        POZOL_CACAO_CHANCE = BUILDER
                .comment("Probability of receiving effects (0.0 to 1.0)")
                .defineInRange("effect_probability", 0.15, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.push("Pozol Blanco Settings");
        POZOL_BLANCO_CHANCE = BUILDER
                .comment("Probability of receiving effects (0.0 to 1.0)")
                .defineInRange("effect_probability", 0.20, 0.0, 1.0);
        BUILDER.pop();

        SPEC = BUILDER.build();

    }
}