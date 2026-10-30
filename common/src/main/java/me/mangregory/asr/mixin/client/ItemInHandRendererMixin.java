package me.mangregory.asr.mixin.client;
import me.mangregory.asr.items.GiantSwordItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.FirstPersonHandsAndItems;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Courtesy of Fuzs
@Mixin(FirstPersonHandsAndItems.class)
abstract class ItemInHandRendererMixin {
    @Inject(method = "itemUsed", at = @At("HEAD"), cancellable = true)
    public void itemUsed(InteractionHand interactionHand, CallbackInfo callback) {
        // don't play the reequip animation when beginning to use an item, like shield or bow
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player.isUsingItem() && minecraft.player.getUsedItemHand() == interactionHand) {
            if (minecraft.player.getUseItem().getItem() instanceof GiantSwordItem)
                callback.cancel();
        }
    }
}