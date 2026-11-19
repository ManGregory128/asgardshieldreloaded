package me.mangregory.asr.neoforge.compat.jei;

import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Collections;
import java.util.List;

/**
 * Represents an anvil repair recipe for JEI display.
 * Implements IJeiAnvilRecipe to integrate with JEI's built-in Anvil category.
 */
public class AnvilRepairRecipe implements IJeiAnvilRecipe {
    private final ResourceLocation uid;
    private final ItemStack leftInput;
    private final List<ItemStack> rightInputs;
    private final ItemStack output;

    public AnvilRepairRecipe(ResourceLocation uid, ItemStack damagedItem, ItemStack repairedItem, Ingredient repairMaterial) {
        this(uid, damagedItem, repairedItem,
                // 1.21.4 removed Ingredient#getItems; items() yields Holder<Item> now
                repairMaterial.items().map(holder -> new ItemStack(holder.value())).toList());
    }

    public AnvilRepairRecipe(ResourceLocation uid, ItemStack damagedItem, ItemStack repairedItem, List<ItemStack> rightInputs) {
        this.uid = uid;
        this.leftInput = damagedItem;
        this.rightInputs = rightInputs;
        this.output = repairedItem;
    }

    @Override
    public List<ItemStack> getLeftInputs() {
        return Collections.singletonList(leftInput);
    }

    @Override
    public List<ItemStack> getRightInputs() {
        return rightInputs;
    }

    @Override
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(output);
    }

    @Override
    public ResourceLocation getUid() {
        return uid;
    }
}
