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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static me.mangregory.asr.util.handlers.EventHandler.playSound;

public class AsgardShieldItem extends ShieldItem {

    private long maxBlockDuration;
    public AsgardShieldItem(Properties properties) {
        super(properties);
        updateMaxBlockDuration();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (StackCooldowns.isOnCooldown(player, stack))
            return InteractionResultHolder.pass(stack);
        updateMaxBlockDuration();
        super.use(level, player, hand);
        if (ModConfig.ENABLE_ASGARD_SHIELD_EQUIP_SOUND)
            playSound(player, SoundEvents.IRON_GOLEM_ATTACK, 0.8F);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000; // Maximum possible use duration
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        if (super.isValidRepairItem(stack, repairCandidate))
            return true;

        String itemId = BuiltInRegistries.ITEM.getKey(this).getPath();
        return switch (itemId) {
            case "diamond_shield", "gilded_diamond_shield" -> repairCandidate.is(Items.DIAMOND);
            case "ender_shield", "gilded_ender_shield" -> repairCandidate.is(Items.OBSIDIAN);
            case "netherquartz_shield", "gilded_netherquartz_shield" -> repairCandidate.is(Items.QUARTZ);
            case "skull_shield", "gilded_skull_shield" -> repairCandidate.is(Items.BONE);
            case "iron_shield", "gilded_iron_shield" -> repairCandidate.is(Items.IRON_INGOT);
            case "stone_shield", "gilded_stone_shield" -> repairCandidate.is(ItemTags.STONE_TOOL_MATERIALS);
            case "copper_shield", "gilded_copper_shield" -> repairCandidate.is(Items.COPPER_INGOT);
            case "netherite_shield", "gilded_netherite_shield" -> repairCandidate.is(Items.NETHERITE_INGOT);
            case "wooden_shield", "gilded_wooden_shield" -> repairCandidate.is(ItemTags.PLANKS);
            default -> false;
        };
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
