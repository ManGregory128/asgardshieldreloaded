package com.mangregory.asgardshieldreloaded.items;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.config.ASRConfig;
import com.mangregory.asgardshieldreloaded.init.ModItems;
import org.jetbrains.annotations.NotNull;

public class ItemAsgardShield extends ItemShield implements IDyeable
{
    private final Item.ToolMaterial material;
    private final int color;

    public ItemAsgardShield(String name, Item.ToolMaterial material, int durability, int color)
    {
        this.setTranslationKey(AsgardShieldReloaded.MOD_ID + "." + name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxDamage(durability);
        this.color = color;
        this.material = material;
        this.addPropertyOverride(new ResourceLocation(AsgardShieldReloaded.NAMESPACE + "blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@NotNull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("dyed"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@NotNull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata()))
                {
                    return 1.0F;
                }

                return 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
        ModItems.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull String getItemStackDisplayName(@NotNull ItemStack stack)
    {
        return I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flag)
    {
        String name = Objects.requireNonNull(this.getRegistryName()).getPath();
        int color = getDyedColor(stack);

        // If it's dyed, show it on the tooltip
        if (color != getDefaultDyedColorForMeta(stack.getMetadata()))
        {
            if (flag.isAdvanced())
            	tooltip.add(new TextComponentTranslation("item.color", TextFormatting.GRAY + String.format("#%06X", color)).getFormattedText());
            else
            {
            	tooltip.add(TextFormatting.ITALIC + new TextComponentTranslation("item.dyed").getFormattedText());
            }
        }

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
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, @NotNull EntityPlayer player, @NotNull EnumHand hand)
    {
        if (ASRConfig.MISC.BLOCKING_SOUND) player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_IRONGOLEM_ATTACK, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean getIsRepairable(@NotNull ItemStack toRepair, @NotNull ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        return !mat.isEmpty() && OreDictionary.itemMatches(mat, repair, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    @Override
    public boolean shouldCauseReequipAnimation(@NotNull ItemStack oldStack, @NotNull ItemStack newStack, boolean slotChanged)
    {
        return slotChanged;
    }

    @Override
    public boolean isShield(@NotNull ItemStack stack, @Nullable EntityLivingBase entity)
    {
        return true;
    }

    /* IDyeable Code - Courtesy of TheCodex6824, IcarussOne */
    @Override
    public int getDyedColor(ItemStack stack)
    {
        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!stack.getTagCompound().hasKey("color", NBT.TAG_INT))
        {
            stack.getTagCompound().setInteger("color", getDefaultDyedColorForMeta(stack.getMetadata()));
        }

        return stack.getTagCompound().getInteger("color");
    }

    @Override
    public void setDyedColor(ItemStack stack, int color)
    {
        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setInteger("color", color);
    }

    @Override
    public int getDefaultDyedColorForMeta(int meta)
    {
        return color;
    }

    @Override
    public @NotNull EnumActionResult onItemUseFirst(EntityPlayer player, World world, @NotNull BlockPos pos, @NotNull EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        // Right-clicking a filled cauldron with the dyed item will wash it out
        if (state.getBlock() == Blocks.CAULDRON && state.getValue(BlockCauldron.LEVEL) > 0 && getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata())) {
            setDyedColor(stack, getDefaultDyedColorForMeta(stack.getMetadata()));
            world.setBlockState(pos, state.withProperty(BlockCauldron.LEVEL, state.getValue(BlockCauldron.LEVEL) - 1));
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 0.5F, 1.0F);
            return EnumActionResult.SUCCESS;
        }

        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }
}