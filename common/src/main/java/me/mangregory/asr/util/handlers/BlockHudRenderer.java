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
    private static final int BAR_Y_OFFSET = -9; // Directly above hunger bar (screenHeight - 39 - 9 = screenHeight - 48)
    private static final int BAR_X_OFFSET = 50; // Shift right to place adjacent to hunger bar
    private static final int OVERLAP_SHIFT = 10; // Shift up when other bars overlap
    
    // State tracking to prevent flashing when cooldown resets
    private static boolean wasBlockingLastTick = false;
    private static float lastFillPercentage = 1.0f;
    
    // Icon positions in texture (6 columns x 4 rows, 9x9 each, with gaps between item types)
    // Regular shields: column 0-1 at (0,0) empty, (9,0) full
    // Gilded shields: column 2-3 at (18,0) empty, (27,0) full
    // Giant swords: column 4-5 at (36,0) empty, (45,0) full

    public static void register() {
        ClientGuiEvent.RENDER_HUD.register(BlockHudRenderer::onRenderHud);
    }

    private static void onRenderHud(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        
        if (player == null || !player.isUsingItem()) {
            return;
        }

        ItemStack usingItem = player.getUseItem();
        Item item = usingItem.getItem();
        
        // Check if player is blocking with a giant sword or asgard shield
        if (!(item instanceof GiantSwordItem) && !(item instanceof AsgardShieldItem)) {
            return;
        }

        boolean isBlockingWithGiantSword = item instanceof GiantSwordItem;

        // Get cooldown from the item (increases as player blocks)
        long cooldown;
        if (isBlockingWithGiantSword) {
            cooldown = ((GiantSwordItem) item).getCooldown(player, usingItem);
        } else {
            cooldown = ((AsgardShieldItem) item).getCooldown(player, usingItem);
        }

        // Get max duration from config
        long maxDuration;
        if (isBlockingWithGiantSword) {
            maxDuration = ModConfig.GIANT_SWORD_BLOCK_DURATION;
        } else {
            maxDuration = ModConfig.ASGARD_SHIELD_BLOCK_DURATION;
        }

        // Calculate fill percentage (invert since cooldown increases)
        // cooldown=0 at start (full bar), cooldown=maxDuration at end (empty bar)
        float fillPercentage = 1.0f - (float) cooldown / maxDuration;
        fillPercentage = Math.clamp(fillPercentage, 0.0f, 1.0f);

        // Prevent flash when cooldown resets (player hit or new block starts)
        boolean isBlocking = player.isUsingItem();
        if (isBlocking && cooldown == 0 && wasBlockingLastTick) {
            // Cooldown reset mid-block (e.g., player was hit) - use last percentage to avoid flash
            fillPercentage = lastFillPercentage;
        }

        // Get screen dimensions
        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();
        
        // Check if other HUD bars would overlap (mount health when riding, oxygen when underwater)
        boolean hasOverlap = player.isPassenger() || player.getAirSupply() < player.getMaxAirSupply();
        
        // Calculate bar position (centered horizontally, directly above hunger bar)
        int totalBarWidth = SEGMENT_COUNT * SEGMENT_SIZE; // No spacing between segments
        int barX = ((screenWidth - totalBarWidth) / 2) + BAR_X_OFFSET;
        int barY = screenHeight - 39 + BAR_Y_OFFSET; // Hunger bar is at screenHeight - 39
        
        // Shift up if mount health or oxygen bar would overlap
        if (hasOverlap) {
            barY -= OVERLAP_SHIFT;
        }

        // Calculate how many segments should be filled
        int filledSegments = (int) Math.ceil(fillPercentage * SEGMENT_COUNT);
        
        // Render each segment
        for (int i = 0; i < SEGMENT_COUNT; i++) {
            int segmentX = barX + i * (SEGMENT_SIZE);
            boolean isFilled = i >= (SEGMENT_COUNT - filledSegments);

            // Use different icon sets based on item type:
            // Regular shields: column 0-1 (0=empty, 9=full)
            // Gilded shields: column 2-3 (18=empty, 27=full)
            // Giant swords: column 4-5 (36=empty, 45=full)
            float u;
            if (isBlockingWithGiantSword) {
                u = isFilled ? 45.0f : 36.0f;
            } else if (isBlockingWithGildedShield(item)) {
                u = isFilled ? 27.0f : 18.0f;
            } else {
                u = isFilled ? 9.0f : 0.0f;
            }
            float v = 0.0f;
            
            guiGraphics.blit(TEXTURE, segmentX, barY, u, v, 
                SEGMENT_SIZE, SEGMENT_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        }
        
        // Update state for next tick
        wasBlockingLastTick = isBlocking;
        lastFillPercentage = fillPercentage;
    }
    static boolean isBlockingWithGildedShield(Item item) {
        return item.toString().contains("gilded");
    }
}
