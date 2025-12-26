package me.mangregory.asr.fabric.client;

import me.mangregory.asr.fabric.client.datagen.EnUsLangProvider;
import me.mangregory.asr.fabric.client.datagen.ItemDefinitionsProvider;
import me.mangregory.asr.fabric.client.datagen.ModelProvider;
import me.mangregory.asr.fabric.client.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AsgardShieldReloadedDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(EnUsLangProvider::new);
        pack.addProvider(ModelProvider::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<ItemDefinitionsProvider>) ItemDefinitionsProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
