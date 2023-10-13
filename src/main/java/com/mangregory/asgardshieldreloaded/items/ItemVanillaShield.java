package com.mangregory.asgardshieldreloaded.items;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.config.ASRConfig;

public class ItemVanillaShield extends ItemShield
{
    private final int maxUseDuration;
    private int cooldown;
    private boolean isBlocking;

    public ItemVanillaShield()
    {
        super();
        this.setTranslationKey("shield");
        this.setRegistryName("minecraft", "shield");
        this.setMaxDamage(ASRConfig.VANILLA_SHIELD.VANILLA_SHIELD_DURABILITY);
        this.cooldown = 0;
        this.maxUseDuration = ASRConfig.VANILLA_SHIELD.VANILLA_SHIELD_MAXUSEDURATION;
        this.setBlocking(false);
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
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer && !worldIn.isRemote)
        {
            ((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, this.cooldown / 2);
            this.cooldown = 0;
        }
        this.setBlocking(false);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        if (!player.world.isRemote) this.cooldown++;
        if (this.cooldown >= this.maxUseDuration)
        {
            player.stopActiveHand();
            this.setBlocking(false);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        String duration = "tooltip." + AsgardShieldReloaded.MOD_ID + ".category.duration";
        tooltip.add(I18n.format(duration) + ": " + this.maxUseDuration / 20 + "s");
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        if (this.getBlocking()) return EnumAction.BLOCK;
        return EnumAction.NONE;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	if (ASRConfig.MISC.BLOCKING_SOUND) playerIn.getEntityWorld().playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_IRONGOLEM_ATTACK, SoundCategory.PLAYERS, 0.8F, 0.8F + playerIn.getEntityWorld().rand.nextFloat() * 0.4F);
        this.setBlocking(true);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}