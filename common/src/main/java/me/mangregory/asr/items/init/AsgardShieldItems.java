package me.mangregory.asr.items.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;

public class AsgardShieldItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            AsgardShieldReloaded.MOD_ID,
            Registries.ITEM
    );

    public static RegistrySupplier<Item> DIAMOND_GIANT_SWORD;
    public static RegistrySupplier<Item> IRON_GIANT_SWORD;
    public static RegistrySupplier<Item> GOLDEN_GIANT_SWORD;
    public static RegistrySupplier<Item> COPPER_GIANT_SWORD;
    public static RegistrySupplier<Item> NETHERITE_GIANT_SWORD;
    public static RegistrySupplier<Item> STONE_GIANT_SWORD;
    public static RegistrySupplier<Item> WOODEN_GIANT_SWORD;
    public static RegistrySupplier<Item> ENDER_GIANT_SWORD;
    public static RegistrySupplier<Item> NETHERQUARTZ_GIANT_SWORD;
    public static RegistrySupplier<Item> SKULL_GIANT_SWORD;

    public static RegistrySupplier<Item> DIAMOND_SHIELD;
    public static RegistrySupplier<Item> GILDED_DIAMOND_SHIELD;
    public static RegistrySupplier<Item> IRON_SHIELD;
    public static RegistrySupplier<Item> GILDED_IRON_SHIELD;
    public static RegistrySupplier<Item> ENDER_SHIELD;
    public static RegistrySupplier<Item> GILDED_ENDER_SHIELD;
    public static RegistrySupplier<Item> NETHERQUARTZ_SHIELD;
    public static RegistrySupplier<Item> GILDED_NETHERQUARTZ_SHIELD;
    public static RegistrySupplier<Item> SKULL_SHIELD;
    public static RegistrySupplier<Item> GILDED_SKULL_SHIELD;
    public static RegistrySupplier<Item> STONE_SHIELD;
    public static RegistrySupplier<Item> GILDED_STONE_SHIELD;
    public static RegistrySupplier<Item> COPPER_SHIELD;
    public static RegistrySupplier<Item> GILDED_COPPER_SHIELD;
    public static RegistrySupplier<Item> WOODEN_SHIELD;
    public static RegistrySupplier<Item> GILDED_WOODEN_SHIELD;
    public static RegistrySupplier<Item> NETHERITE_SHIELD;
    public static RegistrySupplier<Item> GILDED_NETHERITE_SHIELD;

    public static List<RegistrySupplier<Item>> GIANT_SWORDS = List.of();
    public static List<RegistrySupplier<Item>> ASGARD_SHIELDS = List.of();

    public static void init() {
        DIAMOND_GIANT_SWORD = registerItem("diamond_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ALTDIAMOND.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        IRON_GIANT_SWORD = registerItem("iron_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ALTIRON.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GOLDEN_GIANT_SWORD = registerItem("golden_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ALTGOLD.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        COPPER_GIANT_SWORD = registerItem("copper_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ALTCOPPER.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        NETHERITE_GIANT_SWORD = registerItem("netherite_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ALTNETHERITE.getUses()).fireResistant()
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        STONE_GIANT_SWORD = registerItem("stone_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ALTSTONE.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        WOODEN_GIANT_SWORD = registerItem("wooden_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ALTWOOD.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        ENDER_GIANT_SWORD = registerItem("ender_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.ENDTOOLMATERIAL.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        NETHERQUARTZ_GIANT_SWORD = registerItem("netherquartz_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.NQ_TOOLMATERIAL.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        SKULL_GIANT_SWORD = registerItem("skull_giant_sword",
                () -> new GiantSwordItem(new Item.Properties().durability(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL.getUses())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );

        ENDER_SHIELD = registerItem("ender_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(600)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_ENDER_SHIELD = registerItem("gilded_ender_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(700)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        DIAMOND_SHIELD = registerItem("diamond_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(600)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_DIAMOND_SHIELD = registerItem("gilded_diamond_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(680)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        NETHERQUARTZ_SHIELD = registerItem("netherquartz_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(450)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_NETHERQUARTZ_SHIELD = registerItem("gilded_netherquartz_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(500)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        SKULL_SHIELD = registerItem("skull_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(500)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_SKULL_SHIELD = registerItem("gilded_skull_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(580)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        IRON_SHIELD = registerItem("iron_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(400)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_IRON_SHIELD = registerItem("gilded_iron_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(480)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        STONE_SHIELD = registerItem("stone_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(300)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_STONE_SHIELD = registerItem("gilded_stone_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(330)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        COPPER_SHIELD = registerItem("copper_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(350)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_COPPER_SHIELD = registerItem("gilded_copper_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(380)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        NETHERITE_SHIELD = registerItem("netherite_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(800).fireResistant()
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_NETHERITE_SHIELD = registerItem("gilded_netherite_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(900).fireResistant()
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        WOODEN_SHIELD = registerItem("wooden_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(215)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_WOODEN_SHIELD = registerItem("gilded_wooden_shield",
                () -> new AsgardShieldItem(new Item.Properties().durability(280)
                        .arch$tab(CreativeModeTabs.COMBAT))
        );

        GIANT_SWORDS = List.of(
                NETHERITE_GIANT_SWORD,
                DIAMOND_GIANT_SWORD,
                IRON_GIANT_SWORD,
                GOLDEN_GIANT_SWORD,
                COPPER_GIANT_SWORD,
                STONE_GIANT_SWORD,
                WOODEN_GIANT_SWORD,
                ENDER_GIANT_SWORD,
                NETHERQUARTZ_GIANT_SWORD,
                SKULL_GIANT_SWORD
        );

        ASGARD_SHIELDS = List.of(
                DIAMOND_SHIELD,
                GILDED_DIAMOND_SHIELD,
                IRON_SHIELD,
                GILDED_IRON_SHIELD,
                ENDER_SHIELD,
                GILDED_ENDER_SHIELD,
                NETHERQUARTZ_SHIELD,
                GILDED_NETHERQUARTZ_SHIELD,
                SKULL_SHIELD,
                GILDED_SKULL_SHIELD,
                STONE_SHIELD,
                GILDED_STONE_SHIELD,
                COPPER_SHIELD,
                GILDED_COPPER_SHIELD,
                NETHERITE_SHIELD,
                GILDED_NETHERITE_SHIELD,
                WOODEN_SHIELD,
                GILDED_WOODEN_SHIELD
        );

        ITEMS.register();
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, name), item);
    }
}