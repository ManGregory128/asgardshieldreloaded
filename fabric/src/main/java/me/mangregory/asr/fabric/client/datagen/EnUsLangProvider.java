package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class EnUsLangProvider extends FabricLanguageProvider {

    private record ShieldTooltip(String perk, String perkDesc, String weakness, String weaknessDesc) {}

    public EnUsLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder tb) {
        tb.add(AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), "Diamond Giant Sword");
        tb.add(AsgardShieldItems.IRON_GIANT_SWORD.get(), "Iron Giant Sword");
        tb.add(AsgardShieldItems.GOLDEN_GIANT_SWORD.get(), "Golden Giant Sword");
        tb.add(AsgardShieldItems.STONE_GIANT_SWORD.get(), "Stone Giant Sword");
        tb.add(AsgardShieldItems.WOODEN_GIANT_SWORD.get(), "Wooden Giant Sword");
        tb.add(AsgardShieldItems.ENDER_GIANT_SWORD.get(), "Ender Giant Sword");
        tb.add(AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get(), "Nether Quartz Giant Sword");
        tb.add(AsgardShieldItems.SKULL_GIANT_SWORD.get(), "Skull Giant Sword");

        tb.add(AsgardShieldItems.DIAMOND_SHIELD.get(), "Diamond Shield");
        tb.add(AsgardShieldItems.ENDER_SHIELD.get(), "Ender Shield");
        tb.add(AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), "Gilded Diamond Shield");
        tb.add(AsgardShieldItems.GILDED_ENDER_SHIELD.get(), "Gilded Ender Shield");
        tb.add(AsgardShieldItems.GILDED_IRON_SHIELD.get(), "Gilded Iron Shield");
        tb.add(AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), "Gilded Nether Quartz Shield");
        tb.add(AsgardShieldItems.GILDED_SKULL_SHIELD.get(), "Gilded Skull Shield");
        tb.add(AsgardShieldItems.GILDED_STONE_SHIELD.get(), "Gilded Stone Shield");
        tb.add(AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), "Gilded Wooden Shield");
        tb.add(AsgardShieldItems.IRON_SHIELD.get(), "Iron Shield");
        tb.add(AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), "Nether Quartz Shield");
        tb.add(AsgardShieldItems.SKULL_SHIELD.get(), "Skull Shield");
        tb.add(AsgardShieldItems.STONE_SHIELD.get(), "Stone Shield");
        tb.add(AsgardShieldItems.WOODEN_SHIELD.get(), "Wooden Shield");

        Map<Item, ShieldTooltip> tooltips = new LinkedHashMap<>();

        tooltips.put(AsgardShieldItems.DIAMOND_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Reflect",
                "Reflects projectiles back to the attacker (30 percent chance)",
                "Weakness: Displace",
                "Reflected damage also damages the shield"
        ));

        tooltips.put(AsgardShieldItems.ENDER_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Ender Shift",
                "Teleports attackers away from the wielder (20 percent chance)",
                "Weakness: End Tech",
                "Ender folk can bypass the guard"
        ));

        tooltips.put(AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Reflect",
                "Reflects projectiles back to the attacker (60 percent chance)",
                "Weakness: Displace",
                "Reflected damage also damages the shield"
        ));

        tooltips.put(AsgardShieldItems.GILDED_ENDER_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Ender Shift",
                "Teleports attackers away from the wielder (40 percent chance)",
                "Weakness: End Tech",
                "Ender folk can bypass the guard"
        ));

        tooltips.put(AsgardShieldItems.GILDED_IRON_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Unmovable Object",
                "Receives less explosion durability damage",
                "Weakness: Water",
                "Iron begins to rust in wet environments, increasing durability damage taken"
        ));

        tooltips.put(AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Flame Keeper",
                "Allows the wielder to collect fire charges from small fireballs (50 percent chance)",
                "Weakness: Hungering",
                "The living mass feeds on the wielder, drains hunger gauge"
        ));

        tooltips.put(AsgardShieldItems.GILDED_SKULL_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Mind Control",
                "Controls attacker (excluding bosses) to attack other targets or kill self when at 30 percent of max health or less (30 percent chance)",
                "Weakness: Brittle Bones",
                "Receives x3 durability damage (10 percent chance)"
        ));

        tooltips.put(AsgardShieldItems.GILDED_STONE_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Fire Protection",
                "Negates durability damage from fire sources",
                "Weakness: Explosions",
                "Stone breaks easily from strong overwhelming damage"
        ));

        tooltips.put(AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Arrow Catch",
                "Allows the wielder to collect arrows from enemies (50 percent chance)",
                "Weakness: Fire Damage",
                "Fire burns quickly through wood, be careful"
        ));

        tooltips.put(AsgardShieldItems.IRON_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Unmovable Object",
                "Receives less explosion durability damage (50 percent chance)",
                "Weakness: Water",
                "Iron begins to rust in wet environments, increasing durability damage taken"
        ));

        tooltips.put(AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Flame Keeper",
                "Allows the wielder to collect fire charges from small fireballs (25 percent chance)",
                "Weakness: Hungering",
                "The living mass feeds on the wielder, drains hunger gauge"
        ));

        tooltips.put(AsgardShieldItems.SKULL_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Mind Control",
                "Controls attacker (excluding bosses) to attack other targets or kil self when at 20 percent of max health or less (15 percent chance)",
                "Weakness: Brittle Bones",
                "Receives x3 durability damage (10 percent chance)"
        ));

        tooltips.put(AsgardShieldItems.STONE_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Fire Protection",
                "Negates durability damage from fire sources (50 percent chance)",
                "Weakness: Explosions",
                "Stone breaks easily from strong overwhelming damage"
        ));

        tooltips.put(AsgardShieldItems.WOODEN_SHIELD.get(), new ShieldTooltip(
                "Special Perk: Arrow Catch",
                "Allows the wielder to collect arrows from enemies (25 percent chance)",
                "Weakness: Fire Damage",
                "Fire burns quickly through wood, be careful"
        ));

        tooltips.forEach((item, spec) -> {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            String baseKey = "item." + id.getNamespace() + "." + id.getPath();
            tb.add(baseKey + ".perk", spec.perk);
            tb.add(baseKey + ".perk.desc", spec.perkDesc);
            tb.add(baseKey + ".weakness", spec.weakness);
            tb.add(baseKey + ".weakness.desc", spec.weaknessDesc);
        });

        tb.add("config.asr.title", "Asgard Shield: Reloaded Config");
        tb.add("config.asr.description", "(Affects client-side values only)");
        tb.add("config.asr.giant_sword_block_duration", "Giant Sword Block Duration");
        tb.add("config.asr.asgard_shield_block_duration", "Asgard Shield Block Duration");
        tb.add("config.asr.giant_sword_base_knockback", "Giant Sword Base Knockback");
        tb.add("config.asr.asgard_shield_base_knockback", "Asgard Shield Base Knockback");
        tb.add("config.asr.enable_giant_sword_equip_sound", "Enable Giant Sword Equip Sound");
        tb.add("config.asr.enable_asgard_shield_equip_sound", "Enable Asgard Shield Equip Sound");
        tb.add("config.asr.giant_sword_base_itemdamage", "Giant Sword Base Item Damage");
        tb.add("config.asr.asgard_shield_base_itemdamage", "Asgard Shield Base Item Damage");
    }
}
