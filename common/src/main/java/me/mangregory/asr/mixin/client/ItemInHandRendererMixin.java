package me.mangregory.asr.mixin.client;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
abstract class ItemInHandRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    private ItemStack mainHandItem;

    @Shadow
    private ItemStack offHandItem;

    @Inject(method = "itemUsed", at = @At("HEAD"), cancellable = true)
    public void itemUsed(InteractionHand interactionHand, CallbackInfo callback) {
        // Don't play the reequip animation when beginning to use an item, like shield or bow
        if (this.minecraft.player.isUsingItem() && this.minecraft.player.getUsedItemHand() == interactionHand) {
            if (this.minecraft.player.getUseItem().getItem() instanceof GiantSwordItem)
                callback.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void suppressReequipOnDurabilityChange(CallbackInfo ci) {
        if (this.minecraft.player == null) return;
        ItemStack currentMain = this.minecraft.player.getMainHandItem();
        ItemStack currentOff = this.minecraft.player.getOffhandItem();
        // Update stored references when only durability changed for mod items,
        // preventing the re-equip animation from playing after blocking
        if (isModItemWithSameIdentity(this.mainHandItem, currentMain)) {
            this.mainHandItem = currentMain;
        }
        if (isModItemWithSameIdentity(this.offHandItem, currentOff)) {
            this.offHandItem = currentOff;
        }
    }

    private static boolean isModItemWithSameIdentity(ItemStack old, ItemStack current) {
        if (old.isEmpty() || current.isEmpty()) return false;
        Item item = current.getItem();
        if (!(item instanceof GiantSwordItem) && !(item instanceof AsgardShieldItem)) return false;
        return ItemStack.isSameItem(old, current);
    }
}