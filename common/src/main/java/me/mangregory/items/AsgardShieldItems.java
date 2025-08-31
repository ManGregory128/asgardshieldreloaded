package me.mangregory.items;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.mangregory.AsgardShieldReloaded;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
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

    private static final BlocksAttacks BLOCKS_ATTACKS = new BlocksAttacks(
            0.25f, // blockDelaySeconds (5 ticks = 0.25 seconds)
            1.0f,  // disableCooldownScale
            List.of(new BlocksAttacks.DamageReduction(
                    90.0f, // horizontalBlockingAngle
                    Optional.empty(), // damage type filter (empty = all types)
                    0.5f,  // base damage reduction
                    1.0f   // factor (1000% damage reduction)
            )),
            BlocksAttacks.ItemDamageFunction.DEFAULT, // item damage function
            Optional.empty(), // bypassedBy damage types
            Optional.of(SoundEvents.SHIELD_BLOCK), // block sound
            Optional.of(SoundEvents.SHIELD_BREAK)  // disable sound
    );

    public static void init() {
        DIAMOND_GIANT_SWORD = registerItem("diamond_giant_sword",
                () -> new GiantSwordItem(
                        65,
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTDIAMOND.durability())
                                .sword(AsgardShieldToolMaterials.ALTDIAMOND, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(
                                            AsgardShieldReloaded.MOD_ID,"diamond_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );
        IRON_GIANT_SWORD = registerItem("iron_giant_sword",
                () -> new GiantSwordItem(
                        65,
                        new Item.Properties()
                                .durability(AsgardShieldToolMaterials.ALTIRON.durability())
                                .sword(AsgardShieldToolMaterials.ALTIRON, 3, -2.4f)
                                .component(DataComponents.BLOCKS_ATTACKS, BLOCKS_ATTACKS)
                                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,"iron_giant_sword")))
                                .arch$tab(CreativeModeTabs.COMBAT)
                )
        );

        ITEMS.register();
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, name), item);
    }
}
