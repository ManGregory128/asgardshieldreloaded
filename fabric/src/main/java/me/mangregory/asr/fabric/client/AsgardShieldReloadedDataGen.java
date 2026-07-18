package me.mangregory.asr.fabric.client;

import me.mangregory.asr.fabric.client.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AsgardShieldReloadedDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(EnUsLangProvider::new);
        pack.addProvider(ModelProvider::new);
        pack.addProvider(ItemTagsProvider::new);
        pack.addProvider(DamageTypeTagsProvider::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<ItemDefinitionsProvider>) ItemDefinitionsProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
