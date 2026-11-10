package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;
import com.mangregory.asgardshieldreloaded.util.ShieldUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventEnderShieldBlock
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
        double teleportChance;
        float teleportKnockback;

        // Ender Shield: 20% chance to teleport attackers away
        // Gilded Ender Shield: 40% chance to teleport attackers away
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "ender_shield":
                teleportChance = 0.2D;
                teleportKnockback = 1.5F;
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_ender_shield":
                teleportChance = 0.4D;
                teleportKnockback = 2.0F;
                break;
            default:
                return;
        }

        // TODO: Check mobs from other mods
        // Weakness: End Tech
        // Blocking won't work against ender type mobs
        if (attacker instanceof EntityEnderman || attacker instanceof EntityEndermite || attacker instanceof EntityDragon)
        {
            event.setResult(Event.Result.DENY);
        } else if (attacker instanceof EntityLivingBase && RandomUtil.chance(teleportChance))
        {
            ShieldUtil.teleportEnemy(attacker, teleportKnockback);
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}