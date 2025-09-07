package me.mangregory.items;

import me.mangregory.AsgardShieldReloaded;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class GiantSwordItem extends Item {
    private final int maxBlockDuration;
    private int cooldown = 0;

    public GiantSwordItem(int maxBlockDuration, Properties properties) {
        super(properties);
        this.maxBlockDuration = maxBlockDuration;
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Ensure the stack has a unique cooldown group
        if (!stack.has(DataComponents.USE_COOLDOWN)) {
            String uniqueId = UUID.randomUUID().toString();
            ResourceLocation uniqueCooldownGroup = ResourceLocation.fromNamespaceAndPath(
                    AsgardShieldReloaded.MOD_ID,
                    "giant_sword_" + uniqueId
            );
            stack.set(DataComponents.USE_COOLDOWN, new UseCooldown(0.1f, Optional.of(uniqueCooldownGroup)));
        }

        player.startUsingItem(hand);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
        return ItemUseAnimation.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000; // Maximum possible use duration
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeUsed) {
        if (entity instanceof Player && !level.isClientSide) {
            ((Player) entity).getCooldowns().addCooldown(stack, this.cooldown / 2);
            resetCooldown();
        }
        return false;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide) {
            incrementCooldown(1);
            AsgardShieldReloaded.log("cooldown: " + this.cooldown); //TEMP
        }
        if (this.cooldown >= this.maxBlockDuration) {
            entity.stopUsingItem();
            ((Player) entity).getCooldowns().addCooldown(stack, this.cooldown / 2);
            resetCooldown();
        }
    }

    public void resetCooldown() {
        this.cooldown = 0;
    }

    public void incrementCooldown(int value) {
        this.cooldown += value;
    }
}
