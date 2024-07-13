package me.mangregory.asr.util.handlers;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import me.mangregory.asr.item.GiantSwordItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.bus.api.SubscribeEvent;


// Courtesy of Fuzs
@EventBusSubscriber(value = Dist.CLIENT, modid = "asr")
public class FirstPersonRenderingHandler {
    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent evt) {
        final Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player != null && player.isUsingItem() && player.getUsedItemHand() == evt.getHand() && player.getItemInHand(player.getUsedItemHand()).getItem() instanceof GiantSwordItem) {
            ItemInHandRenderer itemRenderer = minecraft.getEntityRenderDispatcher().getItemInHandRenderer();
            PoseStack matrixStack = evt.getPoseStack();
            matrixStack.pushPose();
            boolean isMainHand = evt.getHand() == InteractionHand.MAIN_HAND;
            HumanoidArm handSide = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            boolean isHandSideRight = handSide == HumanoidArm.RIGHT;
            applyItemArmTransform(matrixStack, handSide, evt.getEquipProgress());
            transformBlockFirstPerson(matrixStack, handSide);
            itemRenderer.renderItem(player, evt.getItemStack(), isHandSideRight ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !isHandSideRight, matrixStack, evt.getMultiBufferSource(), evt.getPackedLight());
            matrixStack.popPose();
            evt.setCanceled(true);
        }
    }

    /**
     * Transforms the item in the first person's view based on the given parameters.
     *
     * @param  matrixStack the PoseStack containing the transformation matrix
     * @param  hand        the HumanoidArm representing the hand used for transformation
     */
    private static void transformBlockFirstPerson(PoseStack matrixStack, HumanoidArm hand) {
        int signum = hand == HumanoidArm.RIGHT ? 1 : -1;
        // values taken from Minecraft snapshot 15w33b
        matrixStack.translate(signum * -0.14142136F, 0.08F, 0.14142136F);
        matrixStack.mulPose(Axis.XP.rotationDegrees(-102.25F));
        matrixStack.mulPose(Axis.YP.rotationDegrees(signum * 13.365F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(signum * 78.05F));
    }

    /**
     * Transforms the player arm in the first person's view based on the given parameters.
     *
     * @param  poseStack         the PoseStack containing the transformation matrix
     * @param  arm               the HumanoidArm representing the hand used for transformation
     * @param  equipAnimProgress the equip animation progress (0.0 to 1.0)
     */
    //the function below is implemented from ItemRenderer:
    private static void applyItemArmTransform(PoseStack poseStack, HumanoidArm arm, float equipAnimProgress) {
        int i = arm == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate(i * 0.56F, -0.52F + equipAnimProgress * -0.6F, -0.72F);
    }
}
