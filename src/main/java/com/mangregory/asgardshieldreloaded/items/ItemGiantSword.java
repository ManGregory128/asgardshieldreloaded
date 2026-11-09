package com.mangregory.asgardshieldreloaded.items;

import javax.annotation.Nullable;

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
    public ItemGiantSword(String name, Item.ToolMaterial material)
    {
        super(material);
        this.setTranslationKey(AsgardShieldReloaded.MOD_ID + "." + name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        ModItems.ITEMS.add(this);
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, EntityPlayer player, @NotNull EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        if (ASRConfig.MISC.BLOCKING_SOUND) player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_IRONGOLEM_ATTACK, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean isShield(@NotNull ItemStack stack, @Nullable EntityLivingBase entity)
    {
        return true;
    }

    @Override
    public @NotNull EnumAction getItemUseAction(@NotNull ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    @Override
    public int getMaxItemUseDuration(@NotNull ItemStack stack)
    {
        return 72000;
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