package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.ASRDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

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
        float reflectedDamage;

        // Diamond Shield: Reflects 3 damage back to the attacker
        // Gilded Diamond Shield: Reflects 6 damage back to the attacker
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "diamond_shield":
                reflectedDamage = 3.0F;
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_diamond_shield":
                reflectedDamage = 6.0F;
                break;
            default:
                return;
        }

        if (attacker instanceof EntityLiving)
        {
            event.setShieldDamage(event.getShieldDamage() + (reflectedDamage / 2));
            attacker.hurtResistantTime = 0;
            attacker.attackEntityFrom(ASRDamageSource.DIAMOND_REFLECT, reflectedDamage);
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 1.0F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}