package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.util.AsgardShieldTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagsProvider extends FabricTagsProvider<DamageType> {

    public DamageTypeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.DAMAGE_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.tag(AsgardShieldTags.WOODEN_SHIELD_BYPASSED_BY).forceAddTag(DamageTypeTags.BYPASSES_SHIELD)
                .add((new ResourceKey[]{DamageTypes.ON_FIRE, DamageTypes.FIREBALL, DamageTypes.UNATTRIBUTED_FIREBALL}));
    }
}
