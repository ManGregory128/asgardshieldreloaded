package com.mangregory.asgardshieldreloaded.items;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.init.ModItems;

public class ItemAsgardShield extends ItemShield
{
    private final int maxUseDuration;
    private final Item.ToolMaterial material;
    private int cooldown;
    private boolean isBlocking;

    public ItemAsgardShield(String name, Item.ToolMaterial material, int durability, int maxUseDuration)
    {
        this.setTranslationKey(AsgardShieldReloaded.MOD_ID + "." + name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxDamage(durability);
        this.cooldown = 0;
        this.material = material;
        this.maxUseDuration = maxUseDuration;
        this.setBlocking(false);
        this.addPropertyOverride(new ResourceLocation(AsgardShieldReloaded.NAMESPACE + "blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
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
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
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
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
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
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String name = this.getRegistryName().getPath();

        String duration = "tooltip." + AsgardShieldReloaded.MOD_ID + ".category.duration";
        tooltip.add(I18n.format(duration) + ": " + this.maxUseDuration / 20 + "s");

        String weakness = "item." + AsgardShieldReloaded.MOD_ID + "." + name + ".weakness";
        tooltip.add(TextFormatting.RED + I18n.format(weakness));
        String weaknessDesc = "item." + AsgardShieldReloaded.MOD_ID + "." + name + ".weakness.desc";
        if (GuiScreen.isShiftKeyDown()) tooltip.add(TextFormatting.RED + I18n.format(weaknessDesc));

        String perk = "item." + AsgardShieldReloaded.MOD_ID + "." + name + ".perk";
        tooltip.add(TextFormatting.GREEN + I18n.format(perk));
        String perkDesc = "item." + AsgardShieldReloaded.MOD_ID + "." + name + ".perk.desc";
        if (GuiScreen.isShiftKeyDown()) tooltip.add(TextFormatting.GREEN + I18n.format(perkDesc));
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        if (this.getBlocking()) return EnumAction.BLOCK;
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.getEntityWorld().playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_IRONGOLEM_ATTACK, SoundCategory.PLAYERS, 0.8F, 0.8F + playerIn.getEntityWorld().rand.nextFloat() * 0.4F);
        this.setBlocking(true);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        return !mat.isEmpty() && OreDictionary.itemMatches(mat, repair, false);
    }
}