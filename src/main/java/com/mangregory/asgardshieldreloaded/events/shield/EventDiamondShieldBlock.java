package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

// TODO: Need to add diamond shield functionality
@Mod.EventBusSubscriber
public class EventDiamondShieldBlock
{
    @SubscribeEvent
    public static void onShieldBlock(LivingBlockDamageEvent event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) return;
        EntityPlayer wielder = (EntityPlayer) event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();
        Item shield = event.getShield().getItem();
        String name = Objects.requireNonNull(shield.getRegistryName()).toString();

        // Diamond Shield: TODO
        // Gilded Diamond Shield: TODO
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "diamond_shield":
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_diamond_shield":
                break;
            default:
                return;
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 1.0F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}