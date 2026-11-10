package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventStoneShieldBlock
{
    @SubscribeEvent
    public static void onShieldBlock(LivingBlockDamageEvent event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) return;
        EntityPlayer wielder = (EntityPlayer) event.getEntity();
        DamageSource source = event.getSource();
        Item shield = event.getShield().getItem();
        String name = Objects.requireNonNull(shield.getRegistryName()).toString();

        // Stone Shield: Shield damage from fire is halved
        // Gilded Stone Shield: Shield damage from fire is always at 0
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "stone_shield":
                if (source.isFireDamage())
                {
                    event.setShieldDamage(event.getShieldDamage() / 2.0F);
                }
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_stone_shield":
                if (source.isFireDamage())
                {
                    event.setShieldDamage(0.0F);
                }
                break;
            default:
                return;
        }

        if (source.isExplosion())
        {
            //wielder.stopActiveHand();
            //wielder.world.setEntityState(wielder, (byte) 30);
            event.setShieldDamage(event.getShieldDamage() * 10.0F);
            //event.setShieldCooldown(20 * 20);
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_NOTE_HAT, SoundCategory.PLAYERS, 1.2F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}