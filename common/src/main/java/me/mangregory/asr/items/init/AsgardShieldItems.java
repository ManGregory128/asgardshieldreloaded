package me.mangregory.asr.items.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlocksAttacks;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class AsgardShieldItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            AsgardShieldReloaded.MOD_ID,
            Registries.ITEM
    );

    public static RegistrySupplier<Item> DIAMOND_GIANT_SWORD;
    public static RegistrySupplier<Item> IRON_GIANT_SWORD;
    public static RegistrySupplier<Item> GOLDEN_GIANT_SWORD;
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
    public static RegistrySupplier<Item> WOODEN_SHIELD;
    public static RegistrySupplier<Item> GILDED_WOODEN_SHIELD;

    private static final BlocksAttacks BLOCKS_ATTACKS = new BlocksAttacks(
            0.25f, // blockDelaySeconds (5 ticks = 0.25 seconds)
            1.0f,  // disableCooldownScale
            List.of(new BlocksAttacks.DamageReduction(
                    90.0f, // horizontalBlockingAngle
                    Optional.empty(), // damage type filter (empty = all types)
                    0.5f,  // base damage reduction
                    1.0f   // factor (1000% damage reduction)
            )),
            new BlocksAttacks.ItemDamageFunction(0.0f, 0.0f, 0.0f),
            Optional.empty(), // bypassedBy damage types
            Optional.of(SoundEvents.SHIELD_BLOCK), // block sound
            Optional.of(SoundEvents.SHIELD_BREAK)  // disable sound
    );
    private static final BlocksAttacks BLOCKS_ATTACKS_WOODEN = new BlocksAttacks(
            0.25f,
            1.0f,
            List.of(new BlocksAttacks.DamageReduction(
                    90.0f,
                    Optional.empty(),
                    0.5f,
                    1.0f
            )),
            new BlocksAttacks.ItemDamageFunction(0.0f, 0.0f, 0.0f),
            Optional.of(DamageTypeTags.IS_FIRE), // bypassedBy all fire damage types
            Optional.of(SoundEvents.SHIELD_BLOCK),
            Optional.of(SoundEvents.SHIELD_BREAK)
    );

    public static void init() {
        DIAMOND_GIANT_SWORD = registerItem("diamond_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTDIAMOND.durability())
                                .sword(AsgardShieldToolMaterials.ALTDIAMOND, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                            AsgardShieldReloaded.MOD_ID,"diamond_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        IRON_GIANT_SWORD = registerItem("iron_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTIRON.durability())
                                .sword(AsgardShieldToolMaterials.ALTIRON, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"iron_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        GOLDEN_GIANT_SWORD = registerItem("golden_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTGOLD.durability())
                                .sword(AsgardShieldToolMaterials.ALTGOLD, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"golden_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        STONE_GIANT_SWORD = registerItem("stone_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTSTONE.durability())
                                .sword(AsgardShieldToolMaterials.ALTSTONE, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"stone_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        WOODEN_GIANT_SWORD = registerItem("wooden_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTWOOD.durability())
                                .sword(AsgardShieldToolMaterials.ALTWOOD, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"wooden_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        ENDER_GIANT_SWORD = registerItem("ender_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ENDTOOLMATERIAL.durability())
                                .sword(AsgardShieldToolMaterials.ENDTOOLMATERIAL, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.OBSIDIAN)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"ender_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        NETHERQUARTZ_GIANT_SWORD = registerItem("netherquartz_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.NQ_TOOLMATERIAL.durability())
                                .sword(AsgardShieldToolMaterials.NQ_TOOLMATERIAL, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.QUARTZ)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"netherquartz_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        SKULL_GIANT_SWORD = registerItem("skull_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL.durability())
                                .sword(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.BONE)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"skull_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );

        ENDER_SHIELD = registerItem("ender_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.OBSIDIAN)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"ender_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_ENDER_SHIELD = registerItem("gilded_ender_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(580)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.OBSIDIAN)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"gilded_ender_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        DIAMOND_SHIELD = registerItem("diamond_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.DIAMOND_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"diamond_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_DIAMOND_SHIELD = registerItem("gilded_diamond_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(580)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.DIAMOND_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"gilded_diamond_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        NETHERQUARTZ_SHIELD = registerItem("netherquartz_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(450)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.QUARTZ)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"netherquartz_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_NETHERQUARTZ_SHIELD = registerItem("gilded_netherquartz_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.QUARTZ)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"gilded_netherquartz_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        SKULL_SHIELD = registerItem("skull_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.BONE)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"skull_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_SKULL_SHIELD = registerItem("gilded_skull_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(580)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.BONE)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"gilded_skull_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        IRON_SHIELD = registerItem("iron_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(380)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.IRON_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"iron_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_IRON_SHIELD = registerItem("gilded_iron_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(420)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.IRON_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"gilded_iron_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        STONE_SHIELD = registerItem("stone_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(300)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.STONE_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"stone_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_STONE_SHIELD = registerItem("gilded_stone_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(330)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.STONE_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"gilded_stone_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        WOODEN_SHIELD = registerItem("wooden_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(215)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS_WOODEN)
                                .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"wooden_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );
        GILDED_WOODEN_SHIELD = registerItem("gilded_wooden_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(280)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS_WOODEN)
                                .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"gilded_wooden_shield")))
                                .arch$tab(CreativeModeTabs.COMBAT))
        );

        ITEMS.register();
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(Identifier.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, name), item);
    }
}
