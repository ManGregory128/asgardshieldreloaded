package me.mangregory.asr.data.recipe;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.data.recipe.provider.NormalCraftingTableRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class MainModRecipeProvider extends RecipeProvider {
    protected final DataGenerator generator;
    protected final CompletableFuture<HolderLookup.Provider> lookupProvider;

    public MainModRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(generator.getPackOutput(), lookupProvider);
        this.generator = generator;
        this.lookupProvider = lookupProvider;
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        new NormalCraftingTableRecipeProvider(generator, lookupProvider, pRecipeOutput).build();
    }

    public ResourceLocation getModId(String path) {
        return ResourceLocation.fromNamespaceAndPath(AsgardShieldReloaded.MODID, path);
    }
}
