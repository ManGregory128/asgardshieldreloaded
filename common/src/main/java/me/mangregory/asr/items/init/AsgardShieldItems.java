package me.mangregory.asr.items.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.SwordItem;

import java.util.List;
import java.util.function.Function;

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

    private static RegistrySupplier<Item> registerShield(String name, int durability, TagKey<Item> repairTag) {
        // 1.21.2+: repair items are declared via the repairable component, not an isValidRepairItem override
        // 1.21.2+ Equippable component: lets shields shift-click / auto-equip into the offhand slot
        return registerItem(name, key -> new AsgardShieldItem(new Item.Properties().setId(key).durability(durability).repairable(repairTag)
                .equippableUnswappable(EquipmentSlot.OFFHAND)
                .arch$tab(CreativeModeTabs.COMBAT)));
    }
    public static void init() {
        DIAMOND_GIANT_SWORD = registerItem("diamond_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ALTDIAMOND, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ALTDIAMOND.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        IRON_GIANT_SWORD = registerItem("iron_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ALTIRON, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ALTIRON.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GOLDEN_GIANT_SWORD = registerItem("golden_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ALTGOLD, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ALTGOLD.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        COPPER_GIANT_SWORD = registerItem("copper_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ALTCOPPER, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ALTCOPPER.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        NETHERITE_GIANT_SWORD = registerItem("netherite_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ALTNETHERITE, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ALTNETHERITE.durability()).fireResistant()
                        .fireResistant()
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        STONE_GIANT_SWORD = registerItem("stone_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ALTSTONE, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ALTSTONE.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        WOODEN_GIANT_SWORD = registerItem("wooden_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ALTWOOD, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ALTWOOD.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        ENDER_GIANT_SWORD = registerItem("ender_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.ENDTOOLMATERIAL, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.ENDTOOLMATERIAL.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        NETHERQUARTZ_GIANT_SWORD = registerItem("netherquartz_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.NQ_TOOLMATERIAL, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.NQ_TOOLMATERIAL.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        SKULL_GIANT_SWORD = registerItem("skull_giant_sword",
                key -> new GiantSwordItem(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL, new Item.Properties().setId(key).durability(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL.durability())
                        .arch$tab(CreativeModeTabs.COMBAT))
        );

        ENDER_SHIELD = registerShield("ender_shield", 600, AsgardShieldToolMaterials.REPAIR_OBSIDIAN);
        GILDED_ENDER_SHIELD = registerShield("gilded_ender_shield", 700, AsgardShieldToolMaterials.REPAIR_OBSIDIAN);
        DIAMOND_SHIELD = registerShield("diamond_shield", 600, ItemTags.DIAMOND_TOOL_MATERIALS);
        GILDED_DIAMOND_SHIELD = registerShield("gilded_diamond_shield", 680, ItemTags.DIAMOND_TOOL_MATERIALS);
        NETHERQUARTZ_SHIELD = registerShield("netherquartz_shield", 450, AsgardShieldToolMaterials.REPAIR_QUARTZ);
        GILDED_NETHERQUARTZ_SHIELD = registerShield("gilded_netherquartz_shield", 500, AsgardShieldToolMaterials.REPAIR_QUARTZ);
        SKULL_SHIELD = registerShield("skull_shield", 500, AsgardShieldToolMaterials.REPAIR_BONE);
        GILDED_SKULL_SHIELD = registerShield("gilded_skull_shield", 580, AsgardShieldToolMaterials.REPAIR_BONE);
        IRON_SHIELD = registerShield("iron_shield", 400, ItemTags.IRON_TOOL_MATERIALS);
        GILDED_IRON_SHIELD = registerShield("gilded_iron_shield", 480, ItemTags.IRON_TOOL_MATERIALS);
        STONE_SHIELD = registerShield("stone_shield", 300, ItemTags.STONE_TOOL_MATERIALS);
        GILDED_STONE_SHIELD = registerShield("gilded_stone_shield", 330, ItemTags.STONE_TOOL_MATERIALS);
        COPPER_SHIELD = registerShield("copper_shield", 350, AsgardShieldToolMaterials.REPAIR_COPPER);
        GILDED_COPPER_SHIELD = registerShield("gilded_copper_shield", 380, AsgardShieldToolMaterials.REPAIR_COPPER);
        NETHERITE_SHIELD = registerItem("netherite_shield",
                key -> new AsgardShieldItem(new Item.Properties().setId(key).durability(800).fireResistant()
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_NETHERITE_SHIELD = registerItem("gilded_netherite_shield",
                key -> new AsgardShieldItem(new Item.Properties().setId(key).durability(900).fireResistant()
                        .arch$tab(CreativeModeTabs.COMBAT))
        );
        WOODEN_SHIELD = registerShield("wooden_shield", 215, ItemTags.PLANKS);
        GILDED_WOODEN_SHIELD = registerShield("gilded_wooden_shield", 280, ItemTags.PLANKS);

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

    public static RegistrySupplier<Item> registerItem(String name, Function<ResourceKey<Item>, Item> factory) {
        // 1.21.2+: Item.Properties must carry the registry id (setId) before the Item is constructed
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, name));
        return ITEMS.register(key.location(), () -> factory.apply(key));
    }
}