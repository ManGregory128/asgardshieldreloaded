package com.mangregory.asgardshieldreloaded.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;

@Config(modid = AsgardShieldReloaded.MOD_ID, name = "AsgardShieldReloaded")
public class ASRConfig
{
    @Config.Name("Wooden Giant Sword: Durability")
    public static int WOODEN_GIANT_SWORD_DURABILITY = 88;

    @Config.Name("Wooden Giant Sword: Damage")
    public static double WOODEN_GIANT_SWORD_DAMAGE = 6.0D;

    @Config.Name("Wooden Giant Sword: Cooldown")
    public static int WOODEN_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Wooden Giant Sword: Max Use Duration")
    public static int WOODEN_GIANT_SWORD_MAXUSEDURATION = 100;

    @Config.Name("Stone Giant Sword: Durability")
    public static int STONE_GIANT_SWORD_DURABILITY = 196;

    @Config.Name("Stone Giant Sword: Damage")
    public static double STONE_GIANT_SWORD_DAMAGE = 7.0D;

    @Config.Name("Stone Giant Sword: Cooldown")
    public static int STONE_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Stone Giant Sword: Max Use Duration")
    public static int STONE_GIANT_SWORD_MAXUSEDURATION = 100;

    @Config.Name("Iron Giant Sword: Durability")
    public static int IRON_GIANT_SWORD_DURABILITY = 375;

    @Config.Name("Iron Giant Sword: Damage")
    public static double IRON_GIANT_SWORD_DAMAGE = 8.0D;

    @Config.Name("Iron Giant Sword: Cooldown")
    public static int IRON_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Iron Giant Sword: Max Use Duration")
    public static int IRON_GIANT_SWORD_MAXUSEDURATION = 100;

    @Config.Name("Gold Giant Sword: Durability")
    public static int GOLD_GIANT_SWORD_DURABILITY = 48;

    @Config.Name("Gold Giant Sword: Damage")
    public static double GOLD_GIANT_SWORD_DAMAGE = 6.0D;

    @Config.Name("Gold Giant Sword: Cooldown")
    public static int GOLD_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Gold Giant Sword: Max Use Duration")
    public static int GOLD_GIANT_SWORD_MAXUSEDURATION = 100;

    @Config.Name("Diamond Giant Sword: Durability")
    public static int DIAMOND_GIANT_SWORD_DURABILITY = 2341;

    @Config.Name("Diamond Giant Sword: Damage")
    public static double DIAMOND_GIANT_SWORD_DAMAGE = 9.0D;

    @Config.Name("Diamond Giant Sword: Cooldown")
    public static int DIAMOND_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Diamond Giant Sword: Max Use Duration")
    public static int DIAMOND_GIANT_SWORD_MAXUSEDURATION = 100;

    @Config.Name("Nether Quartz Giant Sword: Durability")
    public static int NETHERQUARTZ_GIANT_SWORD_DURABILITY = 1646;

    @Config.Name("Nether Quartz Giant Sword: Damage")
    public static double NETHERQUARTZ_GIANT_SWORD_DAMAGE = 8.0D;

    @Config.Name("Nether Quartz Giant Sword: Cooldown")
    public static int NETHERQUARTZ_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Nether Quartz Giant Sword: Max Use Duration")
    public static int NETHERQUARTZ_GIANT_SWORD_MAXUSEDURATION = 100;

    @Config.Name("Skull Giant Sword: Durability")
    public static int SKULL_GIANT_SWORD_DURABILITY = 260;

    @Config.Name("Skull Giant Sword: Damage")
    public static double SKULL_GIANT_SWORD_DAMAGE = 7.0D;

    @Config.Name("Skull Giant Sword: Cooldown")
    public static int SKULL_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Skull Giant Sword: Max Use Duration")
    public static int SKULL_GIANT_SWORD_MAXUSEDURATION = 100;

    @Config.Name("Ender Giant Sword: Durability")
    public static int ENDER_GIANT_SWORD_DURABILITY = 1829;

    @Config.Name("Ender Giant Sword: Damage")
    public static double ENDER_GIANT_SWORD_DAMAGE = 9.0D;

    @Config.Name("Ender Giant Sword: Cooldown")
    public static int ENDER_GIANT_SWORD_COOLDOWN = 100;

    @Config.Name("Ender Giant Sword: Max Use Duration")
    public static int ENDER_GIANT_SWORD_MAXUSEDURATION = 100;

    @Mod.EventBusSubscriber
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(AsgardShieldReloaded.MOD_ID)) ConfigManager.sync(AsgardShieldReloaded.MOD_ID, Config.Type.INSTANCE);
        }
    }
}