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
 * Greek translations, mirroring the English keys 1:1.
 */
public class ElGrLangProvider extends FabricLanguageProvider {

    private record ShieldTooltip(String perk, String perkDesc, String weakness, String weaknessDesc) {}

    public ElGrLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "el_gr", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder tb) {
        tb.add(AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), "Διαμαντένιο Γιγαντιαίο Σπαθί");
        tb.add(AsgardShieldItems.IRON_GIANT_SWORD.get(), "Σιδερένιο Γιγαντιαίο Σπαθί");
        tb.add(AsgardShieldItems.GOLDEN_GIANT_SWORD.get(), "Χρυσό Γιγαντιαίο Σπαθί");
        tb.add(AsgardShieldItems.STONE_GIANT_SWORD.get(), "Πέτρινο Γιγαντιαίο Σπαθί");
        tb.add(AsgardShieldItems.WOODEN_GIANT_SWORD.get(), "Ξύλινο Γιγαντιαίο Σπαθί");
        tb.add(AsgardShieldItems.ENDER_GIANT_SWORD.get(), "Γιγαντιαίο Σπαθί του Τελειωτή");
        tb.add(AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get(), "Γιγαντιαίο Σπαθί Χαλαζία Εγκάτων");
        tb.add(AsgardShieldItems.SKULL_GIANT_SWORD.get(), "Γιγαντιαίο Σπαθί Κρανίου");
        tb.add(AsgardShieldItems.COPPER_GIANT_SWORD.get(), "Χάλκινο Γιγαντιαίο Σπαθί");
        tb.add(AsgardShieldItems.NETHERITE_GIANT_SWORD.get(), "Γιγαντιαίο Σπαθί από Εγκατίτη");

        tb.add(AsgardShieldItems.NETHERITE_SHIELD.get(), "Ασπίδα από Εγκατίτη");
        tb.add(AsgardShieldItems.GILDED_NETHERITE_SHIELD.get(), "Επιχρυσωμένη Ασπίδα από Εγκατίτη");
        tb.add(AsgardShieldItems.COPPER_SHIELD.get(), "Χάλκινη Ασπίδα");
        tb.add(AsgardShieldItems.GILDED_COPPER_SHIELD.get(), "Επιχρυσωμένη Χάλκινη Ασπίδα");
        tb.add(AsgardShieldItems.DIAMOND_SHIELD.get(), "Διαμαντένια Ασπίδα");
        tb.add(AsgardShieldItems.ENDER_SHIELD.get(), "Ασπίδα του Τελειωτή");
        tb.add(AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), "Επιχρυσωμένη Διαμαντένια Ασπίδα");
        tb.add(AsgardShieldItems.GILDED_ENDER_SHIELD.get(), "Επιχρυσωμένη Ασπίδα του Τελειωτή");
        tb.add(AsgardShieldItems.GILDED_IRON_SHIELD.get(), "Επιχρυσωμένη Σιδερένια Ασπίδα");
        tb.add(AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), "Επιχρυσωμένη Ασπίδα Χαλαζία Εγκάτων");
        tb.add(AsgardShieldItems.GILDED_SKULL_SHIELD.get(), "Επιχρυσωμένη Ασπίδα Κρανίου");
        tb.add(AsgardShieldItems.GILDED_STONE_SHIELD.get(), "Επιχρυσωμένη Πέτρινη Ασπίδα");
        tb.add(AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), "Επιχρυσωμένη Ξύλινη Ασπίδα");
        tb.add(AsgardShieldItems.IRON_SHIELD.get(), "Σιδερένια Ασπίδα");
        tb.add(AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), "Ασπίδα Χαλαζία Εγκάτων");
        tb.add(AsgardShieldItems.SKULL_SHIELD.get(), "Ασπίδα Κρανίου");
        tb.add(AsgardShieldItems.STONE_SHIELD.get(), "Πέτρινη Ασπίδα");
        tb.add(AsgardShieldItems.WOODEN_SHIELD.get(), "Ξύλινη Ασπίδα");

        Map<Item, ShieldTooltip> tooltips = new LinkedHashMap<>();

        tooltips.put(AsgardShieldItems.NETHERITE_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Αλύγιστο",
                "Δεν απενεργοποιείται από τσεκούρια. Δεν καίγεται στη φωτιά",
                "Αδυναμία: Καμία",
                "Καμία"
        ));
        tooltips.put(AsgardShieldItems.GILDED_NETHERITE_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Αιώνιο Οχυρό",
                "Αλύγιστη ικανότητα συν ανοσία σε ζημιά (πιθανότητα 20%)",
                "Αδυναμία: Καμία",
                "Καμία"
        ));
        tooltips.put(AsgardShieldItems.DIAMOND_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Ανάκλαση",
                "Αντανακλά βλήματα πίσω στον επιτιθέμενο (πιθανότητα 30%)",
                "Αδυναμία: Μετατόπιση",
                "Η ανάκλαση βλάπτει και την ασπίδα"
        ));
        tooltips.put(AsgardShieldItems.ENDER_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Μετατόπιση Τελειωτή",
                "Τηλεμεταφέρει επιτιθέμενους μακριά από τον κάτοχο (πιθανότητα 20%)",
                "Αδυναμία: Τεχνολογία Τελειωτή",
                "Οι κάτοικοι του Τελειωτή μπορούν να παρακάμψουν την προστασία"
        ));
        tooltips.put(AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Ανάκλαση",
                "Αντανακλά βλήματα πίσω στον επιτιθέμενο (πιθανότητα 60%)",
                "Αδυναμία: Μετατόπιση",
                "Η ανάκλαση βλάπτει και την ασπίδα"
        ));
        tooltips.put(AsgardShieldItems.GILDED_ENDER_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Μετατόπιση Τελειωτή",
                "Τηλεμεταφέρει επιτιθέμενους μακριά από τον κάτοχο (πιθανότητα 40%)",
                "Αδυναμία: Τεχνολογία Τελειωτή",
                "Οι κάτοικοι του Τελειωτή μπορούν να παρακάμψουν την προστασία"
        ));
        tooltips.put(AsgardShieldItems.GILDED_IRON_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Ακίνητο Αντικείμενο",
                "Λαμβάνει λιγότερη ζημιά από εκρήξεις",
                "Αδυναμία: Νερό",
                "Ο σίδηρος σκουριάζει σε υγρά περιβάλλοντα, αυξάνοντας τη ζημιά αντοχής"
        ));
        tooltips.put(AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Φύλακας Φλόγας",
                "Επιτρέπει στον κάτοχο να συλλέγει φλογοβόλα από μικρές πύρινες μπάλες (πιθανότητα 50%)",
                "Αδυναμία: Πείνα",
                "Η ζωντανή μάζα τρέφεται από τον κάτοχο και αδειάζει τον μετρητή πείνας"
        ));
        tooltips.put(AsgardShieldItems.GILDED_SKULL_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Έλεγχος Νου",
                "Ελέγχει τον επιτιθέμενο (εκτός από αρχηγούς) ώστε να επιτεθεί σε άλλα πλάσματα ή να σκοτωθεί αν έχει 30% ή λιγότερη της μέγιστης υγείας του (πιθανότητα 30%)",
                "Αδυναμία: Εύθραυστα Οστά",
                "Υφίσταται x3 ζημιά αντοχής (πιθανότητα 10%)"
        ));
        tooltips.put(AsgardShieldItems.GILDED_STONE_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Προστασία από Φωτιά",
                "Σβήνει τον παίκτη όταν αποκρούει επίθεση ενώ καίγεται",
                "Αδυναμία: Εκρήξεις",
                "Η πέτρα σπάει εύκολα από ισχυρή, συντριπτική ζημιά"
        ));
        tooltips.put(AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Πιάσιμο Βελών",
                "Επιτρέπει στον κάτοχο να συλλέγει βέλη από εχθρούς (πιθανότητα 50%)",
                "Αδυναμία: Ζημιά από Φωτιά",
                "Η φωτιά καίει γρήγορα το ξύλο, πρόσεχε"
        ));
        tooltips.put(AsgardShieldItems.IRON_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Ακίνητο Αντικείμενο",
                "Λαμβάνει λιγότερη ζημιά από εκρήξεις (πιθανότητα 50%)",
                "Αδυναμία: Νερό",
                "Ο σίδηρος σκουριάζει σε υγρά περιβάλλοντα, αυξάνοντας τη ζημιά αντοχής"
        ));
        tooltips.put(AsgardShieldItems.COPPER_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Αγωγιμότητα",
                "Προσελκύει κεραυνούς κατά τις καταιγίδες (πιθανότητα 10%)",
                "Αδυναμία: Μη Μονωμένο",
                "Δεν είσαι άτρωτος στους κεραυνούς, πρόσεχε"
        ));
        tooltips.put(AsgardShieldItems.GILDED_COPPER_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Αγωγιμότητα",
                "Προσελκύει κεραυνούς κατά τις καταιγίδες (πιθανότητα 20%)",
                "Αδυναμία: Μη Μονωμένο",
                "Δεν είσαι άτρωτος στους κεραυνούς, πρόσεχε"
        ));
        tooltips.put(AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Φύλακας Φλόγας",
                "Επιτρέπει στον κάτοχο να συλλέγει φλογοβόλα από μικρές πύρινες μπάλες (πιθανότητα 25%)",
                "Αδυναμία: Πείνα",
                "Η ζωντανή μάζα τρέφεται από τον κάτοχο και αδειάζει τον μετρητή πείνας"
        ));
        tooltips.put(AsgardShieldItems.SKULL_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Έλεγχος Νου",
                "Ελέγχει τον επιτιθέμενο (εκτός από αρχηγούς) ώστε να επιτεθεί σε άλλα πλάσματα ή να σκοτωθεί αν έχει 20% ή λιγότερη της μέγιστης υγείας του (πιθανότητα 15%)",
                "Αδυναμία: Εύθραυστα Οστά",
                "Υφίσταται x3 ζημιά αντοχής (πιθανότητα 10%)"
        ));
        tooltips.put(AsgardShieldItems.STONE_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Προστασία από Φωτιά",
                "Σβήνει τον παίκτη όταν αποκρούει επίθεση ενώ καίγεται (πιθανότητα 50%)",
                "Αδυναμία: Εκρήξεις",
                "Η πέτρα σπάει εύκολα από ισχυρή, συντριπτική ζημιά"
        ));
        tooltips.put(AsgardShieldItems.WOODEN_SHIELD.get(), new ShieldTooltip(
                "Ειδική Ικανότητα: Πιάσιμο Βελών",
                "Επιτρέπει στον κάτοχο να συλλέγει βέλη από εχθρούς (πιθανότητα 25%)",
                "Αδυναμία: Ζημιά από Φωτιά",
                "Η φωτιά καίει γρήγορα το ξύλο, πρόσεχε"
        ));
        tooltips.forEach((item, spec) -> {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            String baseKey = "item." + id.getNamespace() + "." + id.getPath();
            tb.add(baseKey + ".perk", spec.perk);
            tb.add(baseKey + ".perk.desc", spec.perkDesc);
            tb.add(baseKey + ".weakness", spec.weakness);
            tb.add(baseKey + ".weakness.desc", spec.weaknessDesc);
        });
        tb.add("config.asr.title", "Διαμόρφωση Asgard Shield: Reloaded");
        tb.add("config.asr.description", "(Επηρεάζει μόνο τιμές από την πλευρά του πελάτη)");
        tb.add("config.asr.giant_sword_block_duration", "Διάρκεια αποκλεισμού Γιγαντιαίου Σπαθιού");
        tb.add("config.asr.asgard_shield_block_duration", "Διάρκεια αποκλεισμού Ασπίδας Asgard");
        tb.add("config.asr.giant_sword_base_knockback", "Βασική ώθηση Γιγαντιαίου Σπαθιού");
        tb.add("config.asr.asgard_shield_base_knockback", "Βασική ώθηση Ασπίδας Asgard");
        tb.add("config.asr.enable_giant_sword_equip_sound", "Ενεργοποίηση ήχου εξοπλισμού Γιγαντιαίου Σπαθιού");
        tb.add("config.asr.enable_asgard_shield_equip_sound", "Ενεργοποίηση ήχου εξοπλισμού Ασπίδας Asgard");
        tb.add("config.asr.giant_sword_base_itemdamage", "Βασική ζημιά Γιγαντιαίου Σπαθιού");
        tb.add("config.asr.asgard_shield_base_itemdamage", "Βασική ζημιά Ασπίδας Asgard");
    }
}
