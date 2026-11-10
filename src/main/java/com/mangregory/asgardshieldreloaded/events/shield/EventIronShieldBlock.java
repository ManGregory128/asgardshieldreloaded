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
public class EventIronShieldBlock
{
    @SubscribeEvent
    public static void onShieldBlock(LivingBlockDamageEvent event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) return;
        EntityPlayer wielder = (EntityPlayer) event.getEntity();
        DamageSource source = event.getSource();
        Item shield = event.getShield().getItem();
        String name = Objects.requireNonNull(shield.getRegistryName()).toString();

        // Iron Shield: Shield damage from explosions is halved
        // Gilded Iron Shield: Shield damage from explosions is always at 0
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "iron_shield":
                if (source.isExplosion()) {
                    event.setShieldDamage(event.getShieldDamage() / 2.0F);
                }
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_iron_shield":
                if (source.isExplosion()) {
                    event.setShieldDamage(0);
                }
                break;
            default:
                return;
        }

        // Weakness: Water
        // Durability taken is increased (2x) while in water or under rain
        if (wielder.isInWater() || wielder.world.isRainingAt(wielder.getPosition()))
        {
            event.setShieldDamage(event.getShieldDamage() * 2.0F);
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}