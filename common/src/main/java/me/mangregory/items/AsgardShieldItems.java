package me.mangregory.items;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.mangregory.AsgardShieldReloaded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class AsgardShieldItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            AsgardShieldReloaded.MOD_ID,
            Registries.ITEM
    );

    public static RegistrySupplier<Item> DIAMOND_GIANT_SWORD;

    public static void init() {
        DIAMOND_GIANT_SWORD = registerItem("diamond_giant_sword",
                () -> new Item(new Item.Properties()
                        .stacksTo(1)
                        .durability(AsgardShieldToolMaterials.ALTDIAMOND.durability())
                        .sword(AsgardShieldToolMaterials.ALTDIAMOND, 3, -2.4f)
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"diamond_giant_sword")))
                        .arch$tab(CreativeModeTabs.COMBAT)
                )
        );

        ITEMS.register();
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, name), item);
    }
}
