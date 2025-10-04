package me.mangregory.items;

import me.mangregory.AsgardShieldReloaded;
import me.mangregory.util.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class AsgardShieldItem extends ShieldItem {

    private long maxBlockDuration;
    private int cooldown = 0;

    public AsgardShieldItem(Properties properties) {
        super(properties);
        updateMaxBlockDuration();
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Ensure the stack has a unique cooldown group
        if (!stack.has(DataComponents.USE_COOLDOWN)) {
            String uniqueId = UUID.randomUUID().toString();
            ResourceLocation uniqueCooldownGroup = ResourceLocation.fromNamespaceAndPath(
                    AsgardShieldReloaded.MOD_ID,
                    "giant_shield_" + uniqueId
            );
            stack.set(DataComponents.USE_COOLDOWN, new UseCooldown(0.1f, Optional.of(uniqueCooldownGroup)));
        }
        updateMaxBlockDuration();
        player.startUsingItem(hand);

        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.IRON_GOLEM_ATTACK,
                SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
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
        if (entity instanceof Player && !level.isClientSide()) {
            ((Player) entity).getCooldowns().addCooldown(stack, this.cooldown / 2);
            resetCooldown();
        }
        return false;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide()) {
            incrementCooldown(1);
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

    private void updateMaxBlockDuration() {
        this.maxBlockDuration = ModConfig.ASGARD_SHIELD_BLOCK_DURATION;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }
        updateMaxBlockDuration();
        boolean sneakPressed = Minecraft.getInstance().hasShiftDown();
        tooltipAdder.accept(Component.literal("Maximum Block Duration: " + this.maxBlockDuration / 20 + "s")
                .withStyle(ChatFormatting.AQUA));
        if (!sneakPressed) {
            tooltipAdder.accept(Component.translatable("item." + stack.toString().replace("1 asr:", "asr.") + ".perk",
                            "shift")
                    .withStyle(ChatFormatting.GREEN));
            tooltipAdder.accept(Component.translatable("item." + stack.toString().replace("1 asr:", "asr.") + ".weakness",
                            "shift")
                    .withStyle(ChatFormatting.RED));
            tooltipAdder.accept(Component.literal("Hold shift for more info").withStyle(ChatFormatting.GRAY));
        } else {
            tooltipAdder.accept(Component.translatable("item." + stack.toString().replace("1 asr:", "asr.") + ".perk.desc", "")
                    .withStyle(ChatFormatting.GREEN));
            tooltipAdder.accept(Component.translatable("item." + stack.toString().replace("1 asr:", "asr.") + ".weakness.desc", "")
                    .withStyle(ChatFormatting.RED));
        }
    }
}
