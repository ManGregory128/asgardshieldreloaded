package me.mangregory.asr.mixin;

import me.mangregory.asr.item.GiantSwordItem;
import me.mangregory.asr.util.handlers.EventHandler;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Courtesy of Fuzs
@Mixin(HumanoidModel.class)
abstract class HumanoidModelMixin<T extends LivingEntity> extends AgeableListModel<T> {
    @Shadow
    public ModelPart rightArm;
    @Shadow
    public ModelPart leftArm;

    @Inject(method = "setupAnim", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/HumanoidModel;setupAttackAnimation(Lnet/minecraft/world/entity/LivingEntity;F)V"))
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
        if (entityIn instanceof Player player) {
            if (EventHandler.isActiveItemStackBlocking(player) && player.getUseItem().getItem() instanceof GiantSwordItem) {
                if (entityIn.getUsedItemHand() == InteractionHand.OFF_HAND) {
                    this.leftArm.xRot = this.leftArm.xRot - ((float) Math.PI * 2.0F) / 10.0F;
                    this.leftArm.yRot = ((float) Math.PI / 6.0F);

                } else {
                    this.rightArm.xRot = this.rightArm.xRot - ((float) Math.PI * 2.0F) / 10.0F;
                    this.rightArm.yRot = ((float) -Math.PI / 6.0F);

                }
            }
        }
    }
}