package com.mangregory.asgardshieldreloaded.events;

import com.mangregory.asgardshieldreloaded.util.ASRDamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventDamageSound {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onHurtEvent(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();

        if (damageSource == ASRDamageSource.DIAMOND_REFLECT) {
            entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_ILLAGER_PREPARE_MIRROR, SoundCategory.NEUTRAL, 0.8F, 2.0F);
        }
    }
}
