package me.mangregory.asr.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.List;
import java.util.Set;

public class GiantSwordItem extends SwordItem {

    private static final Set<ItemAbility> TOOL_ACTIONS = ItemAbilities.DEFAULT_SWORD_ACTIONS;
    public boolean isBlocking;
    public int cooldown;
    public int maxUseDuration;

    public GiantSwordItem(Tier pTier, int maxUseDuration, Properties pProperties) {
        super(pTier, pProperties);
        isBlocking = false;
        this.maxUseDuration = maxUseDuration;
        this.cooldown = 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        this.isBlocking = true;
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility toolAction) {
        TOOL_ACTIONS.add(ItemAbilities.SHIELD_BLOCK);
        return TOOL_ACTIONS.contains(toolAction);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (this.isBlocking) return UseAnim.BLOCK;
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
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
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

        pTooltipComponents.add(Component.literal("Maximum Block Duration: " + this.maxUseDuration / 20 + "s")
                .withStyle(ChatFormatting.AQUA));

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (!entity.level().isClientSide) this.cooldown++;
        if (this.cooldown >= this.maxUseDuration) {
            entity.stopUsingItem(); //used to be stopActiveHand
            this.isBlocking = false;
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
}
