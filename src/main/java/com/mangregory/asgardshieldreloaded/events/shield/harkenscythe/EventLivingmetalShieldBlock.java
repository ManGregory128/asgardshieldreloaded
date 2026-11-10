package com.mangregory.asgardshieldreloaded.events.shield.harkenscythe;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;
import com.mangregory.asgardshieldreloaded.util.ShieldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventLivingmetalShieldBlock
{
    @SubscribeEvent
    public static void onShieldBlock(LivingBlockDamageEvent event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) return;
        EntityPlayer wielder = (EntityPlayer) event.getEntity();
        Item shield = event.getShield().getItem();
        String name = Objects.requireNonNull(shield.getRegistryName()).toString();
        double effectChance;

        // Livingmetal Shield: 10% chance to apply Absorption V for 20 seconds
        // Gilded Livingmetal Shield: 20% chance to apply Absorption V for 20 seconds
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "livingmetal_shield":
                effectChance = 0.1D;
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_livingmetal_shield":
                effectChance = 0.2D;
                break;
            default:
                return;
        }

        if (RandomUtil.chance(effectChance) && (ShieldUtil.handleEssenceContainer(wielder, "harkenscythe:essence_keeper_soul") || ShieldUtil.handleEssenceContainer(wielder, "harkenscythe:essence_vessel_soul")))
        {
            if (!wielder.isPotionActive(MobEffects.ABSORPTION)) wielder.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20 * 20, 4));
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}