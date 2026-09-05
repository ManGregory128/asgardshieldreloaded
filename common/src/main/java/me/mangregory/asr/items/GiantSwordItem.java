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
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static me.mangregory.asr.util.handlers.EventHandler.playSound;

public class GiantSwordItem extends SwordItem {
    private long maxBlockDuration;
    public GiantSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
        updateMaxBlockDuration();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (StackCooldowns.isOnCooldown(player, stack))
            return InteractionResultHolder.pass(stack);
        if (player instanceof LivingEntityAccessor accessor) {
            if (accessor.getAttackStrengthTicker() < 10) {
                return InteractionResultHolder.pass(stack); // Prevent blocking if recently attacked
            }
        }
        updateMaxBlockDuration();
        if (ModConfig.ENABLE_GIANT_SWORD_EQUIP_SOUND)
            playSound(player, SoundEvents.IRON_GOLEM_ATTACK, 0.8F);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000; // Maximum possible use duration
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeUsed) {
        if (entity instanceof Player player && !level.isClientSide()) {
            int blockTicks = getUseDuration(stack, entity) - timeUsed;
            StackCooldowns.addCooldown(player, stack, blockTicks / 2);
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (getCooldown(player, stack) >= this.maxBlockDuration) {
                int blockTicks = getCooldown(player, stack);
                entity.stopUsingItem();
                StackCooldowns.addCooldown(player, stack, blockTicks / 2);
            }
        }
    }

    public int getCooldown(Player player, ItemStack stack) {
        return StackCooldowns.getBlockTicks(player, stack);
    }

    public void resetCooldown(Player player, ItemStack stack) {
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
        } else displayDuration = ClientConfigCache.giantSwordBlockDuration;

        tooltipAdder.add(Component.literal("Maximum Block Duration: " + displayDuration / 20 + "s")
                .withStyle(ChatFormatting.AQUA));
    }

    private void updateMaxBlockDuration() {
        this.maxBlockDuration = ModConfig.GIANT_SWORD_BLOCK_DURATION;
    }
}
