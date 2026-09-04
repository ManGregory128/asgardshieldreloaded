package me.mangregory.asr.util.handlers;

import dev.architectury.event.events.client.ClientGuiEvent;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Renders a HUD bar showing the remaining block time when the player is blocking
 * with a giant sword or asgard shield.
 * Uses segmented icons from vc_gauge.png texture (9x9 icons, 6 columns x 4 rows).
 */
public class BlockHudRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("asr", "textures/gui/vc_gauge.png");
    private static final int SEGMENT_SIZE = 9;
    private static final int SEGMENT_COUNT = 9;
    private static final int TEXTURE_SIZE = 256;
    private static final int BAR_Y_OFFSET = -9;
    private static final int BAR_X_OFFSET = 50;
    private static final int OVERLAP_SHIFT = 10;

    public static void register() {
        ClientGuiEvent.RENDER_HUD.register(BlockHudRenderer::onRenderHud);
    }

    private static void onRenderHud(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) {
            return;
        }

        ItemStack displayStack = getDisplayStack(player);
        if (displayStack.isEmpty()) {
            return;
        }

        Item item = displayStack.getItem();
        if (!(item instanceof GiantSwordItem) && !(item instanceof AsgardShieldItem)) {
            return;
        }

        float fillPercentage = getFillPercentage(player, displayStack, item);
        if (fillPercentage < 0.0f || fillPercentage > 1.0f) {
            return;
        }

        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();
        boolean hasOverlap = player.isPassenger() || player.getAirSupply() < player.getMaxAirSupply();

        int totalBarWidth = SEGMENT_COUNT * SEGMENT_SIZE;
        int barX = ((screenWidth - totalBarWidth) / 2) + BAR_X_OFFSET;
        int barY = screenHeight - 39 + BAR_Y_OFFSET;
        if (hasOverlap) {
            barY -= OVERLAP_SHIFT;
        }

        int filledSegments = (int) Math.ceil(fillPercentage * SEGMENT_COUNT);

        for (int i = 0; i < SEGMENT_COUNT; i++) {
            int segmentX = barX + i * SEGMENT_SIZE;
            boolean isFilled = i >= (SEGMENT_COUNT - filledSegments);
            float u;
            if (item instanceof GiantSwordItem) {
                u = isFilled ? 45.0f : 36.0f;
            } else if (isBlockingWithGildedShield(item)) {
                u = isFilled ? 27.0f : 18.0f;
            } else {
                u = isFilled ? 9.0f : 0.0f;
            }

            guiGraphics.blit(TEXTURE, segmentX, barY, u, 0.0f,
                    SEGMENT_SIZE, SEGMENT_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        }
    }

    private static ItemStack getDisplayStack(Player player) {
        if (player.isUsingItem()) {
            ItemStack activeStack = player.getUseItem();
            Item activeItem = activeStack.getItem();
            if ((activeItem instanceof GiantSwordItem || activeItem instanceof AsgardShieldItem)
                    && !isCoolingDownForDisplay(player, activeStack)) {
                return activeStack;
            }
        }

        ItemStack best = ItemStack.EMPTY;
        int bestRemaining = Integer.MAX_VALUE;
        for (ItemStack stack : new ItemStack[] { player.getMainHandItem(), player.getOffhandItem() }) {
            if (stack.isEmpty()) {
                continue;
            }
            Item item = stack.getItem();
            if (!(item instanceof GiantSwordItem) && !(item instanceof AsgardShieldItem)) {
                continue;
            }

            int remaining = item instanceof GiantSwordItem
                    ? GiantSwordItem.getCooldownRemaining(player, stack)
                    : AsgardShieldItem.getCooldownRemaining(player, stack);
            if (remaining > 0 && remaining < bestRemaining) {
                best = stack;
                bestRemaining = remaining;
            }
        }

        return best;
    }

    private static float getFillPercentage(Player player, ItemStack stack, Item item) {
        int remaining = item instanceof GiantSwordItem
                ? GiantSwordItem.getCooldownRemaining(player, stack)
                : AsgardShieldItem.getCooldownRemaining(player, stack);
        if (remaining > 0) {
            int total = item instanceof GiantSwordItem
                    ? GiantSwordItem.getCooldownTotal(player, stack)
                    : AsgardShieldItem.getCooldownTotal(player, stack);
            if (total <= 0) {
                return 0.0f;
            }
            return Math.clamp(1.0f - ((float) remaining / total), 0.0f, 1.0f);
        }

        if (player.isUsingItem() && stack == player.getUseItem()) {
            long maxDuration = item instanceof GiantSwordItem
                    ? ModConfig.GIANT_SWORD_BLOCK_DURATION
                    : ModConfig.ASGARD_SHIELD_BLOCK_DURATION;
            int used = item instanceof GiantSwordItem
                    ? GiantSwordItem.getCurrentBlockingTicks(player, stack)
                    : AsgardShieldItem.getCurrentBlockingTicks(player, stack);
            return Math.clamp(1.0f - ((float) used / maxDuration), 0.0f, 1.0f);
        }

        return 0.0f;
    }

    private static boolean isCoolingDownForDisplay(Player player, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        Item item = stack.getItem();
        int remaining = item instanceof GiantSwordItem
                ? GiantSwordItem.getCooldownRemaining(player, stack)
                : item instanceof AsgardShieldItem
                        ? AsgardShieldItem.getCooldownRemaining(player, stack)
                        : 0;
        return remaining > 0;
    }

    static boolean isBlockingWithGildedShield(Item item) {
        return item.toString().contains("gilded");
    }
}
