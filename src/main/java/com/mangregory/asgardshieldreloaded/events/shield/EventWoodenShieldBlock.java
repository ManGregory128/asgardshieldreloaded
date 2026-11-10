package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;
import com.mangregory.asgardshieldreloaded.util.ShieldUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventWoodenShieldBlock
{
    @SubscribeEvent
    public static void onShieldBlock(LivingBlockDamageEvent event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) return;
        EntityPlayer wielder = (EntityPlayer) event.getEntity();
        DamageSource source = event.getSource();
        Entity projectile = source.getImmediateSource();
        Item shield = event.getShield().getItem();
        String name = Objects.requireNonNull(shield.getRegistryName()).toString();
        double arrowChance;

        // Wooden Shield: 25% chance to obtain the blocked arrow
        // Gilded Wooden Shield: 50% chance to obtain the blocked arrow
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "wooden_shield":
                arrowChance = 0.25D;
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_wooden_shield":
                arrowChance = 0.5D;
                break;
            default:
                return;
        }

        // Weakness: Fire Damage
        // Durability taken is increased (4x) while blocking against fire sources, additionally the shield is disabled for 5 seconds
        if (source.isFireDamage())
        {
            event.setShieldDamage(event.getShieldDamage() * 4.0F);
            event.setShieldCooldown(5 * 20);
        }

        if (projectile instanceof EntityArrow)
        {
            EntityArrow arrow = (EntityArrow) projectile;

            if (arrow.isBurning())
            {
                event.setShieldDamage(event.getShieldDamage() * 4.0F);
                event.setShieldCooldown(5 * 20);
            }

            if (RandomUtil.chance(arrowChance))
            {
                ShieldUtil.dropArrowAtPlayer(arrow, wielder);
            }
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, SoundCategory.PLAYERS, 0.6F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}