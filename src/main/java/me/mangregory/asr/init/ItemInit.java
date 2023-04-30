package me.mangregory.asr.init;

import me.mangregory.asr.AsgardShieldReloaded;
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
            () -> new SwordItem(Tiers.ALTDIAMOND, 1, -2.4f, PROPERTIES));

    public static final RegistryObject<GiantSwordItem> IRON_GIANT_SWORD = ITEMS.register("iron_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTIRON, 1, -2.4f, PROPERTIES));

    public static final RegistryObject<GiantSwordItem> GOLDEN_GIANT_SWORD = ITEMS.register("golden_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTGOLD, 1, -2.4f, PROPERTIES));;

    public static final RegistryObject<GiantSwordItem> STONE_GIANT_SWORD = ITEMS.register("stone_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTSTONE, 1, -2.4f, PROPERTIES));

    public static final RegistryObject<GiantSwordItem> WOODEN_GIANT_SWORD = ITEMS.register("wooden_giant_sword",
            () -> new GiantSwordItem(Tiers.ALTWOOD, 1, -2.4f, PROPERTIES));

    public static final RegistryObject<GiantSwordItem> ENDER_GIANT_SWORD = ITEMS.register("ender_giant_sword",
            () -> new GiantSwordItem(Tiers.ENDERTIER, 1, -2f, PROPERTIES));
    public static class Tiers {
        public static final Tier ALTDIAMOND = new ForgeTier(3, 3100, -2.4f, 6.5f, 15, null, () -> Ingredient.of(Items.DIAMOND));
        public static final Tier ALTIRON = new ForgeTier(2, 410, 6f, 5.5f, 10, null, () -> Ingredient.of(Items.IRON_INGOT));
        public static final Tier ALTGOLD = new ForgeTier(0, 60, 12.0f, 3.5f, 22, null, () -> Ingredient.of(Items.GOLD_INGOT));
        public static final Tier ALTSTONE = new ForgeTier(1, 215, 4f, 4.5f, 5, null, () -> Ingredient.of(Items.COBBLESTONE));
        public static final Tier ALTWOOD = new ForgeTier(0, 88, 2f, 3f, 7, null, () -> Ingredient.of(ItemTags.PLANKS));
        public static final Tier ENDERTIER = new ForgeTier(3, 4200, -2.4f, 8f, 20, null, () -> Ingredient.of(Items.NETHERITE_INGOT));
    }
}
