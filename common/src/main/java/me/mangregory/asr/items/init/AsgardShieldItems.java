package me.mangregory.asr.items.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.registry.CreativeTabRegistry;
import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import me.mangregory.asr.util.AsgardShieldTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.enchantment.Enchantable;

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

    // Grouped views (populated in init()) for datagen iteration.
    public static List<RegistrySupplier<Item>> GIANT_SWORDS = List.of();
    public static List<RegistrySupplier<Item>> ASGARD_SHIELDS = List.of();

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

    public static void init() {
        DIAMOND_GIANT_SWORD = registerItem("diamond_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTDIAMOND.durability())
                                .sword(AsgardShieldToolMaterials.ALTDIAMOND, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ALTDIAMOND.enchantmentValue()))
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "diamond_giant_sword")))
                )
        );
        IRON_GIANT_SWORD = registerItem("iron_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTIRON.durability())
                                .sword(AsgardShieldToolMaterials.ALTIRON, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ALTIRON.enchantmentValue()))
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "iron_giant_sword")))

                )
        );
        GOLDEN_GIANT_SWORD = registerItem("golden_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTGOLD.durability())
                                .sword(AsgardShieldToolMaterials.ALTGOLD, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ALTGOLD.enchantmentValue()))
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "golden_giant_sword")))
                )
        );
        COPPER_GIANT_SWORD = registerItem("copper_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTCOPPER.durability())
                                .sword(AsgardShieldToolMaterials.ALTCOPPER, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ALTCOPPER.enchantmentValue()))
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "copper_giant_sword")))
                )
        );
        NETHERITE_GIANT_SWORD = registerItem("netherite_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTNETHERITE.durability())
                                .sword(AsgardShieldToolMaterials.ALTNETHERITE, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ALTNETHERITE.enchantmentValue()))
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "netherite_giant_sword")))
                )
        );
        STONE_GIANT_SWORD = registerItem("stone_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTSTONE.durability())
                                .sword(AsgardShieldToolMaterials.ALTSTONE, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ALTSTONE.enchantmentValue()))
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "stone_giant_sword")))
                )
        );
        WOODEN_GIANT_SWORD = registerItem("wooden_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTWOOD.durability())
                                .sword(AsgardShieldToolMaterials.ALTWOOD, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ALTWOOD.enchantmentValue()))
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "wooden_giant_sword")))
                )
        );
        ENDER_GIANT_SWORD = registerItem("ender_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ENDTOOLMATERIAL.durability())
                                .sword(AsgardShieldToolMaterials.ENDTOOLMATERIAL, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.ENDTOOLMATERIAL.enchantmentValue()))
                                .repairable(Items.OBSIDIAN)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "ender_giant_sword")))
                )
        );
        NETHERQUARTZ_GIANT_SWORD = registerItem("netherquartz_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.NQ_TOOLMATERIAL.durability())
                                .sword(AsgardShieldToolMaterials.NQ_TOOLMATERIAL, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.NQ_TOOLMATERIAL.enchantmentValue()))
                                .repairable(Items.QUARTZ)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "netherquartz_giant_sword")))
                )
        );
        SKULL_GIANT_SWORD = registerItem("skull_giant_sword",
                () -> new GiantSwordItem(
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL.durability())
                                .sword(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .component(DataComponents.ENCHANTABLE, new Enchantable(AsgardShieldToolMaterials.SKULL_TOOLMATERIAL.enchantmentValue()))
                                .repairable(Items.BONE)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "skull_giant_sword")))
                )
        );

        ENDER_SHIELD = registerItem("ender_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.OBSIDIAN)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "ender_shield")))
                )
        );
        GILDED_ENDER_SHIELD = registerItem("gilded_ender_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(580)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.OBSIDIAN)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_ender_shield")))
                )
        );
        DIAMOND_SHIELD = registerItem("diamond_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.DIAMOND_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "diamond_shield")))
                )
        );
        GILDED_DIAMOND_SHIELD = registerItem("gilded_diamond_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(580)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.DIAMOND_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_diamond_shield")))
                )
        );
        NETHERQUARTZ_SHIELD = registerItem("netherquartz_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(450)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.QUARTZ)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "netherquartz_shield")))
                )
        );
        GILDED_NETHERQUARTZ_SHIELD = registerItem("gilded_netherquartz_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.QUARTZ)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_netherquartz_shield")))
                )
        );
        SKULL_SHIELD = registerItem("skull_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(500)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.BONE)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "skull_shield")))
                )
        );
        GILDED_SKULL_SHIELD = registerItem("gilded_skull_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(580)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(Items.BONE)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_skull_shield")))

                )
        );
        IRON_SHIELD = registerItem("iron_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(380)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.IRON_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "iron_shield")))

                )
        );
        GILDED_IRON_SHIELD = registerItem("gilded_iron_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(420)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.IRON_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_iron_shield")))

                )
        );
        STONE_SHIELD = registerItem("stone_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(300)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.STONE_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "stone_shield")))

                )
        );
        GILDED_STONE_SHIELD = registerItem("gilded_stone_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(330)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.STONE_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_stone_shield")))
                )
        );
        COPPER_SHIELD = registerItem("copper_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(250)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.COPPER_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "copper_shield")))
                )
        );
        GILDED_COPPER_SHIELD = registerItem("gilded_copper_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(300)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.COPPER_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_copper_shield")))
                )
        );
        NETHERITE_SHIELD = registerItem("netherite_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(590)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.NETHERITE_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "netherite_shield")))
                )
        );
        GILDED_NETHERITE_SHIELD = registerItem("gilded_netherite_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(690)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .repairable(ItemTags.NETHERITE_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_netherite_shield")))
                )
        );
        WOODEN_SHIELD = registerItem("wooden_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(215)
                                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                                        context -> new BlocksAttacks(
                                                0.25f,
                                                1.0F,
                                                List.of(new BlocksAttacks.DamageReduction(
                                                        90.0f,
                                                        Optional.empty(),
                                                        0.5f,
                                                        1.0f
                                                )),
                                                new BlocksAttacks.ItemDamageFunction(0.0F, 0.0F, 0.0F),
                                                Optional.of(context.getOrThrow(AsgardShieldTags.WOODEN_SHIELD_BYPASSED_BY)),
                                                Optional.of(SoundEvents.SHIELD_BLOCK),
                                                Optional.of(SoundEvents.SHIELD_BREAK)
                                        ))
                                .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "wooden_shield")))
                                )
        );
        GILDED_WOODEN_SHIELD = registerItem("gilded_wooden_shield",
                () -> new AsgardShieldItem(
                        new Item.Properties()
                                .durability(280)
                                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                                        context -> new BlocksAttacks(
                                                0.25f,
                                                1.0F,
                                                List.of(new BlocksAttacks.DamageReduction(
                                                        90.0f,
                                                        Optional.empty(),
                                                        0.5f,
                                                        1.0f
                                                )),
                                                new BlocksAttacks.ItemDamageFunction(0.0F, 0.0F, 0.0F),
                                                Optional.of(context.getOrThrow(AsgardShieldTags.WOODEN_SHIELD_BYPASSED_BY)),
                                                Optional.of(SoundEvents.SHIELD_BLOCK),
                                                Optional.of(SoundEvents.SHIELD_BREAK)
                                        ))
                                .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
                                .equippableUnswappable(EquipmentSlot.OFFHAND)
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID, "gilded_wooden_shield")))
                                )
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
        registerCreativeTabs();
    }

    private static void registerCreativeTabs() {
        // Register all items to COMBAT creative tab
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, NETHERITE_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, DIAMOND_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, IRON_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GOLDEN_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, COPPER_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, STONE_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, WOODEN_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, ENDER_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, NETHERQUARTZ_GIANT_SWORD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, SKULL_GIANT_SWORD);

        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, DIAMOND_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_DIAMOND_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, IRON_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_IRON_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, ENDER_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_ENDER_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, NETHERQUARTZ_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_NETHERQUARTZ_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, SKULL_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_SKULL_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, STONE_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_STONE_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, COPPER_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_COPPER_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, NETHERITE_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_NETHERITE_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, WOODEN_SHIELD);
        CreativeTabRegistry.append(CreativeModeTabs.COMBAT, GILDED_WOODEN_SHIELD);
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(Identifier.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, name), item);
    }
}
