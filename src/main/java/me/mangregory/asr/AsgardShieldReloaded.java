package me.mangregory.asr;

import me.mangregory.asr.init.ItemInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("asr")
public class AsgardShieldReloaded {
	
	public static final String MOD_ID = "asr";
	
	public AsgardShieldReloaded() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ItemInit.ITEMS.register(bus);
		MinecraftForge.EVENT_BUS.register(this);
	}
}
