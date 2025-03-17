package com.mangregory.asgardshieldreloaded;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.mangregory.asgardshieldreloaded.compat.CompatHandler;

@Mod(modid = AsgardShieldReloaded.MOD_ID, name = AsgardShieldReloaded.NAME, version = AsgardShieldReloaded.VERSION, acceptedMinecraftVersions = AsgardShieldReloaded.ACCEPTED_VERSIONS, dependencies = AsgardShieldReloaded.DEPENDENCIES)
public class AsgardShieldReloaded
{
    public static final String MOD_ID = "asr";
    public static final String NAMESPACE = MOD_ID + ":";
    public static final String NAME = "Asgard Shield Reloaded";
    public static final String VERSION = "2.2.1";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String DEPENDENCIES = "after:harkenscythe";

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event)
    {
        CompatHandler.init();
    }
}