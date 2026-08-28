package me.mangregory.asr.items;

import me.mangregory.asr.config.ClientConfigCache;
import me.mangregory.asr.config.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.mangregory.asr.util.handlers.EventHandler.playSound;

public class AsgardShieldItem extends ShieldItem {

    private long maxBlockDuration;
    private static final Map<String, Integer> cooldownMap = new HashMap<>();

    public AsgardShieldItem(Properties properties) {
        super(properties);
        updateMaxBlockDuration();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        updateMaxBlockDuration();
        super.use(level, player, hand);
        if (ModConfig.ENABLE_ASGARD_SHIELD_EQUIP_SOUND)
            playSound(player, SoundEvents.IRON_GOLEM_ATTACK, 0.8F);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000; // Maximum possible use duration
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeUsed) {
        if (entity instanceof Player player && !level.isClientSide()) {
            String key = getCooldownKey(player, stack);
            player.getCooldowns().addCooldown(stack.getItem(), getCooldown(key) / 2);
            resetCooldown(key);
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide() && entity instanceof Player player) {
            String key = getCooldownKey(player, stack);
            incrementCooldown(key, 1);

            if (getCooldown(key) >= this.maxBlockDuration) {
                entity.stopUsingItem();
                player.getCooldowns().addCooldown(stack.getItem(), getCooldown(key) / 2);
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

    private void updateMaxBlockDuration() {
        this.maxBlockDuration = ModConfig.ASGARD_SHIELD_BLOCK_DURATION;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
                                List<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipAdder, flag);

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }
        long displayDuration;
        if (mc.hasSingleplayerServer()) {
            updateMaxBlockDuration();
            displayDuration = this.maxBlockDuration;
        } else displayDuration = ClientConfigCache.asgardShieldBlockDuration;

        boolean sneakPressed = Screen.hasShiftDown();
        tooltipAdder.add(Component.literal("Maximum Block Duration: " + displayDuration / 20 + "s")
                .withStyle(ChatFormatting.AQUA));
        if (!sneakPressed) {
            tooltipAdder.add(Component.translatable(TooltipKeys.perk(stack), "shift")
                    .withStyle(ChatFormatting.GREEN));
            tooltipAdder.add(Component.translatable(TooltipKeys.weakness(stack), "shift")
                    .withStyle(ChatFormatting.RED));
            tooltipAdder.add(Component.literal("Hold shift for more info").withStyle(ChatFormatting.GRAY));
        } else {
            tooltipAdder.add(Component.translatable(TooltipKeys.perkDesc(stack), "")
                    .withStyle(ChatFormatting.GREEN));
            tooltipAdder.add(Component.translatable(TooltipKeys.weaknessDesc(stack), "")
                    .withStyle(ChatFormatting.RED));
        }
    }
}
