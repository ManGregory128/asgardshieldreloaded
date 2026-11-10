package me.mangregory.asr.fabric.client.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// 1.20.1: ItemModelGenerators.output is private, so write the model JSONs directly as a DataProvider
public class ModelProvider implements DataProvider {
    private final FabricDataOutput output;

    public ModelProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        AsgardShieldItems.GIANT_SWORDS.forEach(sword ->
                modelFiles(cache, futures, sword.get(), true));
        AsgardShieldItems.ASGARD_SHIELDS.forEach(shield ->
                modelFiles(cache, futures, shield.get(), false));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private void modelFiles(CachedOutput cache, List<CompletableFuture<?>> futures, Item item, boolean sword) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        ResourceLocation texture = new ResourceLocation(itemId.getNamespace(), "item/" + itemId.getPath());
        ResourceLocation blockingModelId = new ResourceLocation(itemId.getNamespace(), "item/" + itemId.getPath() + "_blocking");

        Path basePath = modelPath(itemId, false);
        Path blockingPath = modelPath(itemId, true);
        futures.add(DataProvider.saveStable(cache, sword ? baseSwordModelJson(texture, blockingModelId) : baseShieldModelJson(texture, blockingModelId), basePath));
        futures.add(DataProvider.saveStable(cache, sword ? blockingSwordModelJson(texture) : blockingShieldModelJson(texture), blockingPath));
    }

    private Path modelPath(ResourceLocation itemId, boolean blocking) {
        String name = itemId.getPath() + (blocking ? "_blocking" : "");
        return output.getOutputFolder().resolve("assets/" + itemId.getNamespace() + "/models/item/" + name + ".json");
    }

    @Override
    public String getName() {
        return "asr models";
    }

    private static JsonObject baseShieldModelJson(ResourceLocation layer0, ResourceLocation blockingModelId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0.toString());
        root.add("textures", textures);

        root.add("display", shieldDisplayDefault());

        // Add blocking override predicate
        JsonArray overrides = new JsonArray();
        JsonObject blockingOverride = new JsonObject();
        JsonObject predicate = new JsonObject();
        predicate.addProperty("blocking", 1);
        blockingOverride.add("predicate", predicate);
        blockingOverride.addProperty("model", blockingModelId.toString());
        overrides.add(blockingOverride);
        root.add("overrides", overrides);

        return root;
    }

    private static JsonObject blockingShieldModelJson(ResourceLocation layer0) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0.toString());
        root.add("textures", textures);

        root.add("display", shieldDisplayBlocking());
        return root;
    }

    private static JsonObject shieldDisplayDefault() {
        JsonObject display = new JsonObject();

        JsonObject tpr = new JsonObject();
        tpr.add("rotation", arr(-80.97, -88.52, -81.7));
        tpr.add("translation", arr(2.5, -2.25, 1.75));
        display.add("thirdperson_righthand", tpr);

        JsonObject tpl = new JsonObject();
        tpl.add("rotation", arr(-80.97, -88.52, -81.7));
        tpl.add("translation", arr(2.5, -2.25, 1.75));
        display.add("thirdperson_lefthand", tpl);

        JsonObject fpr = new JsonObject();
        fpr.add("rotation", arr(0, -30, 0));
        fpr.add("translation", arr(1, -0.5, 1));
        fpr.add("scale", arr(0.7, 0.7, 0.7));
        display.add("firstperson_righthand", fpr);

        JsonObject fpl = new JsonObject();
        fpl.add("rotation", arr(0, -30, 0));
        fpl.add("translation", arr(1, -0.5, 1));
        fpl.add("scale", arr(0.7, 0.7, 0.7));
        display.add("firstperson_lefthand", fpl);

        JsonObject ground = new JsonObject();
        ground.add("translation", arr(0, 2, 0));
        ground.add("scale", arr(0.7, 0.7, 0.7));
        display.add("ground", ground);

        return display;
    }

    private static JsonObject baseSwordModelJson(ResourceLocation layer0, ResourceLocation blockingModelId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:item/handheld");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0.toString());
        root.add("textures", textures);

        JsonArray overrides = new JsonArray();
        JsonObject blockingOverride = new JsonObject();
        JsonObject predicate = new JsonObject();
        predicate.addProperty("blocking", 1);
        blockingOverride.add("predicate", predicate);
        blockingOverride.addProperty("model", blockingModelId.toString());
        overrides.add(blockingOverride);
        root.add("overrides", overrides);

        return root;
    }

    private static JsonObject blockingSwordModelJson(ResourceLocation layer0) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:item/handheld");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0.toString());
        root.add("textures", textures);

        root.add("display", swordDisplayBlocking());
        return root;
    }

    private static JsonObject swordDisplayBlocking() {
        JsonObject display = new JsonObject();

        JsonObject tpr = new JsonObject();
        tpr.add("rotation", arr(30, -27, 90));
        tpr.add("translation", arr(-5.5, 2, 2.5));
        tpr.add("scale", arr(0.8, 0.8, 0.8));
        display.add("thirdperson_righthand", tpr);

        JsonObject tpl = new JsonObject();
        tpl.add("rotation", arr(30, -27, 0));
        tpl.add("translation", arr(-5.5, 2, 2.5));
        tpl.add("scale", arr(0.8, 0.8, 0.8));
        display.add("thirdperson_lefthand", tpl);

        JsonObject fpr = new JsonObject();
        fpr.add("rotation", arr(0, 180, 0));
        fpr.add("translation", arr(-1, 3, 1));
        fpr.add("scale", arr(0.7, 0.7, 0.7));
        display.add("firstperson_righthand", fpr);

        JsonObject fpl = new JsonObject();
        fpl.add("translation", arr(-1, 3, 1));
        fpl.add("scale", arr(0.7, 0.7, 0.7));
        display.add("firstperson_lefthand", fpl);

        return display;
    }

    private static JsonObject shieldDisplayBlocking() {
        JsonObject display = new JsonObject();

        JsonObject tpr = new JsonObject();
        tpr.add("rotation", arr(63.7, -0.03, 0.78));
        tpr.add("translation", arr(0, 0.5, 1));
        display.add("thirdperson_righthand", tpr);

        JsonObject tpl = new JsonObject();
        tpl.add("rotation", arr(63.7, -0.03, 0.78));
        tpl.add("translation", arr(0, 0.5, 1));
        display.add("thirdperson_lefthand", tpl);

        JsonObject fpr = new JsonObject();
        fpr.add("translation", arr(-3, 3, 1));
        fpr.add("scale", arr(0.7, 0.7, 0.7));
        display.add("firstperson_righthand", fpr);

        JsonObject fpl = new JsonObject();
        fpl.add("translation", arr(-3, 3, 1));
        fpl.add("scale", arr(0.7, 0.7, 0.7));
        display.add("firstperson_lefthand", fpl);

        return display;
    }

    private static JsonArray arr(double x, double y, double z) {
        JsonArray a = new JsonArray();
        a.add(x);
        a.add(y);
        a.add(z);
        return a;
    }
}
