package com.mangregory.asgardshieldreloaded.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nullable;

public class ASRDamageSource extends EntityDamageSource {
    public static final DamageSource DIAMOND_REFLECT = new DamageSource("asr_diamond_reflect").setDamageBypassesArmor().setDamageIsAbsolute();

    public ASRDamageSource(String damageType, @Nullable Entity damageSourceEntity) {
        super(damageType, damageSourceEntity);
        this.damageType = damageType;
    }
}
