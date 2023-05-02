package me.mangregory.asr.item;

import net.minecraft.world.item.ShieldItem;

public class AsgardShieldItem extends ShieldItem {
    public int cooldown;
    public boolean isBlocking;
    public AsgardShieldItem(Properties p_43089_) {
        super(p_43089_);
        this.cooldown = 0;
        this.isBlocking = false;
    }

}
