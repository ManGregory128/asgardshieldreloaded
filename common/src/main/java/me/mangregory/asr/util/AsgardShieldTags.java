package me.mangregory.asr.util;

import me.mangregory.asr.AsgardShieldReloaded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public class AsgardShieldTags {
    public static final TagKey<DamageType> WOODEN_SHIELD_BYPASSED_BY = TagKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, "wooden_shield_bypassed_by"));
}
