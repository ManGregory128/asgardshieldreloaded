package me.mangregory.asr.init;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.item.AsgardShieldItem;
import me.mangregory.asr.item.GiantSwordItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;



public class ItemInit {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AsgardShieldReloaded.MODID);

    public static final Supplier<GiantSwordItem> DIAMOND_GIANT_SWORD = ITEMS.register("diamond_giant_sword", () -> new GiantSwordItem(
            Tiers.ALTDIAMOND,
            100,
            new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            Tiers.ALTDIAMOND,
                            3,
                            -2.4f
                    )
            )));

    public static final Supplier<GiantSwordItem> IRON_GIANT_SWORD = ITEMS.register("iron_giant_sword",
            () -> new GiantSwordItem(
                    Tiers.ALTIRON,
                    100,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    Tiers.ALTIRON,
                                    3,
                                    -2.4f
                            )
                    )));

    public static final Supplier<GiantSwordItem> GOLDEN_GIANT_SWORD = ITEMS.register("golden_giant_sword",
            () -> new GiantSwordItem(
                    Tiers.ALTGOLD,
                    100,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    Tiers.ALTGOLD,
                                    3,
                                    -2.4f
                            )
                    )));

    public static final Supplier<GiantSwordItem> STONE_GIANT_SWORD = ITEMS.register("stone_giant_sword",
            () -> new GiantSwordItem(
                    Tiers.ALTSTONE,
                    100,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    Tiers.ALTSTONE,
                                    3,
                                    -2.4f
                            )
                    )));

    public static final Supplier<GiantSwordItem> WOODEN_GIANT_SWORD = ITEMS.register("wooden_giant_sword",
            () -> new GiantSwordItem(
                    Tiers.ALTWOOD,
                    100,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    Tiers.ALTWOOD,
                                    3,
                                    -2.4f
                            )
                    )));

    public static final Supplier<GiantSwordItem> ENDER_GIANT_SWORD = ITEMS.register("ender_giant_sword",
            () -> new GiantSwordItem(
                    Tiers.ENDERTIER,
                    100,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    Tiers.ENDERTIER,
                                    3,
                                    -2.4f
                            )
                    )));

    public static final Supplier<AsgardShieldItem> WOODEN_SHIELD = ITEMS.register("wooden_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(215), 100, "Arrow Catch", "Fire Damage"));
    public static final Supplier<AsgardShieldItem> GILDED_WOODEN_SHIELD = ITEMS.register("gilded_wooden_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(280), 100, "Arrow Catch", "Fire Damage"));
    public static final Supplier<AsgardShieldItem> STONE_SHIELD = ITEMS.register("stone_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(300), 100, "Fire Protection", "Breaks Easily from Explosions"));
    public static final Supplier<AsgardShieldItem> GILDED_STONE_SHIELD = ITEMS.register("gilded_stone_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(330), 100, "Fire Protection", "Breaks Easily from Explosions"));
    public static final Supplier<AsgardShieldItem> IRON_SHIELD = ITEMS.register("iron_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(380), 100, "Decreased Explosion Damage", "Water (Rusts in Wet Environments)"));
    public static final Supplier<AsgardShieldItem> GILDED_IRON_SHIELD = ITEMS.register("gilded_iron_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(420), 100, "Decreased Explosion Damage", "Water (Rusts in Wet Environments)"));
    public static final Supplier<AsgardShieldItem> DIAMOND_SHIELD = ITEMS.register("diamond_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(500), 100, "Reflect Projectiles to Attacker", "Reflecting Damages the Shield"));
    public static final Supplier<AsgardShieldItem> GILDED_DIAMOND_SHIELD = ITEMS.register("gilded_diamond_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(580), 100, "Reflect Projectiles to Attacker", "Reflecting Damages the Shield"));
    public static final Supplier<AsgardShieldItem> NETHERQUARTZ_SHIELD = ITEMS.register("netherquartz_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(450), 100, "Flame Keeper - Collect Fire Charges", "Hungering"));
    public static final Supplier<AsgardShieldItem> GILDED_NETHERQUARTZ_SHIELD = ITEMS.register("gilded_netherquartz_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(500), 100, "Flame Keeper - Collect Fire Charges", "Hungering"));
    public static final Supplier<AsgardShieldItem> SKULL_SHIELD = ITEMS.register("skull_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(500), 100, "Mind Control - Enemies Might Attack Enemies", "Brittle Bones - 3x Durability Damage"));
    public static final Supplier<AsgardShieldItem> GILDED_SKULL_SHIELD = ITEMS.register("gilded_skull_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(580), 100, "Mind Control - Enemies Might Attack Enemies", "Brittle Bones - 3x Durability Damage"));
    public static final Supplier<AsgardShieldItem> ENDER_SHIELD = ITEMS.register("ender_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(500), 100, "Ender Shift - Enemies Might Teleport", "End Tech - Might be Bypassed by Ender Mobs"));
    public static final Supplier<AsgardShieldItem> GILDED_ENDER_SHIELD = ITEMS.register("gilded_ender_shield",
            () -> new AsgardShieldItem(new Item.Properties().durability(580), 100, "Ender Shift - Enemies Might Teleport", "End Tech - Might be Bypassed by Ender Mobs"));

    public static class Tiers {
        public static final Tier ALTDIAMOND = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3100, -2.4f, 4f, 15, () -> Ingredient.of(Items.DIAMOND));
        public static final Tier ALTIRON = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 410, 6f, 3f, 10, () -> Ingredient.of(Items.IRON_INGOT));
        public static final Tier ALTGOLD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 60, 12.0f, 1f, 22, () -> Ingredient.of(Items.GOLD_INGOT));
        public static final Tier ALTSTONE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 215, 4f, 2f, 5,  () -> Ingredient.of(Items.COBBLESTONE));
        public static final Tier ALTWOOD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 88, 2f, 1f, 7,  () -> Ingredient.of(ItemTags.PLANKS));
        public static final Tier ENDERTIER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 4200, -2.4f, 4f, 20,  () -> Ingredient.of(Items.NETHERITE_INGOT));
    }
}
