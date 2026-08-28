package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.util.AsgardShieldTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagsProvider extends FabricTagProvider<DamageType> {

    public DamageTypeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.DAMAGE_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(AsgardShieldTags.WOODEN_SHIELD_BYPASSED_BY)
                .forceAddTag(DamageTypeTags.BYPASSES_SHIELD)
                .add(DamageTypes.ON_FIRE, DamageTypes.FIREBALL, DamageTypes.UNATTRIBUTED_FIREBALL);
    }
}
