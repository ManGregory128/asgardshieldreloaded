package me.mangregory.asr.data;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.data.recipe.MainModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = event.getGenerator().getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

            generator.addProvider(true, new MainModRecipeProvider(generator, event.getLookupProvider()));
        }
        catch(RuntimeException e) {
            AsgardShieldReloaded.logger.error("Failed to generate data", e);
        }
    }
}
