package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * German translations, ported from the 1.12.2 de_de.lang where the item still exists
 * and completed/corrected for modern items and changed perks.
 */
public class DeDeLangProvider extends FabricLanguageProvider {

    private record ShieldTooltip(String perk, String perkDesc, String weakness, String weaknessDesc) {}

    public DeDeLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "de_de", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder tb) {
        tb.add(AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), "Diamantriesenschwert");
        tb.add(AsgardShieldItems.IRON_GIANT_SWORD.get(), "Eisenriesenschwert");
        tb.add(AsgardShieldItems.GOLDEN_GIANT_SWORD.get(), "Goldriesenschwert");
        tb.add(AsgardShieldItems.STONE_GIANT_SWORD.get(), "Steinriesenschwert");
        tb.add(AsgardShieldItems.WOODEN_GIANT_SWORD.get(), "Holzriesenschwert");
        tb.add(AsgardShieldItems.ENDER_GIANT_SWORD.get(), "Enderriesenschwert");
        tb.add(AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get(), "Netherquarzriesenschwert");
        tb.add(AsgardShieldItems.SKULL_GIANT_SWORD.get(), "Skelettschädelriesenschwert");
        tb.add(AsgardShieldItems.COPPER_GIANT_SWORD.get(), "Kupferriesenschwert");
        tb.add(AsgardShieldItems.NETHERITE_GIANT_SWORD.get(), "Netheritriesenschwert");

        tb.add(AsgardShieldItems.NETHERITE_SHIELD.get(), "Netheritschild");
        tb.add(AsgardShieldItems.GILDED_NETHERITE_SHIELD.get(), "Vergoldeter Netheritschild");
        tb.add(AsgardShieldItems.COPPER_SHIELD.get(), "Kupferschild");
        tb.add(AsgardShieldItems.GILDED_COPPER_SHIELD.get(), "Vergoldeter Kupferschild");
        tb.add(AsgardShieldItems.DIAMOND_SHIELD.get(), "Diamantschild");
        tb.add(AsgardShieldItems.ENDER_SHIELD.get(), "Enderschild");
        tb.add(AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), "Vergoldeter Diamantschild");
        tb.add(AsgardShieldItems.GILDED_ENDER_SHIELD.get(), "Vergoldeter Enderschild");
        tb.add(AsgardShieldItems.GILDED_IRON_SHIELD.get(), "Vergoldeter Eisenschild");
        tb.add(AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), "Vergoldeter Netherquarzschild");
        tb.add(AsgardShieldItems.GILDED_SKULL_SHIELD.get(), "Vergoldeter Skelettschädelschild");
        tb.add(AsgardShieldItems.GILDED_STONE_SHIELD.get(), "Vergoldeter Steinschild");
        tb.add(AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), "Vergoldeter Holzschild");
        tb.add(AsgardShieldItems.IRON_SHIELD.get(), "Eisenschild");
        tb.add(AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), "Netherquarzschild");
        tb.add(AsgardShieldItems.SKULL_SHIELD.get(), "Skelettschädelschild");
        tb.add(AsgardShieldItems.STONE_SHIELD.get(), "Steinschild");
        tb.add(AsgardShieldItems.WOODEN_SHIELD.get(), "Holzschild");

        Map<Item, ShieldTooltip> tooltips = new LinkedHashMap<>();

        tooltips.put(AsgardShieldItems.NETHERITE_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Unbezwingbar",
                "Kann nicht durch Äxte deaktiviert werden. Brennt nicht im Feuer",
                "Schwäche: Keine",
                "Keine"
        ));
        tooltips.put(AsgardShieldItems.GILDED_NETHERITE_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Ewige Bastion",
                "Unbezwingbarer Vorteil plus immun gegen Schaden (20 Prozent Chance)",
                "Schwäche: Keine",
                "Keine"
        ));
        tooltips.put(AsgardShieldItems.DIAMOND_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Reflektieren",
                "Reflektiert Projektile zurück zum Angreifer (30 Prozent Chance)",
                "Schwäche: Verlagern",
                "Reflektierter Schaden beschädigt auch den Schild"
        ));
        tooltips.put(AsgardShieldItems.ENDER_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Enderverschiebung",
                "Teleportiert Angreifer vom Träger weg (20 Prozent Chance)",
                "Schwäche: Endtechnologie",
                "Enderbewohner können den Schutz umgehen"
        ));
        tooltips.put(AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Reflektieren",
                "Reflektiert Projektile zurück zum Angreifer (60 Prozent Chance)",
                "Schwäche: Verlagern",
                "Reflektierter Schaden beschädigt auch den Schild"
        ));
        tooltips.put(AsgardShieldItems.GILDED_ENDER_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Enderverschiebung",
                "Teleportiert Angreifer vom Träger weg (40 Prozent Chance)",
                "Schwäche: Endtechnologie",
                "Enderbewohner können den Schutz umgehen"
        ));
        tooltips.put(AsgardShieldItems.GILDED_IRON_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Unbewegliches Objekt",
                "Erhält weniger Explosions-Haltbarkeitsschaden",
                "Schwäche: Wasser",
                "Eisen rostet in feuchten Umgebungen und erhöht den erlittenen Haltbarkeitsschaden"
        ));
        tooltips.put(AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Flammenbewahrer",
                "Ermöglicht dem Träger, Feuerladungen von kleinen Feuerbällen einzusammeln (50 Prozent Chance)",
                "Schwäche: Hunger",
                "Die lebende Masse ernährt sich vom Träger und leert die Hungeranzeige"
        ));
        tooltips.put(AsgardShieldItems.GILDED_SKULL_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Gedankenkontrolle",
                "Kontrolliert den Angreifer (außer Bosse), sodass er andere Ziele angreift oder sich selbst tötet, wenn er bei 30 Prozent oder weniger der maximalen Gesundheit ist (30 Prozent Chance)",
                "Schwäche: Brüchige Knochen",
                "Erleidet x3 Haltbarkeitsschaden (10 Prozent Chance)"
        ));
        tooltips.put(AsgardShieldItems.GILDED_STONE_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Feuerschutz",
                "Löscht den Spieler, wenn er einen Angriff blockt, während er brennt",
                "Schwäche: Explosionen",
                "Stein bricht leicht bei starkem, überwältigendem Schaden"
        ));
        tooltips.put(AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Pfeilfang",
                "Ermöglicht dem Träger, Pfeile von Gegnern einzusammeln (50 Prozent Chance)",
                "Schwäche: Feuerschaden",
                "Feuer brennt schnell durch Holz, sei vorsichtig"
        ));
        tooltips.put(AsgardShieldItems.IRON_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Unbewegliches Objekt",
                "Erhält weniger Explosions-Haltbarkeitsschaden (50 Prozent Chance)",
                "Schwäche: Wasser",
                "Eisen rostet in feuchten Umgebungen und erhöht den erlittenen Haltbarkeitsschaden"
        ));
        tooltips.put(AsgardShieldItems.COPPER_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Leitfähigkeit",
                "Zieht bei Gewittern Blitzschläge an (10 Prozent Chance)",
                "Schwäche: Unisoliert",
                "Du bist nicht immun gegen Blitzschlag, sei vorsichtig"
        ));
        tooltips.put(AsgardShieldItems.GILDED_COPPER_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Leitfähigkeit",
                "Zieht bei Gewittern Blitzschläge an (20 Prozent Chance)",
                "Schwäche: Unisoliert",
                "Du bist nicht immun gegen Blitzschlag, sei vorsichtig"
        ));
        tooltips.put(AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Flammenbewahrer",
                "Ermöglicht dem Träger, Feuerladungen von kleinen Feuerbällen einzusammeln (25 Prozent Chance)",
                "Schwäche: Hunger",
                "Die lebende Masse ernährt sich vom Träger und leert die Hungeranzeige"
        ));
        tooltips.put(AsgardShieldItems.SKULL_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Gedankenkontrolle",
                "Kontrolliert den Angreifer (außer Bosse), sodass er andere Ziele angreift oder sich selbst tötet, wenn er bei 20 Prozent oder weniger der maximalen Gesundheit ist (15 Prozent Chance)",
                "Schwäche: Brüchige Knochen",
                "Erleidet x3 Haltbarkeitsschaden (10 Prozent Chance)"
        ));
        tooltips.put(AsgardShieldItems.STONE_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Feuerschutz",
                "Löscht den Spieler, wenn er einen Angriff blockt, während er brennt (50 Prozent Chance)",
                "Schwäche: Explosionen",
                "Stein bricht leicht bei starkem, überwältigendem Schaden"
        ));
        tooltips.put(AsgardShieldItems.WOODEN_SHIELD.get(), new ShieldTooltip(
                "Besonderer Vorteil: Pfeilfang",
                "Ermöglicht dem Träger, Pfeile von Gegnern einzusammeln (25 Prozent Chance)",
                "Schwäche: Feuerschaden",
                "Feuer brennt schnell durch Holz, sei vorsichtig"
        ));
        tooltips.forEach((item, spec) -> {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            String baseKey = "item." + id.getNamespace() + "." + id.getPath();
            tb.add(baseKey + ".perk", spec.perk);
            tb.add(baseKey + ".perk.desc", spec.perkDesc);
            tb.add(baseKey + ".weakness", spec.weakness);
            tb.add(baseKey + ".weakness.desc", spec.weaknessDesc);
        });
        tb.add("config.asr.title", "Asgard Shield: Reloaded Konfiguration");
        tb.add("config.asr.description", "(Betrifft nur clientseitige Werte)");
        tb.add("config.asr.giant_sword_block_duration", "Riesenschwert-Blockdauer");
        tb.add("config.asr.asgard_shield_block_duration", "Asgard-Schild-Blockdauer");
        tb.add("config.asr.giant_sword_base_knockback", "Riesenschwert-Grundrückstoß");
        tb.add("config.asr.asgard_shield_base_knockback", "Asgard-Schild-Grundrückstoß");
        tb.add("config.asr.enable_giant_sword_equip_sound", "Riesenschwert-Ausrüstungsgeräusch aktivieren");
        tb.add("config.asr.enable_asgard_shield_equip_sound", "Asgard-Schild-Ausrüstungsgeräusch aktivieren");
        tb.add("config.asr.giant_sword_base_itemdamage", "Riesenschwert-Grundschaden");
        tb.add("config.asr.asgard_shield_base_itemdamage", "Asgard-Schild-Grundschaden");
    }
}
