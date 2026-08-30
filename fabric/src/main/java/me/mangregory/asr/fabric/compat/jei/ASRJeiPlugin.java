package me.mangregory.asr.fabric.compat.jei;

import dev.architectury.registry.registries.RegistrySupplier;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.registration.IRecipeRegistration;
import me.mangregory.asr.items.init.AsgardShieldItems;
import me.mangregory.asr.items.init.AsgardShieldToolMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * JEI plugin for Asgard Shield Reloaded.
 * Adds anvil repair recipes for giant swords and asgard shields to JEI's built-in Anvil category.
 */
@JeiPlugin
public class ASRJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath("asr", "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<IJeiAnvilRecipe> recipes = new ArrayList<>();
        
        // Add repair recipes for giant swords
        addGiantSwordRepairRecipes(recipes);
        
        // Add repair recipes for asgard shields
        addAsgardShieldRepairRecipes(recipes);
        
        // Add same-item repair recipes (using two damaged items of the same type)
        addSameItemRepairRecipes(recipes);
        
        registration.addRecipes(RecipeTypes.ANVIL, recipes);
    }

    private void addGiantSwordRepairRecipes(List<IJeiAnvilRecipe> recipes) {
        // Diamond Giant Sword - uses diamonds
        addRepairRecipe(recipes, AsgardShieldItems.DIAMOND_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ALTDIAMOND.getRepairIngredient());
        
        // Iron Giant Sword - uses iron ingots
        addRepairRecipe(recipes, AsgardShieldItems.IRON_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ALTIRON.getRepairIngredient());
        
        // Golden Giant Sword - uses gold ingots
        addRepairRecipe(recipes, AsgardShieldItems.GOLDEN_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ALTGOLD.getRepairIngredient());
        
        // Copper Giant Sword - uses copper ingots
        addRepairRecipe(recipes, AsgardShieldItems.COPPER_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ALTCOPPER.getRepairIngredient());
        
        // Netherite Giant Sword - uses netherite ingots
        addRepairRecipe(recipes, AsgardShieldItems.NETHERITE_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ALTNETHERITE.getRepairIngredient());
        
        // Stone Giant Sword - uses stone tool materials
        addRepairRecipe(recipes, AsgardShieldItems.STONE_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ALTSTONE.getRepairIngredient());
        
        // Wooden Giant Sword - uses planks
        addRepairRecipe(recipes, AsgardShieldItems.WOODEN_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ALTWOOD.getRepairIngredient());
        
        // Ender Giant Sword - uses obsidian
        addRepairRecipe(recipes, AsgardShieldItems.ENDER_GIANT_SWORD, 
                        AsgardShieldToolMaterials.ENDTOOLMATERIAL.getRepairIngredient());
        
        // Netherquartz Giant Sword - uses quartz
        addRepairRecipe(recipes, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD, 
                        AsgardShieldToolMaterials.NQ_TOOLMATERIAL.getRepairIngredient());
        
        // Skull Giant Sword - uses bones
        addRepairRecipe(recipes, AsgardShieldItems.SKULL_GIANT_SWORD, 
                        AsgardShieldToolMaterials.SKULL_TOOLMATERIAL.getRepairIngredient());
    }

    private void addAsgardShieldRepairRecipes(List<IJeiAnvilRecipe> recipes) {
        // For shields, we use their material as repair ingredient
        // Diamond shields - use diamonds
        addShieldRepairRecipe(recipes, AsgardShieldItems.DIAMOND_SHIELD, Items.DIAMOND);
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_DIAMOND_SHIELD, Items.DIAMOND);
        
        // Ender shields - use obsidian
        addShieldRepairRecipe(recipes, AsgardShieldItems.ENDER_SHIELD, Items.OBSIDIAN);
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_ENDER_SHIELD, Items.OBSIDIAN);
        
        // Netherquartz shields - use quartz
        addShieldRepairRecipe(recipes, AsgardShieldItems.NETHERQUARTZ_SHIELD, Items.QUARTZ);
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD, Items.QUARTZ);
        
        // Skull shields - use bones
        addShieldRepairRecipe(recipes, AsgardShieldItems.SKULL_SHIELD, Items.BONE);
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_SKULL_SHIELD, Items.BONE);
        
        // Iron shields - use iron ingots
        addShieldRepairRecipe(recipes, AsgardShieldItems.IRON_SHIELD, Items.IRON_INGOT);
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_IRON_SHIELD, Items.IRON_INGOT);
        
        // Stone shields - use stone tool materials tag
        addShieldRepairRecipe(recipes, AsgardShieldItems.STONE_SHIELD, Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_STONE_SHIELD, Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));
        
        // Copper shields - use copper ingots
        addShieldRepairRecipe(recipes, AsgardShieldItems.COPPER_SHIELD, Items.COPPER_INGOT);
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_COPPER_SHIELD, Items.COPPER_INGOT);
        
        // Netherite shields - use netherite ingots
        addShieldRepairRecipe(recipes, AsgardShieldItems.NETHERITE_SHIELD, Items.NETHERITE_INGOT);
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_NETHERITE_SHIELD, Items.NETHERITE_INGOT);
        
        // Wooden shields - use planks tag
        addShieldRepairRecipe(recipes, AsgardShieldItems.WOODEN_SHIELD, Ingredient.of(ItemTags.PLANKS));
        addShieldRepairRecipe(recipes, AsgardShieldItems.GILDED_WOODEN_SHIELD, Ingredient.of(ItemTags.PLANKS));
    }

    private void addSameItemRepairRecipes(List<IJeiAnvilRecipe> recipes) {
        // Add recipes for repairing items with two of the same damaged item
        // Giant Swords
        addSameItemRepairRecipe(recipes, AsgardShieldItems.DIAMOND_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.IRON_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GOLDEN_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.COPPER_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.NETHERITE_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.STONE_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.WOODEN_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.ENDER_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.SKULL_GIANT_SWORD);
        
        // Asgard Shields
        addSameItemRepairRecipe(recipes, AsgardShieldItems.DIAMOND_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_DIAMOND_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.ENDER_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_ENDER_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.NETHERQUARTZ_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.SKULL_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_SKULL_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.IRON_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_IRON_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.STONE_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_STONE_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.COPPER_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_COPPER_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.NETHERITE_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_NETHERITE_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.WOODEN_SHIELD);
        addSameItemRepairRecipe(recipes, AsgardShieldItems.GILDED_WOODEN_SHIELD);
    }

    private void addRepairRecipe(List<IJeiAnvilRecipe> recipes, 
                                   RegistrySupplier<Item> itemSupplier,
                                   Ingredient repairMaterial) {
        ItemStack damaged = createDamagedItem(itemSupplier);
        ItemStack repaired = new ItemStack(itemSupplier.get());
        
        ResourceLocation uid = ResourceLocation.fromNamespaceAndPath(
                "asr", 
                "anvil_repair/" + itemSupplier.get().getDescriptionId()
        );
        
        recipes.add(new AnvilRepairRecipe(uid, damaged, repaired, repairMaterial));
    }

    private void addShieldRepairRecipe(List<IJeiAnvilRecipe> recipes,
                                         RegistrySupplier<Item> itemSupplier,
                                         Item repairItem) {
        addShieldRepairRecipe(recipes, itemSupplier, Ingredient.of(repairItem));
    }

    private void addShieldRepairRecipe(List<IJeiAnvilRecipe> recipes,
                                         RegistrySupplier<Item> itemSupplier,
                                         Ingredient repairMaterial) {
        ItemStack damaged = createDamagedItem(itemSupplier);
        ItemStack repaired = new ItemStack(itemSupplier.get());
        
        ResourceLocation uid = ResourceLocation.fromNamespaceAndPath(
                "asr", 
                "anvil_repair/" + itemSupplier.get().getDescriptionId()
        );
        
        recipes.add(new AnvilRepairRecipe(uid, damaged, repaired, repairMaterial));
    }

    private void addSameItemRepairRecipe(List<IJeiAnvilRecipe> recipes, RegistrySupplier<Item> itemSupplier) {
        ItemStack damaged = createDamagedItem(itemSupplier);
        ItemStack repaired = new ItemStack(itemSupplier.get());
        
        ResourceLocation uid = ResourceLocation.fromNamespaceAndPath(
                "asr", 
                "anvil_repair/same_item/" + itemSupplier.get().getDescriptionId()
        );
        
        // For same-item repair, both left and right inputs are the same damaged item
        recipes.add(new AnvilRepairRecipe(uid, damaged, repaired, Ingredient.of(damaged)));
    }

    private static ItemStack createDamagedItem(RegistrySupplier<Item> itemSupplier) {
        ItemStack damaged = new ItemStack(itemSupplier.get());
        // Set damage to 75% of max (so item has 25% durability remaining)
        // This is more representative than 1 durability which was too extreme
        damaged.setDamageValue((int) (damaged.getMaxDamage() * 0.75f));
        return damaged;
    }
}
