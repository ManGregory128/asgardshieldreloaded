package com.mangregory.asgardshieldreloaded.events;

import com.expandedevents.api.event.LivingBlockDamageEvent;
import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.items.ItemGiantSword;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventGiantSwordBlock {
    @SubscribeEvent
    public static void onShieldBlock(LivingBlockDamageEvent event) {
        Entity wielder = event.getEntity();
        Item shield = event.getShield().getItem();
        String name = Objects.requireNonNull(shield.getRegistryName()).toString();

        if (shield instanceof ItemGiantSword) {
            switch (name) {
                case AsgardShieldReloaded.NAMESPACE + "wooden_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, SoundCategory.PLAYERS, 0.6F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "stone_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_NOTE_HAT, SoundCategory.PLAYERS, 1.2F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "iron_giant_sword":
                case AsgardShieldReloaded.NAMESPACE + "livingmetal_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "golden_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.PLAYERS, 0.6F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "diamond_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 1.0F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "netherquartz_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.PLAYERS, 1.2F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "patchwork_giant_sword":
                case AsgardShieldReloaded.NAMESPACE + "biomass_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_STEP, SoundCategory.PLAYERS, 2.0F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "skull_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
                case AsgardShieldReloaded.NAMESPACE + "ender_giant_sword":
                    wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
                    break;
            }

            wielder.getEntityWorld().playSound(null, wielder.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.PLAYERS, 0.8F, 3.0F + wielder.getEntityWorld().rand.nextFloat() * 0.4F);
        }
    }
}
