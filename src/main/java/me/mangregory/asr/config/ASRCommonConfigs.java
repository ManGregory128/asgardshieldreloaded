package me.mangregory.asr.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ASRCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> WOODEN_GIANT_SWORD_DURABILITY;
    public static final ForgeConfigSpec.ConfigValue<Double> WOODEN_GIANT_SWORD_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> WOODEN_GIANT_SWORD_MAXUSEDURATION;

    public static final ForgeConfigSpec.ConfigValue<Integer> STONE_GIANT_SWORD_DURABILITY;
    public static final ForgeConfigSpec.ConfigValue<Double> STONE_GIANT_SWORD_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> STONE_GIANT_SWORD_MAXUSEDURATION;

    public static final ForgeConfigSpec.ConfigValue<Integer> IRON_GIANT_SWORD_DURABILITY;
    public static final ForgeConfigSpec.ConfigValue<Double> IRON_GIANT_SWORD_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> IRON_GIANT_SWORD_MAXUSEDURATION;

    public static final ForgeConfigSpec.ConfigValue<Integer> GOLDEN_GIANT_SWORD_DURABILITY;
    public static final ForgeConfigSpec.ConfigValue<Double> GOLDEN_GIANT_SWORD_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> GOLDEN_GIANT_SWORD_MAXUSEDURATION;

    public static final ForgeConfigSpec.ConfigValue<Integer> DIAMOND_GIANT_SWORD_DURABILITY;
    public static final ForgeConfigSpec.ConfigValue<Double> DIAMOND_GIANT_SWORD_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> DIAMOND_GIANT_SWORD_MAXUSEDURATION;

    public static final ForgeConfigSpec.ConfigValue<Integer> ENDER_GIANT_SWORD_DURABILITY;
    public static final ForgeConfigSpec.ConfigValue<Double> ENDER_GIANT_SWORD_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ENDER_GIANT_SWORD_MAXUSEDURATION;
    static {
        BUILDER.push("Configuration file for Asgard Shield: Reloaded");

        WOODEN_GIANT_SWORD_DURABILITY = BUILDER.comment("Wooden Giant Sword: Durability")
                .define("Wooden Giant Sword Durability", 88);
        WOODEN_GIANT_SWORD_DAMAGE = BUILDER.comment("Wooden Giant Sword: Damage")
                .define("Wooden Giant Sword Damage", 5.0D);
        WOODEN_GIANT_SWORD_MAXUSEDURATION = BUILDER.comment("Wooden Giant Sword: Max Use Duration")
                .define("Wooden Giant Sword Max Use Duration", 100);

        STONE_GIANT_SWORD_DURABILITY = BUILDER.comment("Stone Giant Sword: Durability")
                .define("Stone Giant Sword Durability", 196);
        STONE_GIANT_SWORD_DAMAGE = BUILDER.comment("Stone Giant Sword: Damage")
                .define("Stone Giant Sword Damage", 6.0D);
        STONE_GIANT_SWORD_MAXUSEDURATION = BUILDER.comment("Stone Giant Sword: Max Use Duration")
                .define("Stone Giant Sword Max Use Duration", 100);

        IRON_GIANT_SWORD_DURABILITY = BUILDER.comment("Iron Giant Sword: Durability")
                .define("Iron Giant Sword Durability", 375);
        IRON_GIANT_SWORD_DAMAGE = BUILDER.comment("Iron Giant Sword: Damage")
                .define("Iron Giant Sword Damage", 7.0D);
        IRON_GIANT_SWORD_MAXUSEDURATION = BUILDER.comment("Iron Giant Sword: Max Use Duration")
                .define("Iron Giant Sword Max Use Duration", 100);

        GOLDEN_GIANT_SWORD_DURABILITY = BUILDER.comment("Golden Giant Sword: Durability")
                .define("Golden Giant Sword Durability", 48);
        GOLDEN_GIANT_SWORD_DAMAGE = BUILDER.comment("Golden Giant Sword: Damage")
                .define("Golden Giant Sword Damage", 5.0D);
        GOLDEN_GIANT_SWORD_MAXUSEDURATION = BUILDER.comment("Golden Giant Sword: Max Use Duration")
                .define("Golden Giant Sword Max Use Duration", 100);

        DIAMOND_GIANT_SWORD_DURABILITY = BUILDER.comment("Diamond Giant Sword: Durability")
                .define("Diamond Giant Sword Durability", 2341);
        DIAMOND_GIANT_SWORD_DAMAGE = BUILDER.comment("Diamond Giant Sword: Damage")
                .define("Diamond Giant Sword Damage", 8.0D);
        DIAMOND_GIANT_SWORD_MAXUSEDURATION = BUILDER.comment("Diamond Giant Sword: Max Use Duration")
                .define("Diamond Giant Sword Max Use Duration", 100);

        ENDER_GIANT_SWORD_DURABILITY = BUILDER.comment("Ender Giant Sword: Durability")
                .define("Ender Giant Sword Durability", 1829);
        ENDER_GIANT_SWORD_DAMAGE = BUILDER.comment("Ender Giant Sword: Damage")
                .define("Ender Giant Sword Damage", 8.0D);
        ENDER_GIANT_SWORD_MAXUSEDURATION = BUILDER.comment("Ender Giant Sword: Max Use Duration")
                .define("Ender Giant Sword Max Use Duration", 100);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
