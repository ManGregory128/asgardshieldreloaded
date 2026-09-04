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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.mangregory.asr.util.handlers.EventHandler.playSound;

public class GiantSwordItem extends SwordItem {
    private long maxBlockDuration;
    private static final Map<String, Integer> BLOCK_TICKS = new HashMap<>();
    private static final Map<String, Integer> COOLDOWN_REMAINING = new HashMap<>();
    private static final Map<String, Integer> COOLDOWN_TOTAL = new HashMap<>();

    public GiantSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
        updateMaxBlockDuration();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (isCoolingDown(player, stack)) {
            return InteractionResultHolder.fail(stack);
        }

        if (player instanceof LivingEntityAccessor accessor) {
            if (accessor.getAttackStrengthTicker() < 10) {
                return InteractionResultHolder.pass(stack);
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
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeUsed) {
        if (entity instanceof Player player && !level.isClientSide()) {
            startStackCooldown(player, stack);
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide() && entity instanceof Player player) {
            String key = getStackKey(player, stack);
            int blockTicks = getCurrentBlockingTicks(player, stack) + 1;
            BLOCK_TICKS.put(key, blockTicks);

            if (blockTicks >= this.maxBlockDuration) {
                entity.stopUsingItem();
                startStackCooldown(player, stack);
            }
        }
    }

    public static void tickStackCooldown(Player player, ItemStack stack) {
        if (stack.isEmpty()) {
            return;
        }

        String key = getStackKey(player, stack);
        int remaining = COOLDOWN_REMAINING.getOrDefault(key, 0);
        if (remaining <= 0) {
            return;
        }

        int next = remaining - 1;
        if (next > 0) {
            COOLDOWN_REMAINING.put(key, next);
        } else {
            COOLDOWN_REMAINING.remove(key);
            COOLDOWN_TOTAL.remove(key);
            BLOCK_TICKS.remove(key);
        }
    }

    public static int getCurrentBlockingTicks(Player player, ItemStack stack) {
        return BLOCK_TICKS.getOrDefault(getStackKey(player, stack), 0);
    }

    public static int getCooldownRemaining(Player player, ItemStack stack) {
        return COOLDOWN_REMAINING.getOrDefault(getStackKey(player, stack), 0);
    }

    public static boolean isCoolingDown(Player player, ItemStack stack) {
        return getCooldownRemaining(player, stack) > 0;
    }

    public static int getCooldownTotal(Player player, ItemStack stack) {
        return COOLDOWN_TOTAL.getOrDefault(getStackKey(player, stack), 0);
    }

    private void startStackCooldown(Player player, ItemStack stack) {
        String key = getStackKey(player, stack);
        int used = BLOCK_TICKS.getOrDefault(key, 0);
        int cooldownTicks = Math.max(1, used / 2);
        COOLDOWN_REMAINING.put(key, cooldownTicks);
        COOLDOWN_TOTAL.put(key, cooldownTicks);
        BLOCK_TICKS.remove(key);
    }

    public void resetCooldown(Player player, ItemStack stack) {
        String key = getStackKey(player, stack);
        BLOCK_TICKS.remove(key);
        COOLDOWN_REMAINING.remove(key);
        COOLDOWN_TOTAL.remove(key);
    }

    private static String getStackKey(Player player, ItemStack stack) {
        if (stack == player.getMainHandItem()) {
            return player.getUUID() + ":main";
        }
        if (stack == player.getOffhandItem()) {
            return player.getUUID() + ":off";
        }
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            if (player.getInventory().items.get(i) == stack) {
                return player.getUUID() + ":inv:" + i;
            }
        }
        return player.getUUID() + ":" + System.identityHashCode(stack);
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
