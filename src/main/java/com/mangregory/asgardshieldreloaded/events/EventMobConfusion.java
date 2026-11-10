package com.mangregory.asgardshieldreloaded.events;

import com.mangregory.asgardshieldreloaded.util.ShieldUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventMobConfusion
{
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (!(event.getEntityLiving() instanceof EntityLiving)) return;
        EntityLiving entity = (EntityLiving) event.getEntityLiving();
        if (entity.world.isRemote) return;
        ShieldUtil.updateConfusion(entity);
    }
}
