package me.mangregory.util.handlers;

import dev.architectury.event.events.common.LootEvent;
import me.mangregory.items.init.AsgardShieldItems;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LootEventHandler {
    public static void registerEvents() {
        LootEvent.MODIFY_LOOT_TABLE.register((key, context, builtin) -> {
            if (BuiltInLootTables.END_CITY_TREASURE.equals(key)) {
                context.addPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0.0f, 1.0f))
                        .add(LootItem.lootTableItem(AsgardShieldItems.ENDER_SHIELD.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AsgardShieldItems.GILDED_ENDER_SHIELD.get()).setWeight(2)));
            }
            if (BuiltInLootTables.BASTION_TREASURE.equals(key)) {
                context.addPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0.0f, 1.0f))
                        .add(LootItem.lootTableItem(AsgardShieldItems.NETHERQUARTZ_SHIELD.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get()).setWeight(2)));
            }
            if (BuiltInLootTables.BURIED_TREASURE.equals(key)) {
                context.addPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0.0f, 1.0f))
                        .add(LootItem.lootTableItem(AsgardShieldItems.IRON_GIANT_SWORD.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AsgardShieldItems.GOLDEN_GIANT_SWORD.get()).setWeight(2)));
            }
            if (BuiltInLootTables.SPAWN_BONUS_CHEST.equals(key)) {
                context.addPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0.9f, 1.0f))
                        .add(LootItem.lootTableItem(AsgardShieldItems.WOODEN_GIANT_SWORD.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AsgardShieldItems.WOODEN_SHIELD.get()).setWeight(5)));
            }
            if (BuiltInLootTables.NETHER_BRIDGE.equals(key)) {
                context.addPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0.0f, 1.0f))
                        .add(LootItem.lootTableItem(AsgardShieldItems.SKULL_GIANT_SWORD.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AsgardShieldItems.GILDED_SKULL_SHIELD.get()).setWeight(2)));
            }
        });
    }
}
