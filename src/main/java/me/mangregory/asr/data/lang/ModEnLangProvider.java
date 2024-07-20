package me.mangregory.asr.data.lang;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLangProvider extends LanguageProvider {

    public ModEnLangProvider(PackOutput output) {
        super(output, AsgardShieldReloaded.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Item Names
        addItem(ItemInit.DIAMOND_GIANT_SWORD, "Diamond Giant Sword");
        addItem(ItemInit.IRON_GIANT_SWORD, "Iron Giant Sword");
        addItem(ItemInit.WOODEN_GIANT_SWORD, "Wooden Giant Sword");
        addItem(ItemInit.STONE_GIANT_SWORD, "Stone Giant Sword");
        addItem(ItemInit.GOLDEN_GIANT_SWORD, "Golden Giant Sword");
        addItem(ItemInit.ENDER_GIANT_SWORD, "Ender Giant Sword");
        addItem(ItemInit.WOODEN_SHIELD, "Wooden Shield");
        addItem(ItemInit.GILDED_WOODEN_SHIELD, "Gilded Wooden Shield");
        addItem(ItemInit.STONE_SHIELD, "Stone Shield");
        addItem(ItemInit.GILDED_STONE_SHIELD, "Gilded Stone Shield");
        addItem(ItemInit.IRON_SHIELD, "Iron Shield");
        addItem(ItemInit.GILDED_IRON_SHIELD, "Gilded Iron Shield");
        addItem(ItemInit.DIAMOND_SHIELD, "Diamond Shield");
        addItem(ItemInit.GILDED_DIAMOND_SHIELD, "Gilded Diamond Shield");
        addItem(ItemInit.NETHERQUARTZ_SHIELD, "Nether Quartz Shield");
        addItem(ItemInit.GILDED_NETHERQUARTZ_SHIELD, "Gilded Nether Quartz Shield");
        addItem(ItemInit.SKULL_SHIELD, "Skull Shield");
        addItem(ItemInit.GILDED_SKULL_SHIELD, "Gilded Skull Shield");
        addItem(ItemInit.ENDER_SHIELD, "Ender Shield");
        addItem(ItemInit.GILDED_ENDER_SHIELD, "Gilded Ender Shield");

        // Item ToolTips
        add("tooltip.asr.category.duration", "Max Use Duration");

        add("item.asr.diamond_shield.perk", "Special Perk: Reflect");
        add("item.asr.diamond_shield.perk.desc", "Reflects projectiles back to the attacker (30 percent chance)");
        add("item.asr.diamond_shield.weakness", "Weakness: Displace");
        add("item.asr.diamond_shield.weakness.desc", "Reflected damage also damages the shield");

        add("item.asr.gilded_diamond_shield.perk", "Special Perk: Reflect");
        add("item.asr.gilded_diamond_shield.perk.desc", "Reflects projectiles back to the attacker (60 percent chance)");
        add("item.asr.gilded_diamond_shield.weakness", "Weakness: Displace");
        add("item.asr.gilded_diamond_shield.weakness.desc", "Reflected damage also damages the shield");

        add("item.asr.ender_shield.perk", "Special Perk: Ender Shift");
        add("item.asr.ender_shield.perk.desc", "Teleports attackers away from the wielder (20 percent chance)");
        add("item.asr.ender_shield.weakness", "Weakness: End Tech");
        add("item.asr.ender_shield.weakness.desc", "Ender folk can bypass the guard");

        add("item.asr.gilded_ender_shield.perk", "Special Perk: Ender Shift");
        add("item.asr.gilded_ender_shield.perk.desc", "Teleports attackers away from the wielder (40 percent chance)");
        add("item.asr.gilded_ender_shield.weakness", "Weakness: End Tech");
        add("item.asr.gilded_ender_shield.weakness.desc", "Ender folk can bypass the guard");

        add("item.asr.golden_shield.perk", "Special Perk: Paladin's Ward");
        add("item.asr.golden_shield.perk.desc", "Heals the wielder for half a heart plus 4 percent of the attacker's max health (20 percent chance)");
        add("item.asr.golden_shield.weakness", "Weakness: Paladin's Oath");
        add("item.asr.golden_shield.weakness.desc", "Knockback is halved against non-undead attackers");

        add("item.asr.blessed_golden_shield.perk", "Special Perk: Paladin's Ward");
        add("item.asr.blessed_golden_shield.perk.desc", "Heals the wielder for half a heart plus 5 percent of the attacker's max health (30 percent chance)");
        add("item.asr.blessed_golden_shield.weakness", "Weakness: Paladin's Oath");
        add("item.asr.blessed_golden_shield.weakness.desc", "Knockback is halved against non-undead attackers");

        add("item.asr.iron_shield.perk", "Special Perk: Unmovable Object");
        add("item.asr.iron_shield.perk.desc", "Receives less explosion durability damage (50 percent chance)");
        add("item.asr.iron_shield.weakness", "Weakness: Water");
        add("item.asr.iron_shield.weakness.desc", "Iron begins to rust in wet environments, increasing durability damage taken");

        add("item.asr.gilded_iron_shield.perk", "Special Perk: Unmovable Object");
        add("item.asr.gilded_iron_shield.perk.desc", "Receives less explosion durability damage");
        add("item.asr.gilded_iron_shield.weakness", "Weakness: Water");
        add("item.asr.gilded_iron_shield.weakness.desc", "Iron begins to rust in wet environments, increasing durability damage taken");

        add("item.asr.netherquartz_shield.perk", "Special Perk: Flame Keeper");
        add("item.asr.netherquartz_shield.perk.desc", "Allows the wielder to collect fire charges from small fireballs (25 percent chance)");
        add("item.asr.netherquartz_shield.weakness", "Weakness: Hungering");
        add("item.asr.netherquartz_shield.weakness.desc", "The living mass feeds on the wielder, drains hunger gauge");

        add("item.asr.gilded_netherquartz_shield.perk", "Special Perk: Flame Keeper");
        add("item.asr.gilded_netherquartz_shield.perk.desc", "Allows the wielder to collect fire charges from small fireballs (50 percent chance)");
        add("item.asr.gilded_netherquartz_shield.weakness", "Weakness: Hungering");
        add("item.asr.gilded_netherquartz_shield.weakness.desc", "The living mass feeds on the wielder, drains hunger gauge");

        add("item.asr.patchwork_shield.perk", "Special Perk: Foulness");
        add("item.asr.patchwork_shield.perk.desc", "Inflicts attackers with nausea or weakness (10 percent chance)");
        add("item.asr.patchwork_shield.weakness", "Weakness: Foulness");
        add("item.asr.patchwork_shield.weakness.desc", "Inflicts wielder with nausea (5 percent chance)");

        add("item.asr.gilded_patchwork_shield.perk", "Special Perk: Foulness");
        add("item.asr.gilded_patchwork_shield.perk.desc", "Inflicts attackers with nausea or weakness (20 percent chance)");
        add("item.asr.gilded_patchwork_shield.weakness", "Weakness: Foulness");
        add("item.asr.gilded_patchwork_shield.weakness.desc", "Inflicts wielder with nausea (5 percent chance)");

        add("item.asr.skull_shield.perk", "Special Perk: Mind Control");
        add("item.asr.skull_shield.perk.desc", "Controls attacker (excluding bosses) to attack other targets or kil self when at 20 percent of max health or less (15 percent chance)");
        add("item.asr.skull_shield.weakness", "Weakness: Brittle Bones");
        add("item.asr.skull_shield.weakness.desc", "Receives x3 durability damage (10 percent chance)");

        add("item.asr.gilded_skull_shield.perk", "Special Perk: Mind Control");
        add("item.asr.gilded_skull_shield.perk.desc", "Controls attacker (excluding bosses) to attack other targets or kill self when at 30 percent of max health or less (30 percent chance)");
        add("item.asr.gilded_skull_shield.weakness", "Weakness: Brittle Bones");
        add("item.asr.gilded_skull_shield.weakness.desc", "Receives x3 durability damage (10 percent chance)");

        add("item.asr.stone_shield.perk", "Special Perk: Fire Protection");
        add("item.asr.stone_shield.perk.desc", "Negates durability damage from fire sources (50 percent chance)");
        add("item.asr.stone_shield.weakness", "Weakness: Explosions");
        add("item.asr.stone_shield.weakness.desc", "Stone breaks easily from strong overwhelming damage");

        add("item.asr.gilded_stone_shield.perk", "Special Perk: Fire Protection");
        add("item.asr.gilded_stone_shield.perk.desc", "Negates durability damage from fire sources");
        add("item.asr.gilded_stone_shield.weakness", "Weakness: Explosions");
        add("item.asr.gilded_stone_shield.weakness.desc", "Stone breaks easily from strong overwhelming damage");

        add("item.asr.wooden_shield.perk", "Special Perk: Arrow Catch");
        add("item.asr.wooden_shield.perk.desc", "Allows the wielder to collect arrows from enemies (25 percent chance)");
        add("item.asr.wooden_shield.weakness", "Weakness: Fire Damage");
        add("item.asr.wooden_shield.weakness.desc", "Fire burns quickly through wood, be careful");

        add("item.asr.gilded_wooden_shield.perk", "Special Perk: Arrow Catch");
        add("item.asr.gilded_wooden_shield.perk.desc", "Allows the wielder to collect arrows from enemies (50 percent chance)");
        add("item.asr.gilded_wooden_shield.weakness", "Weakness: Fire Damage");
        add("item.asr.gilded_wooden_shield.weakness.desc", "Fire burns quickly through wood, be careful");
    }
}
