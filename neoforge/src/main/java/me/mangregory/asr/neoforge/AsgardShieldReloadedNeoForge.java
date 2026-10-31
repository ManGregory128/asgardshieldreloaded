package me.mangregory.asr.neoforge;

import net.minecraft.core.Holder;
import me.mangregory.asr.gametest.AsrGameTestInstance;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.gametest.AsrGameTests;

import java.util.List;
import java.util.function.Consumer;

@Mod(AsgardShieldReloaded.MOD_ID)
public final class AsgardShieldReloadedNeoForge {
    public AsgardShieldReloadedNeoForge(IEventBus modEventBus) {
        // Run our common setup.
        AsgardShieldReloaded.init();

        modEventBus.addListener(RegisterGameTestsEvent.class, event -> {
            Holder<TestEnvironmentDefinition<?>> environment = event.registerEnvironment(
                    Identifier.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, "default"),
                    new TestEnvironmentDefinition.AllOf(List.of()));
            register(event, environment, "cooldown_lifecycle", AsrGameTests::cooldownLifecycle);
            register(event, environment, "gilded_stone_extinguishes", AsrGameTests::gildedStoneShieldExtinguishes);

            register(event, environment, "loot_tables_contain_mod_items", AsrGameTests::lootTablesContainModItems);
            register(event, environment, "stone_shield_explosion_weakness", AsrGameTests::stoneShieldExplosionWeakness);
            register(event, environment, "wooden_shield_collects_arrow", AsrGameTests::woodenShieldCollectsArrowOnForcedRoll);
            register(event, environment, "diamond_shield_reflects_arrow", AsrGameTests::diamondShieldReflectsArrowOnForcedRoll);
            register(event, environment, "client_cooldown_cache", AsrGameTests::clientCooldownCacheReadPath);
            register(event, environment, "iron_shield_water_bonus", AsrGameTests::ironShieldWaterBonus);
            register(event, environment, "iron_shield_explosion_negate", AsrGameTests::ironShieldExplosionNegateOnForcedRoll);
            register(event, environment, "copper_shield_lightning", AsrGameTests::copperShieldLightningOnForcedRoll);
            register(event, environment, "ender_shield_vs_enderman", AsrGameTests::enderShieldStopsAgainstEnderman);
            register(event, environment, "ender_shield_teleports_enemy", AsrGameTests::enderShieldTeleportsEnemyOnForcedRoll);
            register(event, environment, "skull_shield_mind_control", AsrGameTests::skullShieldMindControl);
        });
    }

    private static void register(RegisterGameTestsEvent event, Holder<TestEnvironmentDefinition<?>> environment,
                                 String name, Consumer<GameTestHelper> body) {
        register(event, environment, name, body, 100);
    }

    private static void register(RegisterGameTestsEvent event, Holder<TestEnvironmentDefinition<?>> environment,
                                 String name, Consumer<GameTestHelper> body, int maxTicks) {
        event.registerTest(Identifier.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, name),
                new AsrGameTestInstance(environment, body, maxTicks));
    }
}
