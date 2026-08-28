package me.mangregory.asr.fabric.client.datagen;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.mangregory.asr.AsgardShieldReloaded;
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

public final class ItemDefinitionsProvider implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final PackOutput.PathProvider itemsPath;

    public ItemDefinitionsProvider(PackOutput output) {
        this.itemsPath = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        Map<ResourceLocation, JsonObject> toWrite = new LinkedHashMap<>();

        for (var sup : AsgardShieldItems.GIANT_SWORDS) {
            Item item = sup.get();
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            toWrite.put(id, swordItemDefinition(id));
        }

        for (var sup : AsgardShieldItems.ASGARD_SHIELDS) {
            Item item = sup.get();
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            toWrite.put(id, shieldItemDefinition(id));
        }

        CompletableFuture<?>[] futures = toWrite.entrySet().stream()
                .map(e -> writeJsonAsync(cachedOutput, e.getKey(), e.getValue()))
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures);
    }

    private CompletableFuture<?> writeJsonAsync(CachedOutput cachedOutput, ResourceLocation id, JsonObject json) {
        Path path = itemsPath.json(id); // assets/<ns>/items/<path>.json
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
        return "AsgardShieldReloaded Item Definitions";
    }

    private static JsonObject swordItemDefinition(ResourceLocation itemId) {
        JsonObject root = new JsonObject();

        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:model");
        model.addProperty("model", modelId(itemId));

        root.add("model", model);
        return root;
    }

    private static JsonObject shieldItemDefinition(ResourceLocation itemId) {
        JsonObject root = new JsonObject();

        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:condition");
        model.addProperty("property", "minecraft:using_item");

        JsonObject onTrue = new JsonObject();
        onTrue.addProperty("type", "minecraft:model");
        onTrue.addProperty("model", modelIdBlocking(itemId));

        JsonObject onFalse = new JsonObject();
        onFalse.addProperty("type", "minecraft:model");
        onFalse.addProperty("model", modelId(itemId));

        model.add("on_true", onTrue);
        model.add("on_false", onFalse);

        root.add("model", model);
        return root;
    }

    private static String modelId(ResourceLocation itemId) {
        return AsgardShieldReloaded.MOD_ID + ":item/" + itemId.getPath();
    }

    private static String modelIdBlocking(ResourceLocation itemId) {
        return AsgardShieldReloaded.MOD_ID + ":item/" + itemId.getPath() + "_blocking";
    }
}
