package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;
import com.mangregory.asgardshieldreloaded.util.ShieldUtil;
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

// TODO: Need to add skull shield functionality
@Mod.EventBusSubscriber
public class EventSkullShieldBlock
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
        double confusionChance;

        // Skull Shield: Has a 15% chance to confuse targets into attacking other hostiles
        // Gilded Skull Shield: Has a 30% chance to confuse targets into attacking other hostiles
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "skull_shield":
                confusionChance = 0.15;
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_skull_shield":
                confusionChance = 0.3;
                break;
            default:
                return;
        }

        // Weakness: Brittle Bones
        // There is a 10% chance for durability loss to be increased (3x) when blocking
        if (RandomUtil.chance(0.1D))
        {
            event.setShieldDamage(event.getShieldDamage() * 3.0F);
        }

        if (attacker instanceof EntityLiving && attacker.isNonBoss() && RandomUtil.chance(confusionChance))
        {
            wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.PLAYERS, 0.4F, 0.2F);
            ShieldUtil.confuseEnemy((EntityLiving) attacker, wielder, 30 * 20);
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}