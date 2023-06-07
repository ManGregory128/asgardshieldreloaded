package me.mangregory.asr.util.handlers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

// Courtesy of Fuzs
public class SwordBlockingRenderer {
    @SubscribeEvent
    public void onRenderHand(final RenderHandEvent evt) {
        final Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player.getUsedItemHand() == evt.getHand() && EventHandlerClient.isActiveItemStackBlocking(player)) {
            ItemInHandRenderer itemRenderer = minecraft.getEntityRenderDispatcher().getItemInHandRenderer();
            PoseStack matrixStack = evt.getPoseStack();
            matrixStack.pushPose();
            boolean isMainHand = evt.getHand() == InteractionHand.MAIN_HAND;
            HumanoidArm handSide = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            boolean isHandSideRight = handSide == HumanoidArm.RIGHT;
            //GAME CRASHES AT EACH OF THE TWO LINES BELOW:
            applyItemArmTransform(matrixStack, handSide, evt.getEquipProgress());
            applyItemArmAttackTransform(matrixStack, handSide, evt.getSwingProgress());
            this.transformBlockFirstPerson(matrixStack, handSide);
            itemRenderer.renderItem(player, evt.getItemStack(), isHandSideRight ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !isHandSideRight, matrixStack, evt.getMultiBufferSource(), evt.getPackedLight());
            matrixStack.popPose();
            evt.setCanceled(true);
        }
    }

    private void transformBlockFirstPerson(PoseStack matrixStack, HumanoidArm hand) {
        int signum = hand == HumanoidArm.RIGHT ? 1 : -1;
        // values taken from Minecraft snapshot 15w33b
        matrixStack.translate(signum * -0.14142136F, 0.08F, 0.14142136F);
        matrixStack.mulPose(Axis.XP.rotationDegrees(-102.25F));
        matrixStack.mulPose(Axis.YP.rotationDegrees(signum * 13.365F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(signum * 78.05F));
    }

    //the two functions below are implemented from ItemRenderer:
    private void applyItemArmTransform(PoseStack poseStack, HumanoidArm arm, float p_109385_) {
        int i = arm == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate((float)i * 0.56F, -0.52F + p_109385_ * -0.6F, -0.72F);
    }

    private void applyItemArmAttackTransform(PoseStack poseStack, HumanoidArm arm, float p_109338_) {
        int i = arm == HumanoidArm.RIGHT ? 1 : -1;
        float f = Mth.sin(p_109338_ * p_109338_ * (float)Math.PI);
        poseStack.mulPose(Axis.YP.rotationDegrees((float)i * (45.0F + f * -20.0F)));
        float f1 = Mth.sin(Mth.sqrt(p_109338_) * (float)Math.PI);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float)i * f1 * -20.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(f1 * -80.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees((float)i * -45.0F));
    }
}
