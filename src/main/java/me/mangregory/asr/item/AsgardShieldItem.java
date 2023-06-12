package me.mangregory.asr.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AsgardShieldItem extends ShieldItem {
    private final String strengthDesc;
    private final String weaknessDesc;
    public int cooldown;
    public boolean isBlocking;
    public int maxUseDuration;

    public AsgardShieldItem(Properties properties, int maxUseDuration, String strength, String weakness) {
        super(properties);
        this.cooldown = 0;
        this.isBlocking = false;
        this.maxUseDuration = maxUseDuration;
        this.strengthDesc = strength;
        this.weaknessDesc = weakness;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        //player.setMainArm(HumanoidArm.RIGHT);
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
        this.cooldown++;
        if (this.cooldown >= this.maxUseDuration) {
            entity.stopUsingItem(); //used to be stopActiveHand
            this.isBlocking = false;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {

        components.add(Component.literal("Maximum Block Duration: " + this.maxUseDuration / 20 + "s").withStyle(ChatFormatting.AQUA));
        components.add(Component.literal("Perk: " + strengthDesc).withStyle(ChatFormatting.GREEN));
        components.add(Component.literal("Weakness: " + weaknessDesc).withStyle(ChatFormatting.RED));
        super.appendHoverText(stack, level, components, flag);
    }
}
