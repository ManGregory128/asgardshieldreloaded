package me.mangregory.asr.item;

import com.google.common.collect.Sets;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GiantSwordItem extends SwordItem {

    public boolean isBlocking;
    public int cooldown;
    public int maxUseDuration;
    private static final Set<ToolAction> TOOL_ACTIONS =  Stream.of(ToolActions.SHIELD_BLOCK).collect(Collectors.toCollection(Sets::newIdentityHashSet));
    public GiantSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_, int maxUseDuration) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
        isBlocking = false;
        this.maxUseDuration = maxUseDuration;
        this.cooldown = 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.setMainArm(HumanoidArm.RIGHT);
        player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)),
                SoundEvents.IRON_GOLEM_ATTACK, SoundSource.PLAYERS, 0.8F, 0.8F + level.random.nextFloat() * 0.4F);
        this.isBlocking = true;
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return TOOL_ACTIONS.contains(toolAction);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (this.isBlocking) return UseAnim.BLOCK;
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        if (entity instanceof Player && !entity.getLevel().isClientSide) {
            ((Player) entity).getCooldowns().addCooldown(this, this.cooldown / 2);
            this.cooldown = 0;
        }
        this.isBlocking = false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {

        components.add(Component.literal("Maximum Block Duration: " + this.maxUseDuration / 20 + "s").withStyle(ChatFormatting.AQUA));

        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        this.cooldown++;
        if (this.cooldown >= this.maxUseDuration) {
            entity.stopUsingItem(); //used to be stopActiveHand
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return false;
    }
}
