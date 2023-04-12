package com.mangregory.asgardshieldreloaded.util.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.mangregory.asgardshieldreloaded.items.ItemAsgardShield;
import com.mangregory.asgardshieldreloaded.items.ItemGiantSword;

// Courtesy of Fuzs
@Mod.EventBusSubscriber(value = Side.CLIENT)
public class EventHandlerClient
{
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Pre<AbstractClientPlayer> event)
    {
        if (event.getEntity() instanceof AbstractClientPlayer)
        {
            AbstractClientPlayer player = (AbstractClientPlayer) event.getEntity();
            if (player != null && player.isHandActive())
            {
                Item heldItem = player.getHeldItem(player.getActiveHand()).getItem();
                if (heldItem instanceof ItemAsgardShield || heldItem instanceof ItemGiantSword)
                {
                    ModelPlayer model = (ModelPlayer) event.getRenderer().getMainModel();
                    boolean left1 = (player.getActiveHand() == EnumHand.OFF_HAND && player.getPrimaryHand() == EnumHandSide.RIGHT);
                    boolean left2 = (player.getActiveHand() == EnumHand.MAIN_HAND && player.getPrimaryHand() == EnumHandSide.LEFT);
                    if (left1 || left2)
                    {
                        if (model.leftArmPose == ModelBiped.ArmPose.ITEM) model.leftArmPose = ModelBiped.ArmPose.BLOCK;
                    }
                    else if (model.rightArmPose == ModelBiped.ArmPose.ITEM)
                    {
                        model.rightArmPose = ModelBiped.ArmPose.BLOCK;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRenderHand(RenderSpecificHandEvent event)
    {
        EntityPlayerSP player = mc.player;
        if (player != null && player.isHandActive() && player.getActiveHand() == event.getHand())
        {
            ItemStack stack = event.getItemStack();
            if (player.getHeldItem(player.getActiveHand()).getItem() instanceof ItemGiantSword)
            {
                GlStateManager.pushMatrix();
                boolean rightHanded = (((event.getHand() == EnumHand.MAIN_HAND) ? player.getPrimaryHand() : player.getPrimaryHand().opposite()) == EnumHandSide.RIGHT);
                transformSideFirstPerson(rightHanded ? 1.0F : -1.0F, event.getEquipProgress());
                mc.getItemRenderer().renderItemSide(player, stack, rightHanded ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, !rightHanded);
                GlStateManager.popMatrix();
                event.setCanceled(true);
            }
        }
    }

    private static void transformSideFirstPerson(float side, float equippedProg)
    {
        GlStateManager.translate(side * 0.56F, -0.52F + equippedProg * -0.6F, -0.72F);
        GlStateManager.translate(side * -0.14142136F, 0.08F, 0.14142136F);
        GlStateManager.rotate(-102.25F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(side * 13.365F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(side * 78.05F, 0.0F, 0.0F, 1.0F);
    }
}