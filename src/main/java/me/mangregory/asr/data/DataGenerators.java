package me.mangregory.asr.data;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.data.lang.ModEnLangProvider;
import me.mangregory.asr.data.recipe.MainModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = event.getGenerator().getPackOutput();

            generator.addProvider(true, new MainModRecipeProvider(generator, event.getLookupProvider()));
            generator.addProvider(true, new ModEnLangProvider(output));
        }
        catch(RuntimeException e) {
            AsgardShieldReloaded.logger.error("Failed to generate data", e);
        }
    }
}
