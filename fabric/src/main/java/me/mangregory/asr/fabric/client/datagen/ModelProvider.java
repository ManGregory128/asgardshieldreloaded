package me.mangregory.asr.fabric.client.datagen;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.mangregory.asr.items.init.AsgardShieldItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Writes plain item model JSON (assets/<ns>/models/item/<id>.json) directly.
 * 1.21.4 made ItemModelGenerators' helpers private, so raw JSON is the reliable path;
 * the blocking-model switch itself lives in ItemDefinitionsProvider (items/<id>.json),
 * because model "overrides" were removed in 1.21.2.
 */
public class ModelProvider implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final PackOutput.PathProvider modelsPath;

    public ModelProvider(PackOutput output) {
        this.modelsPath = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        Map<ResourceLocation, JsonObject> toWrite = new LinkedHashMap<>();

        for (var sup : AsgardShieldItems.GIANT_SWORDS) {
            Item item = sup.get();
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
            ResourceLocation baseModelId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
            toWrite.put(baseModelId, baseSwordModelJson(texture));
        }

        for (var sup : AsgardShieldItems.ASGARD_SHIELDS) {
            Item item = sup.get();
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
            ResourceLocation baseModelId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
            ResourceLocation blockingModelId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath() + "_blocking");
            toWrite.put(baseModelId, baseShieldModelJson(texture));
            toWrite.put(blockingModelId, blockingShieldModelJson(texture));
        }

        CompletableFuture<?>[] futures = toWrite.entrySet().stream()
                .map(e -> writeJsonAsync(cachedOutput, e.getKey(), e.getValue()))
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures);
    }

    private CompletableFuture<?> writeJsonAsync(CachedOutput cachedOutput, ResourceLocation id, JsonObject json) {
        Path path = modelsPath.json(id); // assets/<ns>/models/<path>.json (id already starts with item/)
        byte[] bytes = (GSON.toJson(json) + "\n").getBytes(StandardCharsets.UTF_8);
        var hash = Hashing.sha1().hashBytes(bytes);

        return CompletableFuture.runAsync(() -> {
            try {
                cachedOutput.writeIfNeeded(path, bytes, hash);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public String getName() {
        return "AsgardShieldReloaded Item Models";
    }

    private static JsonObject baseShieldModelJson(ResourceLocation layer0) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0.toString());
        root.add("textures", textures);

        root.add("display", shieldDisplayDefault());
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

    private static JsonObject baseSwordModelJson(ResourceLocation layer0) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:item/handheld");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0.toString());
        root.add("textures", textures);
        return root;
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
