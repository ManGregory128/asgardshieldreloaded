package me.mangregory.asr.fabric.client.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        // no block models to generate
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {
        AsgardShieldItems.GIANT_SWORDS.forEach(sword ->
                swordModels(gen, sword.get()));
        AsgardShieldItems.ASGARD_SHIELDS.forEach(shield ->
                shieldModels(gen, shield.get()));
    }

    private static void swordModels(ItemModelGenerators gen, Item sword) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(sword);
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());

        ResourceLocation baseModelId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
        ResourceLocation blockingModelId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath() + "_blocking");

        gen.output.accept(baseModelId, () -> baseSwordModelJson(texture, blockingModelId));
        gen.output.accept(blockingModelId, () -> blockingSwordModelJson(texture));
    }

    private static void shieldModels(ItemModelGenerators gen, Item shield) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(shield);
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());

        ResourceLocation baseModelId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
        ResourceLocation blockingModelId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath() + "_blocking");

        gen.output.accept(baseModelId, () -> baseShieldModelJson(texture, blockingModelId));
        gen.output.accept(blockingModelId, () -> blockingShieldModelJson(texture));
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
