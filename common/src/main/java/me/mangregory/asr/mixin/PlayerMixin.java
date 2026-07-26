package me.mangregory.asr.mixin;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
abstract class PlayerMixin {

    @Redirect(method = "blockUsingItem",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;getSecondsToDisableBlocking()F"
            )
    )
    private float noDisableForStrongestShield(LivingEntity attacker) {
        Player self = (Player) (Object) this;
        ItemStack blockingWith = self.getItemBlockingWith();

        if (blockingWith != null && (blockingWith.is(AsgardShieldItems.NETHERITE_SHIELD.get()) ||
                blockingWith.is(AsgardShieldItems.GILDED_NETHERITE_SHIELD.get()) ||
                blockingWith.is(AsgardShieldItems.NETHERITE_GIANT_SWORD.get()))) {
            return 0.0F;
        }

        return attacker.getSecondsToDisableBlocking();
    }
}
