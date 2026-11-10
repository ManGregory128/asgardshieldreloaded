package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventPatchworkShieldBlock
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
        double attackerEffectChance;

        // Patchwork Shield: Attacker has a 10% chance to get Weakness II (Nausea if it's a player)
        // Gilded Diamond Shield: Attacker has a 20% chance to get Weakness II (Nausea if it's a player)
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "patchwork_shield":
                attackerEffectChance = 0.1D;
                break;
            case AsgardShieldReloaded.NAMESPACE + "gilded_patchwork_shield":
                attackerEffectChance = 0.2D;
                break;
            default:
                return;
        }

        if (RandomUtil.chance(0.1D)) {
            wielder.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 8 * 20, 0));
        }

        if (RandomUtil.chance(attackerEffectChance))
        {
            if (attacker instanceof EntityPlayer) {
                ((EntityPlayer) attacker).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 10 * 20, 0));
            } else if (attacker instanceof EntityLivingBase) {
                ((EntityLivingBase) attacker).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 15 * 20, 1));
            }
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_STEP, SoundCategory.PLAYERS, 2.0F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}