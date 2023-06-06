package me.mangregory.asr.util.handlers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// Courtesy of Fuzs
@Mixin(ItemInHandRenderer.class)
public interface ItemInHandRendererAccessor {

    @Invoker
    void callApplyItemArmAttackTransform(PoseStack matrixStackIn, HumanoidArm handIn, float swingProgress);

    @Invoker
    void callApplyItemArmTransform(PoseStack matrixStackIn, HumanoidArm handIn, float equippedProg);
}
