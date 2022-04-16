package me.mangregory.asr.init;

import com.google.common.base.Supplier;

import me.mangregory.asr.AsgardShieldReloaded;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AsgardShieldReloaded.MOD_ID);
	
	public static final RegistryObject<Item> DIAMOND_GIANT_SWORD = register("diamond_giant_sword", () -> new SwordItem(ModTiers.CUSTOM_DIAMOND, 2, 1f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}
}
