package com.mangregory.asgardshieldreloaded.items;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.config.ASRConfig;
import com.mangregory.asgardshieldreloaded.init.ModItems;
import org.jetbrains.annotations.NotNull;

public class ItemGiantSword extends ItemSword
{
    private final int maxUseDuration;
    private int cooldown;
    private boolean isBlocking;

    public ItemGiantSword(String name, Item.ToolMaterial material, int maxUseDuration)
    {
        super(material);
        this.setTranslationKey(AsgardShieldReloaded.MOD_ID + "." + name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.cooldown = 0;
        this.maxUseDuration = maxUseDuration;
        this.setBlocking(false);
        ModItems.ITEMS.add(this);
    }

    public boolean getBlocking()
    {
        return isBlocking;
    }

    public void setBlocking(boolean blocking)
    {
        isBlocking = blocking;
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, EntityPlayer playerIn, @NotNull EnumHand hand)
    {
        ItemStack stack = playerIn.getHeldItem(hand);
        playerIn.setActiveHand(hand);
        if (ASRConfig.MISC.BLOCKING_SOUND) playerIn.getEntityWorld().playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_IRONGOLEM_ATTACK, SoundCategory.PLAYERS, 0.8F, 0.8F + playerIn.getEntityWorld().rand.nextFloat() * 0.4F);
        this.setBlocking(true);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public @NotNull EnumAction getItemUseAction(@NotNull ItemStack stack)
    {
        if (this.getBlocking()) return EnumAction.BLOCK;
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(@NotNull ItemStack stack)
    {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(@NotNull ItemStack stack, @NotNull World world, @NotNull EntityLivingBase entity, int timeLeft)
    {
        if (entity instanceof EntityPlayer && !world.isRemote)
        {
            ((EntityPlayer) entity).getCooldownTracker().setCooldown(this, this.cooldown / 2);
            this.cooldown = 0;
        }
        this.setBlocking(false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<String> tooltip, @NotNull ITooltipFlag flag)
    {
        String duration = "tooltip." + AsgardShieldReloaded.MOD_ID + ".category.duration";
        tooltip.add(I18n.format(duration) + ": " + this.maxUseDuration / 20 + "s");
    }

    @Override
    public void onUsingTick(@NotNull ItemStack stack, EntityLivingBase player, int count)
    {
        if (!player.world.isRemote) this.cooldown++;
        if (this.cooldown >= this.maxUseDuration)
        {
            player.stopActiveHand();
            this.setBlocking(false);
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(@NotNull ItemStack oldStack, @NotNull ItemStack newStack, boolean slotChanged)
    {
        return slotChanged;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
}