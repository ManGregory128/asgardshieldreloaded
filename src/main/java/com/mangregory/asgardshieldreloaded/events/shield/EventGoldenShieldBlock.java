package com.mangregory.asgardshieldreloaded.events.shield;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventGoldenShieldBlock
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
        int healAmount;

        // Golden Shield: Shield heals by 3 against undead
        // Blessed Golden Shield: Shield heals by 6 against undead
        switch (name)
        {
            case AsgardShieldReloaded.NAMESPACE + "golden_shield":
                healAmount = 3;
                break;
            case AsgardShieldReloaded.NAMESPACE + "blessed_golden_shield":
                healAmount = 6;
                break;
            default:
                return;
        }

        if (RandomUtil.chance(0.5D) && wielder.shouldHeal() && attacker instanceof EntityLivingBase)
        {
            boolean isUndead = ((EntityLivingBase) attacker).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
            // Weakness: Paladin's Oath
            // Healing will only work when blocking against undead
            if (isUndead)
            {
                // TODO: Spawn particles when player is healed
                // One heart and a half + 10% of the shield damage
                wielder.heal(healAmount + event.getShieldDamage() * 0.1F);
                wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.PLAYERS, 2.0F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
            }
        }

        wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.PLAYERS, 0.6F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
    }
}