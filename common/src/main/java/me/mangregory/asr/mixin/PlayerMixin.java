package me.mangregory.asr.mixin;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
abstract class PlayerMixin {

    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    private void noDisableForStrongestShield(CallbackInfo ci) {
        Player self = (Player) (Object) this;
        ItemStack blockingWith = self.getUseItem();

        if (blockingWith != null && blockingWith.is(AsgardShieldItems.NETHERITE_SHIELD.get()) ||
        blockingWith.is(AsgardShieldItems.GILDED_NETHERITE_SHIELD.get()) ||
                blockingWith.is(AsgardShieldItems.NETHERITE_GIANT_SWORD.get())) {
            ci.cancel();
        }
    }
}
