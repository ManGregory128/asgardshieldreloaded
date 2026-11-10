package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

// TODO: Need to add Quartz shield weakness because apparently that wasn't implemented
@Mod.EventBusSubscriber
public class EventQuartzShieldBlock
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
        double fireballChance;

        // Nether Quartz Shield: 25% chance to obtain the blocked fireball
        // Gilded Nether Quartz Shield: 50% chance to obtain the blocked fireball
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "netherquartz_shield":
                fireballChance = 0.25D;
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_netherquartz_shield":
                fireballChance = 0.5D;
                break;
            default:
                return;
        }

        // Weakness: Fatigue
        // There is a 10% chance for the shield to break for 5 seconds due to its heavy weight
        if (RandomUtil.chance(0.1D))
        {
            event.setShieldCooldown(5 * 20);
        }

        if (projectile instanceof EntityFireball && RandomUtil.chance(fireballChance))
        {
            EntityFireball fireball = (EntityFireball) projectile;
            wielder.getEntityWorld().playSound(null, projectile.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 0.8F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
            fireball.setDead();
            wielder.inventory.addItemStackToInventory(new ItemStack(Items.FIRE_CHARGE));
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.PLAYERS, 1.2F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}