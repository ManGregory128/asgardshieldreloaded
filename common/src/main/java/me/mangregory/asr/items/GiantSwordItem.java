package me.mangregory.asr.items;

import me.mangregory.asr.mixin.LivingEntityAccessor;
import me.mangregory.asr.config.ClientConfigCache;
import me.mangregory.asr.config.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static me.mangregory.asr.util.handlers.EventHandler.playSound;

public class GiantSwordItem extends Item {
    private long maxBlockDuration;
    private static final Map<String, Integer> cooldownMap = new HashMap<>();

    public GiantSwordItem(Properties properties) {
        super(properties);
        updateMaxBlockDuration();
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (player instanceof LivingEntityAccessor accessor) {
            if (accessor.getAttackStrengthTicker() < 10) {
                return InteractionResult.PASS; // Prevent blocking if the player recently attacked
            }
        }
        updateMaxBlockDuration();
        super.use(level, player, hand);
        if (ModConfig.ENABLE_GIANT_SWORD_EQUIP_SOUND)
            playSound(player, SoundEvents.IRON_GOLEM_ATTACK, 0.8F);
        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000; // Maximum possible use duration
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeUsed) {
        if (entity instanceof Player player && !level.isClientSide()) {
            String key = getCooldownKey(player, stack);
            player.getCooldowns().addCooldown(stack, getCooldown(key) / 2);
            resetCooldown(key);
        }
        return false;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide() && entity instanceof Player player) {
            String key = getCooldownKey(player, stack);
            incrementCooldown(key, 1);

            if (getCooldown(key) >= this.maxBlockDuration) {
                entity.stopUsingItem();
                player.getCooldowns().addCooldown(stack, getCooldown(key) / 2);
                resetCooldown(key);
            }
        }
    }

    private String getCooldownKey(Player player, ItemStack stack) {
        int slot = player.getInventory().findSlotMatchingItem(stack);
        return player.getUUID() + ":" + slot;
    }

    private int getCooldown(String key) {
        return cooldownMap.getOrDefault(key, 0);
    }

    private void resetCooldown(String key) {
        cooldownMap.remove(key);
    }

    public int getCooldown(Player player, ItemStack stack) {
        String key = getCooldownKey(player, stack);
        return getCooldown(key);
    }

    public void resetCooldown(Player player, ItemStack stack) {
        String key = getCooldownKey(player, stack);
        resetCooldown(key);
    }

    private void incrementCooldown(String key, int value) {
        cooldownMap.put(key, getCooldown(key) + value);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
                                Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }
        long displayDuration;
        if (mc.hasSingleplayerServer()) {
            updateMaxBlockDuration();
            displayDuration = this.maxBlockDuration;
        } else displayDuration = ClientConfigCache.giantSwordBlockDuration;

        tooltipAdder.accept(Component.literal("Maximum Block Duration: " + displayDuration / 20 + "s")
                .withStyle(ChatFormatting.AQUA));
    }

    private void updateMaxBlockDuration() {
        this.maxBlockDuration = ModConfig.GIANT_SWORD_BLOCK_DURATION;
    }
}
