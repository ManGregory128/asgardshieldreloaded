package me.mangregory.asr.init;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.item.AsgardShieldItem;
import me.mangregory.asr.item.GiantSwordItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AsgardShieldReloaded.MODID);
    public static final Item.Properties PROPERTIES = new Item.Properties();
    public static final RegistryObject<SwordItem> DIAMOND_GIANT_SWORD = ITEMS.register("diamond_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTDIAMOND, 1, -2.4f, PROPERTIES, 100));

    public static final RegistryObject<GiantSwordItem> IRON_GIANT_SWORD = ITEMS.register("iron_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTIRON, 1, -2.4f, PROPERTIES, 100));

    public static final RegistryObject<GiantSwordItem> GOLDEN_GIANT_SWORD = ITEMS.register("golden_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTGOLD, 1, -2.4f, PROPERTIES, 100));

    public static final RegistryObject<GiantSwordItem> STONE_GIANT_SWORD = ITEMS.register("stone_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTSTONE, 1, -2.4f, PROPERTIES, 100));

    public static final RegistryObject<GiantSwordItem> WOODEN_GIANT_SWORD = ITEMS.register("wooden_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTWOOD, 1, -2.4f, PROPERTIES, 100));

    public static final RegistryObject<GiantSwordItem> ENDER_GIANT_SWORD = ITEMS.register("ender_giant_sword",
            () -> new GiantSwordItem(Tiers.ENDERTIER, 1, -2f, PROPERTIES, 100));

    public static final RegistryObject<AsgardShieldItem> WOODEN_SHIELD = ITEMS.register("wooden_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Arrow Catch", "Fire Damage"));
    public static final RegistryObject<AsgardShieldItem> GILDED_WOODEN_SHIELD = ITEMS.register("gilded_wooden_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Arrow Catch", "Fire Damage"));
    public static final RegistryObject<AsgardShieldItem> STONE_SHIELD = ITEMS.register("stone_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Fire Protection", "Breaks Easily from Explosions"));
    public static final RegistryObject<AsgardShieldItem> GILDED_STONE_SHIELD = ITEMS.register("gilded_stone_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Fire Protection", "Breaks Easily from Explosions"));
    public static final RegistryObject<AsgardShieldItem> IRON_SHIELD = ITEMS.register("iron_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Decreased Explosion Damage", "Water (Rusts in Wet Environments)"));
    public static final RegistryObject<AsgardShieldItem> GILDED_IRON_SHIELD = ITEMS.register("gilded_iron_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Decreased Explosion Damage", "Water (Rusts in Wet Environments)"));
    public static final RegistryObject<AsgardShieldItem> DIAMOND_SHIELD = ITEMS.register("diamond_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Reflect Projectiles to Attacker", "Reflecting Damages the Shield"));
    public static final RegistryObject<AsgardShieldItem> GILDED_DIAMOND_SHIELD = ITEMS.register("gilded_diamond_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Reflect Projectiles to Attacker", "Reflecting Damages the Shield"));
    public static final RegistryObject<AsgardShieldItem> NETHERQUARTZ_SHIELD = ITEMS.register("netherquartz_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Flame Keeper - Collect Fire Charges", "Hungering"));
    public static final RegistryObject<AsgardShieldItem> GILDED_NETHERQUARTZ_SHIELD = ITEMS.register("gilded_netherquartz_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Flame Keeper - Collect Fire Charges", "Hungering"));
    public static final RegistryObject<AsgardShieldItem> SKULL_SHIELD = ITEMS.register("skull_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Mind Control - Enemies Might Attack Enemies", "Brittle Bones - 3x Durability Damage"));
    public static final RegistryObject<AsgardShieldItem> GILDED_SKULL_SHIELD = ITEMS.register("gilded_skull_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Mind Control - Enemies Might Attack Enemies", "Brittle Bones - 3x Durability Damage"));
    public static final RegistryObject<AsgardShieldItem> ENDER_SHIELD = ITEMS.register("ender_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Ender Shift - Enemies Might Teleport", "End Tech - Might be Bypassed by Ender Mobs"));
    public static final RegistryObject<AsgardShieldItem> GILDED_ENDER_SHIELD = ITEMS.register("gilded_ender_shield",
            () -> new AsgardShieldItem(PROPERTIES, 100, "Ender Shift - Enemies Might Teleport", "End Tech - Might be Bypassed by Ender Mobs"));

    public static class Tiers {
        public static final Tier ALTDIAMOND = new ForgeTier(3, 3100, -2.4f, 6.5f, 15, null, () -> Ingredient.of(Items.DIAMOND));
        public static final Tier ALTIRON = new ForgeTier(2, 410, 6f, 5.5f, 10, null, () -> Ingredient.of(Items.IRON_INGOT));
        public static final Tier ALTGOLD = new ForgeTier(0, 60, 12.0f, 3.5f, 22, null, () -> Ingredient.of(Items.GOLD_INGOT));
        public static final Tier ALTSTONE = new ForgeTier(1, 215, 4f, 4.5f, 5, null, () -> Ingredient.of(Items.COBBLESTONE));
        public static final Tier ALTWOOD = new ForgeTier(0, 88, 2f, 3f, 7, null, () -> Ingredient.of(ItemTags.PLANKS));
        public static final Tier ENDERTIER = new ForgeTier(3, 4200, -2.4f, 6.5f, 20, null, () -> Ingredient.of(Items.NETHERITE_INGOT));
    }
}
