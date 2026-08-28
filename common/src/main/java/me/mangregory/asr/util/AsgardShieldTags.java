package me.mangregory.asr.util;

import me.mangregory.asr.AsgardShieldReloaded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public class AsgardShieldTags {
    public static final TagKey<DamageType> WOODEN_SHIELD_BYPASSED_BY = TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, "wooden_shield_bypassed_by"));
}
