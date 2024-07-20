package me.mangregory.asr.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class AsgardShieldItem extends ShieldItem {
    public int cooldown;
    public boolean isBlocking;
    public int maxUseDuration;

    public AsgardShieldItem(Properties properties, int maxUseDuration) {
        super(properties);
        this.cooldown = 0;
        this.isBlocking = false;
        this.maxUseDuration = maxUseDuration;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                SoundEvents.IRON_GOLEM_ATTACK, SoundSource.PLAYERS, 0.8F, 0.8F + level.random.nextFloat() * 0.4F);
        this.isBlocking = true;
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (this.isBlocking) return UseAnim.BLOCK;
        return UseAnim.NONE;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        if (entity instanceof Player && !entity.level().isClientSide) {
            ((Player) entity).getCooldowns().addCooldown(this, this.cooldown / 2);
            this.cooldown = 0;
        }
        this.isBlocking = false;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (!entity.level().isClientSide) this.cooldown++;
        if (this.cooldown >= this.maxUseDuration) {
            entity.stopUsingItem(); //used to be stopActiveHand
            this.isBlocking = false;
        }
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }

        boolean sneakPressed = Screen.hasShiftDown();
        pTooltipComponents.add(Component.translatable("tooltip.asr.category.duration", ": " + this.maxUseDuration / 20 + "s").withStyle(ChatFormatting.AQUA));
        if (!sneakPressed) {
            pTooltipComponents.add(Component.translatable("item." + pStack.toString().replace("1 asr:", "asr.") + ".perk",
                            "shift")
                    .withStyle(ChatFormatting.GREEN));
            pTooltipComponents.add(Component.translatable("item." + pStack.toString().replace("1 asr:", "asr.") + ".weakness",
                            "shift")
                    .withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal("Hold shift for more info").withStyle(ChatFormatting.GRAY));
        } else {
            pTooltipComponents.add(Component.translatable("item." + pStack.toString().replace("1 asr:", "asr.") + ".perk.desc", "")
                    .withStyle(ChatFormatting.GREEN));
            pTooltipComponents.add(Component.translatable("item." + pStack.toString().replace("1 asr:", "asr.") + ".weakness.desc", "")
                    .withStyle(ChatFormatting.RED));
        }
    }
}
