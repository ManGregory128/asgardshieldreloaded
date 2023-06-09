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
    public static final CategorySwords SWORDS = new CategorySwords();
    public static final CategoryShields SHIELDS = new CategoryShields();

    public static class CategorySwords
    {
        @Config.Name("Wooden Giant Sword: Durability")
        public int WOODEN_GIANT_SWORD_DURABILITY = 88;

        @Config.Name("Wooden Giant Sword: Damage")
        public double WOODEN_GIANT_SWORD_DAMAGE = 5.0D;

        @Config.Name("Wooden Giant Sword: Max Use Duration")
        public int WOODEN_GIANT_SWORD_MAXUSEDURATION = 100;

        @Config.Name("Stone Giant Sword: Durability")
        public int STONE_GIANT_SWORD_DURABILITY = 196;

        @Config.Name("Stone Giant Sword: Damage")
        public double STONE_GIANT_SWORD_DAMAGE = 6.0D;

        @Config.Name("Stone Giant Sword: Max Use Duration")
        public int STONE_GIANT_SWORD_MAXUSEDURATION = 100;

        @Config.Name("Iron Giant Sword: Durability")
        public int IRON_GIANT_SWORD_DURABILITY = 375;

        @Config.Name("Iron Giant Sword: Damage")
        public double IRON_GIANT_SWORD_DAMAGE = 7.0D;

        @Config.Name("Iron Giant Sword: Max Use Duration")
        public int IRON_GIANT_SWORD_MAXUSEDURATION = 100;

        @Config.Name("Gold Giant Sword: Durability")
        public int GOLD_GIANT_SWORD_DURABILITY = 48;

        @Config.Name("Gold Giant Sword: Damage")
        public double GOLD_GIANT_SWORD_DAMAGE = 5.0D;

        @Config.Name("Gold Giant Sword: Max Use Duration")
        public int GOLD_GIANT_SWORD_MAXUSEDURATION = 100;

        @Config.Name("Diamond Giant Sword: Durability")
        public int DIAMOND_GIANT_SWORD_DURABILITY = 2341;

        @Config.Name("Diamond Giant Sword: Damage")
        public double DIAMOND_GIANT_SWORD_DAMAGE = 8.0D;

        @Config.Name("Diamond Giant Sword: Max Use Duration")
        public int DIAMOND_GIANT_SWORD_MAXUSEDURATION = 100;

        @Config.Name("Nether Quartz Giant Sword: Durability")
        public int NETHERQUARTZ_GIANT_SWORD_DURABILITY = 1646;

        @Config.Name("Nether Quartz Giant Sword: Damage")
        public double NETHERQUARTZ_GIANT_SWORD_DAMAGE = 7.0D;

        @Config.Name("Nether Quartz Giant Sword: Max Use Duration")
        public int NETHERQUARTZ_GIANT_SWORD_MAXUSEDURATION = 100;

        @Config.Name("Skull Giant Sword: Durability")
        public int SKULL_GIANT_SWORD_DURABILITY = 260;

        @Config.Name("Skull Giant Sword: Damage")
        public double SKULL_GIANT_SWORD_DAMAGE = 6.0D;

        @Config.Name("Skull Giant Sword: Max Use Duration")
        public int SKULL_GIANT_SWORD_MAXUSEDURATION = 100;

        @Config.Name("Ender Giant Sword: Durability")
        public int ENDER_GIANT_SWORD_DURABILITY = 1829;

        @Config.Name("Ender Giant Sword: Damage")
        public double ENDER_GIANT_SWORD_DAMAGE = 8.0D;

        @Config.Name("Ender Giant Sword: Max Use Duration")
        public int ENDER_GIANT_SWORD_MAXUSEDURATION = 100;
    }

    public static class CategoryShields
    {
        @Config.Name("Wooden Shield: Durability")
        public int WOODEN_SHIELD_DURABILITY = 118;

        @Config.Name("Wooden Shield: Max Use Duration")
        public int WOODEN_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Gilded Wooden Shield: Durability")
        public int GILDED_WOODEN_SHIELD_DURABILITY = 246;

        @Config.Name("Gilded Wooden Shield: Max Use Duration")
        public int GILDED_WOODEN_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Stone Shield: Durability")
        public int STONE_SHIELD_DURABILITY = 131;

        @Config.Name("Stone Shield: Max Use Duration")
        public int STONE_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Gilded Stone Shield: Durability")
        public int GILDED_STONE_SHIELD_DURABILITY = 259;

        @Config.Name("Gilded Stone Shield: Max Use Duration")
        public int GILDED_STONE_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Iron Shield: Durability")
        public int IRON_SHIELD_DURABILITY = 250;

        @Config.Name("Iron Shield: Max Use Duration")
        public int IRON_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Gilded Iron Shield: Durability")
        public int GILDED_IRON_SHIELD_DURABILITY = 378;

        @Config.Name("Gilded Iron Shield: Max Use Duration")
        public int GILDED_IRON_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Diamond Shield: Durability")
        public int DIAMOND_SHIELD_DURABILITY = 780;

        @Config.Name("Diamond Shield: Max Use Duration")
        public int DIAMOND_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Gilded Diamond Shield: Durability")
        public int GILDED_DIAMOND_SHIELD_DURABILITY = 908;

        @Config.Name("Gilded Diamond Shield: Max Use Duration")
        public int GILDED_DIAMOND_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Nether Quartz Shield: Durability")
        public int NETHERQUARTZ_SHIELD_DURABILITY = 369;

        @Config.Name("Nether Quartz Shield: Max Use Duration")
        public int NETHERQUARTZ_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Gilded Nether Quartz Shield: Durability")
        public int GILDED_NETHERQUARTZ_SHIELD_DURABILITY = 497;

        @Config.Name("Gilded Nether Quartz Shield: Max Use Duration")
        public int GILDED_NETHERQUARTZ_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Skull Shield: Durability")
        public int SKULL_SHIELD_DURABILITY = 174;

        @Config.Name("Skull Shield: Max Use Duration")
        public int SKULL_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Gilded Skull Shield: Durability")
        public int GILDED_SKULL_SHIELD_DURABILITY = 302;

        @Config.Name("Gilded Skull Shield: Max Use Duration")
        public int GILDED_SKULL_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Ender Shield: Durability")
        public int ENDER_SHIELD_DURABILITY = 435;

        @Config.Name("Ender Shield: Max Use Duration")
        public int ENDER_SHIELD_MAXUSEDURATION = 200;

        @Config.Name("Gilded Ender Shield: Durability")
        public int GILDED_ENDER_SHIELD_DURABILITY = 563;

        @Config.Name("Gilded Ender Shield: Max Use Duration")
        public int GILDED_ENDER_SHIELD_MAXUSEDURATION = 200;
    }

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