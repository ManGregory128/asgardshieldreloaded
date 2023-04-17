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
import com.mangregory.asgardshieldreloaded.init.ModItems;

public class ItemGiantSword extends ItemSword
{
    public int cooldown;
    public int maxUseDuration;
    public boolean isBlocking;

    public ItemGiantSword(String name, Item.ToolMaterial material, int maxUseDuration)
    {
        super(material);
        this.setTranslationKey(AsgardShieldReloaded.MOD_ID + "." + name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.cooldown = 0;
        this.maxUseDuration = maxUseDuration;
        this.isBlocking = false;
        ModItems.ITEMS.add(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        playerIn.getEntityWorld().playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_IRONGOLEM_ATTACK, SoundCategory.PLAYERS, 0.8F, 0.8F + playerIn.getEntityWorld().rand.nextFloat() * 0.4F);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        if (this.isBlocking) return EnumAction.BLOCK;
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer && !worldIn.isRemote)
        {
            ((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, this.cooldown / 2);
            this.cooldown = 0;
        }
        this.isBlocking = false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String duration = "tooltip." + AsgardShieldReloaded.MOD_ID + ".category.duration";
        tooltip.add(I18n.format(duration) + ": " + this.maxUseDuration / 20 + "s");
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        this.cooldown++;
        if (this.cooldown >= this.maxUseDuration)
        {
            player.stopActiveHand();
            this.isBlocking = false;
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return false;
    }
}