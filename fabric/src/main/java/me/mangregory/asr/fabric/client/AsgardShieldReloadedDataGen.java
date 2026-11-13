package me.mangregory.asr.fabric.client;

import me.mangregory.asr.fabric.client.datagen.DeDeLangProvider;
import me.mangregory.asr.fabric.client.datagen.ElGrLangProvider;
import me.mangregory.asr.fabric.client.datagen.EnUsLangProvider;
import me.mangregory.asr.fabric.client.datagen.ItemTagsProvider;
import me.mangregory.asr.fabric.client.datagen.ModelProvider;
import me.mangregory.asr.fabric.client.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AsgardShieldReloadedDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.addProvider(EnUsLangProvider::new);
        generator.addProvider(DeDeLangProvider::new);
        generator.addProvider(ElGrLangProvider::new);
        generator.addProvider(ModelProvider::new);
        generator.addProvider(ModRecipeProvider::new);
        generator.addProvider(ItemTagsProvider::new);
    }
}
