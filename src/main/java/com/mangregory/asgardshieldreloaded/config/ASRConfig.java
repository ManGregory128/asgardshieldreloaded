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
    public static final CategoryMisc MISC = new CategoryMisc();

    public static class CategorySwords
    {
        @Config.Name("Wooden Giant Sword: Durability")
        public int WOODEN_GIANT_SWORD_DURABILITY = 88;

        @Config.Name("Wooden Giant Sword: Damage")
        public double WOODEN_GIANT_SWORD_DAMAGE = 5.0D;

        @Config.Name("Stone Giant Sword: Durability")
        public int STONE_GIANT_SWORD_DURABILITY = 176;

        @Config.Name("Stone Giant Sword: Damage")
        public double STONE_GIANT_SWORD_DAMAGE = 6.0D;

        @Config.Name("Iron Giant Sword: Durability")
        public int IRON_GIANT_SWORD_DURABILITY = 475;

        @Config.Name("Iron Giant Sword: Damage")
        public double IRON_GIANT_SWORD_DAMAGE = 7.0D;

        @Config.Name("Gold Giant Sword: Durability")
        public int GOLD_GIANT_SWORD_DURABILITY = 151;

        @Config.Name("Gold Giant Sword: Damage")
        public double GOLD_GIANT_SWORD_DAMAGE = 5.0D;

        @Config.Name("Diamond Giant Sword: Durability")
        public int DIAMOND_GIANT_SWORD_DURABILITY = 2241;

        @Config.Name("Diamond Giant Sword: Damage")
        public double DIAMOND_GIANT_SWORD_DAMAGE = 8.0D;

        @Config.Name("Nether Quartz Giant Sword: Durability")
        public int NETHERQUARTZ_GIANT_SWORD_DURABILITY = 525;

        @Config.Name("Nether Quartz Giant Sword: Damage")
        public double NETHERQUARTZ_GIANT_SWORD_DAMAGE = 7.0D;

        @Config.Name("Patchwork Giant Sword: Durability")
        public int PATCHWORK_GIANT_SWORD_DURABILITY = 45;

        @Config.Name("Patchwork Giant Sword: Damage")
        public double PATCHWORK_GIANT_SWORD_DAMAGE = 5.0D;

        @Config.Name("Skull Giant Sword: Durability")
        public int SKULL_GIANT_SWORD_DURABILITY = 360;

        @Config.Name("Skull Giant Sword: Damage")
        public double SKULL_GIANT_SWORD_DAMAGE = 6.0D;

        @Config.Name("Ender Giant Sword: Durability")
        public int ENDER_GIANT_SWORD_DURABILITY = 1529;

        @Config.Name("Ender Giant Sword: Damage")
        public double ENDER_GIANT_SWORD_DAMAGE = 8.0D;

        @Config.Name("Biomass Giant Sword: Durability")
        public int BIOMASS_GIANT_SWORD_DURABILITY = 225;

        @Config.Name("Biomass Giant Sword: Damage")
        public double BIOMASS_GIANT_SWORD_DAMAGE = 6.0D;

        @Config.Name("Livingmetal Giant Sword: Durability")
        public int LIVINGMETAL_GIANT_SWORD_DURABILITY = 481;

        @Config.Name("Livingmetal Giant Sword: Damage")
        public double LIVINGMETAL_GIANT_SWORD_DAMAGE = 6.0D;
    }

    public static class CategoryShields
    {
        @Config.Name("Wooden Shield: Durability")
        public int WOODEN_SHIELD_DURABILITY = 118;

        @Config.Name("Gilded Wooden Shield: Durability")
        public int GILDED_WOODEN_SHIELD_DURABILITY = 246;

        @Config.Name("Stone Shield: Durability")
        public int STONE_SHIELD_DURABILITY = 131;

        @Config.Name("Gilded Stone Shield: Durability")
        public int GILDED_STONE_SHIELD_DURABILITY = 259;

        @Config.Name("Iron Shield: Durability")
        public int IRON_SHIELD_DURABILITY = 250;

        @Config.Name("Gilded Iron Shield: Durability")
        public int GILDED_IRON_SHIELD_DURABILITY = 378;

        @Config.Name("Golden Shield: Durability")
        public int GOLDEN_SHIELD_DURABILITY = 121;

        @Config.Name("Blessed Golden Shield: Durability")
        public int BLESSED_GOLDEN_SHIELD_DURABILITY = 489;

        @Config.Name("Diamond Shield: Durability")
        public int DIAMOND_SHIELD_DURABILITY = 780;

        @Config.Name("Gilded Diamond Shield: Durability")
        public int GILDED_DIAMOND_SHIELD_DURABILITY = 908;

        @Config.Name("Nether Quartz Shield: Durability")
        public int NETHERQUARTZ_SHIELD_DURABILITY = 369;

        @Config.Name("Gilded Nether Quartz Shield: Durability")
        public int GILDED_NETHERQUARTZ_SHIELD_DURABILITY = 497;

        @Config.Name("Patchwork Shield: Durability")
        public int PATCHWORK_SHIELD_DURABILITY = 60;

        @Config.Name("Gilded Patchwork Shield: Durability")
        public int GILDED_PATCHWORK_SHIELD_DURABILITY = 188;

        @Config.Name("Skull Shield: Durability")
        public int SKULL_SHIELD_DURABILITY = 174;

        @Config.Name("Gilded Skull Shield: Durability")
        public int GILDED_SKULL_SHIELD_DURABILITY = 302;

        @Config.Name("Ender Shield: Durability")
        public int ENDER_SHIELD_DURABILITY = 450;

        @Config.Name("Gilded Ender Shield: Durability")
        public int GILDED_ENDER_SHIELD_DURABILITY = 578;

        @Config.Name("Biomass Shield: Durability")
        public int BIOMASS_SHIELD_DURABILITY = 481;

        @Config.Name("Gilded Biomass Shield: Durability")
        public int GILDED_BIOMASS_SHIELD_DURABILITY = 650;

        @Config.Name("Livingmetal Shield: Durability")
        public int LIVINGMETAL_SHIELD_DURABILITY = 481;

        @Config.Name("Gilded Livingmetal Shield: Durability")
        public int GILDED_LIVINGMETAL_SHIELD_DURABILITY = 650;

    }

    public static class CategoryMisc
    {
        @Config.Name("Blocking Sound")
        @Config.Comment("Plays a sound when attempting to block")
        public boolean BLOCKING_SOUND = true;
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