package me.mangregory.asr.gametest;

import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.init.AsgardShieldItems;
import me.mangregory.asr.util.handlers.items.AsgardShieldHandler;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.monster.Enderman;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.phys.AABB;
import dev.architectury.event.EventResult;
import java.util.List;
import me.mangregory.asr.config.ClientBlockCooldownCache;
import me.mangregory.asr.config.ModConfig;

public final class AsrGameTests {

    /** Block-time cooldown increments per use tick and resets on release. */
    public static void cooldownLifecycle(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        AsgardShieldItem item = (AsgardShieldItem) AsgardShieldItems.STONE_SHIELD.get();
        ItemStack stack = new ItemStack(item);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);

        for (int i = 0; i < 3; i++) {
            item.onUseTick(helper.getLevel(), player, stack, 72000);
        }
        int afterTicks = item.getCooldown(player, stack);
        if (afterTicks != 3)
            throw helper.assertionException("expected cooldown 3 after 3 use ticks, got " + afterTicks);

        item.releaseUsing(stack, helper.getLevel(), player, 0);
        int afterRelease = item.getCooldown(player, stack);
        if (afterRelease != 0)
            throw helper.assertionException("expected cooldown 0 after release, got " + afterRelease);

        helper.succeed();
    }

    /** Gilded stone shield extinguishes a burning player when blocking an attack. */
    public static void gildedStoneShieldExtinguishes(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        ItemStack stack = new ItemStack(AsgardShieldItems.GILDED_STONE_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        player.setRemainingFireTicks(200);

        DamageSource source = helper.getLevel().damageSources().generic();
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source);

        if (player.isOnFire())
            throw helper.assertionException("player still burning after gilded stone shield block");
        helper.succeed();
    }

    /** A stub random that always rolls 0 (or all-ones for "never") so chance-based perks are deterministic. */
    private static RandomSource fixedRoll(long bits) {
        return new RandomSource() {
            @Override public long nextLong() { return bits; }
            @Override public int nextInt() { return (int) bits; }
            @Override public int nextInt(int bound) { return 0; }
            @Override public float nextFloat() { return bits == 0 ? 0.0F : 1.0F; }
            @Override public double nextDouble() { return bits == 0 ? 0.0 : 1.0; }
            @Override public boolean nextBoolean() { return bits != 0; }
            @Override public double nextGaussian() { return bits == 0 ? 0.0 : 1.0; }
            @Override public void setSeed(long seed) {}
            @Override public RandomSource fork() { return this; }
            @Override public PositionalRandomFactory forkPositional() { return null; }
        };
    }

    private static final RandomSource ROLL_ALWAYS = fixedRoll(0L);

    /** The mod's loot pools actually generate mod items (deterministic: fixed seeds). */
    public static void lootTablesContainModItems(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();
        LootTable endCity = level.getServer().reloadableRegistries().getLootTable(BuiltInLootTables.END_CITY_TREASURE);
        LootParams params = new LootParams.Builder(level).withParameter(LootContextParams.ORIGIN, Vec3.ZERO).create(endCity.getParamSet());
        boolean found = false;
        for (long seed = 0; seed < 200 && !found; seed++) {
            for (ItemStack stack : endCity.getRandomItems(params, seed)) {
                if (stack.is(AsgardShieldItems.ENDER_SHIELD.get())) {
                    found = true;
                    break;
                }
            }
        }
        if (!found)
            throw helper.assertionException("ender shield never generated from end city treasure in 200 seeds");
        helper.succeed();
    }

    /** Stone shield explosion weakness: takes base + 5 durability damage from explosions. */
    public static void stoneShieldExplosionWeakness(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setGameMode(GameType.SURVIVAL); // mock players default to creative, which skips item damage
        ItemStack stack = new ItemStack(AsgardShieldItems.STONE_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        int before = stack.getDamageValue();

        DamageSource source = helper.getLevel().damageSources().explosion((Entity) null, (Entity) null);
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source);

        int expected = (int) ModConfig.ASGARD_SHIELD_BASE_ATTACKDMG + 5;
        int actual = stack.getDamageValue() - before;
        if (actual != expected)
            throw helper.assertionException("expected " + expected + " explosion damage, got " + actual);
        helper.succeed();
    }

    /** Wooden shield arrow-catch perk fires when the roll succeeds (forced via stub random). */
    public static void woodenShieldCollectsArrowOnForcedRoll(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        ItemStack stack = new ItemStack(AsgardShieldItems.WOODEN_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        @SuppressWarnings("unchecked")
        EntityType<Arrow> arrowType = (EntityType<Arrow>) BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.withDefaultNamespace("arrow"));
        Arrow arrow = helper.spawn(arrowType, new BlockPos(0, 0, 0));

        DamageSource source = helper.getLevel().damageSources().arrow(arrow, player);
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source, ROLL_ALWAYS);

        if (!arrow.isRemoved())
            throw helper.assertionException("arrow was not collected");
        if (player.getInventory().countItem(Items.ARROW) <= 0)
            throw helper.assertionException("player did not receive the arrow item");
        helper.succeed();
    }

    /** Diamond shield arrow-reflect perk fires when the roll succeeds (forced via stub random). */
    public static void diamondShieldReflectsArrowOnForcedRoll(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        ItemStack stack = new ItemStack(AsgardShieldItems.DIAMOND_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        @SuppressWarnings("unchecked")
        EntityType<Arrow> arrowType = (EntityType<Arrow>) BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.withDefaultNamespace("arrow"));
        Arrow arrow = helper.spawn(arrowType, new BlockPos(0, 0, 0));

        DamageSource source = helper.getLevel().damageSources().arrow(arrow, player);
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source, ROLL_ALWAYS);

        if (arrow.getOwner() != player)
            throw helper.assertionException("arrow was not reflected to the player");
        if (!arrow.syncVelocity)
            throw helper.assertionException("syncVelocity not set after reflect");
        helper.succeed();
    }

    /** The client-side cooldown cache read path used by the HUD. */
    public static void clientCooldownCacheReadPath(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        AsgardShieldItem item = (AsgardShieldItem) AsgardShieldItems.STONE_SHIELD.get();
        ItemStack stack = new ItemStack(item);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        String key = player.getUUID() + ":" + player.getInventory().findSlotMatchingItem(stack);

        ClientBlockCooldownCache.update(key, 5);
        if (item.getClientCooldown(player, stack) != 5)
            throw helper.assertionException("expected synced cooldown 5, got " + item.getClientCooldown(player, stack));
        ClientBlockCooldownCache.update(key, 0);
        if (item.getClientCooldown(player, stack) != 0)
            throw helper.assertionException("expected synced cooldown reset to 0");
        helper.succeed();
    }

    /** Iron shield in water/rain takes base + 5 durability damage (environment injected). */
    public static void ironShieldWaterBonus(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setGameMode(GameType.SURVIVAL);
        ItemStack stack = new ItemStack(AsgardShieldItems.IRON_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        int before = stack.getDamageValue();

        DamageSource source = helper.getLevel().damageSources().generic();
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source,
                ROLL_ALWAYS, true, false);

        int expected = (int) ModConfig.ASGARD_SHIELD_BASE_ATTACKDMG + 5;
        int actual = stack.getDamageValue() - before;
        if (actual != expected)
            throw helper.assertionException("expected " + expected + " water-bonus damage, got " + actual);
        helper.succeed();
    }

    /** Iron shield negates explosion damage entirely when the 50% roll succeeds (forced). */
    public static void ironShieldExplosionNegateOnForcedRoll(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setGameMode(GameType.SURVIVAL);
        ItemStack stack = new ItemStack(AsgardShieldItems.IRON_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        int before = stack.getDamageValue();

        DamageSource source = helper.getLevel().damageSources().explosion((Entity) null, (Entity) null);
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source, ROLL_ALWAYS);

        if (stack.getDamageValue() != before)
            throw helper.assertionException("explosion damage was not negated, damage=" + stack.getDamageValue());
        helper.succeed();
    }

    /** Copper shield summons a lightning bolt while thundering when the roll succeeds (forced). */
    public static void copperShieldLightningOnForcedRoll(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setGameMode(GameType.SURVIVAL);
        // mock players spawn at absolute (0,0,0), outside the loaded test area -- move into the structure
        BlockPos origin = helper.absolutePos(new BlockPos(0, 0, 0));
        player.setPos(origin.getX() + 0.5, origin.getY(), origin.getZ() + 0.5);
        ItemStack stack = new ItemStack(AsgardShieldItems.COPPER_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);

        DamageSource source = level.damageSources().generic();
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source,
                ROLL_ALWAYS, false, true);

        // newly added entities become visible to queries after a tick
        helper.runAfterDelay(5, () -> {
            List<LightningBolt> bolts = level.getEntitiesOfClass(LightningBolt.class, player.getBoundingBox().inflate(64));
            if (bolts.isEmpty())
                bolts = level.getEntitiesOfClass(LightningBolt.class, new AABB(player.blockPosition()).inflate(128));
            if (bolts.isEmpty())
                throw helper.assertionException("no lightning bolt spawned by copper shield; playerPos=" + player.blockPosition()
                        + " entities=" + level.getEntitiesOfClass(Entity.class, new AABB(player.blockPosition()).inflate(128)).size());
            helper.succeed();
        });
    }

    /** Ender shield stops the block and interrupts (interruptTrue) when the attacker is an Enderman. */
    public static void enderShieldStopsAgainstEnderman(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setGameMode(GameType.SURVIVAL);
        ItemStack stack = new ItemStack(AsgardShieldItems.ENDER_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        @SuppressWarnings("unchecked")
        EntityType<Enderman> endermanType = (EntityType<Enderman>) BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.withDefaultNamespace("enderman"));
        Enderman enderman = helper.spawn(endermanType, new BlockPos(0, 0, 0));

        DamageSource source = helper.getLevel().damageSources().mobAttack(enderman);
        EventResult result = AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source, ROLL_ALWAYS);

        if (!result.interruptsFurtherEvaluation() || !Boolean.TRUE.equals(result.value()))
            throw helper.assertionException("expected interruptTrue against enderman, got " + result);
        helper.succeed();
    }

    /** Ender shield teleports the attacker away when the 20% roll succeeds (forced, fixed offset). */
    public static void enderShieldTeleportsEnemyOnForcedRoll(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();
                ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setGameMode(GameType.SURVIVAL);
        BlockPos origin = helper.absolutePos(new BlockPos(0, 0, 0));
        player.setPos(origin.getX() + 0.5, origin.getY(), origin.getZ() + 0.5);
        ItemStack stack = new ItemStack(AsgardShieldItems.ENDER_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);

        // solid platform so findSafePosition can land the teleport (test structure itself is air)
        for (int dx = -12; dx <= 12; dx++)
            for (int dz = -12; dz <= 12; dz++)
                level.setBlock(origin.offset(dx, -1, dz), Blocks.STONE.defaultBlockState(), 3);

        @SuppressWarnings("unchecked")
        EntityType<Pig> pigType = (EntityType<Pig>) BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.withDefaultNamespace("pig"));
        Pig pig = helper.spawn(pigType, new BlockPos(0, 0, 0));
        pig.setNoAi(true);
        BlockPos before = pig.blockPosition();

        DamageSource source = level.damageSources().mobAttack(pig);
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source, ROLL_ALWAYS);

        if (pig.blockPosition().equals(before))
            throw helper.assertionException("enemy was not teleported, still at " + before);
        helper.succeed();
    }

    /** Skull shield mind control: on a successful roll the enemy is sent to attack a nearby ally. */
    public static void skullShieldMindControl(GameTestHelper helper) {
        ServerLevel level = helper.getLevel();
        ServerPlayer player = helper.makeMockServerPlayerInLevel();
        player.setGameMode(GameType.SURVIVAL);
        BlockPos origin = helper.absolutePos(new BlockPos(0, 0, 0));
        player.setPos(origin.getX() + 0.5, origin.getY(), origin.getZ() + 0.5);
        ItemStack stack = new ItemStack(AsgardShieldItems.SKULL_SHIELD.get());
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);

        @SuppressWarnings("unchecked")
        EntityType<Enderman> endermanType2 = (EntityType<Enderman>) BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.withDefaultNamespace("enderman"));
        Enderman enemy = helper.spawn(endermanType2, new BlockPos(0, 0, 0));
        Enderman ally = helper.spawn(endermanType2, new BlockPos(2, 0, 0));
        enemy.setNoAi(true);
        ally.setNoAi(true);
        float allyHealthBefore = ally.getHealth();

        DamageSource source = level.damageSources().mobAttack(enemy);
        AsgardShieldHandler.handleShieldFunctionality(player, stack.getItem(), InteractionHand.MAIN_HAND, source, ROLL_ALWAYS);

        if (ally.getHealth() >= allyHealthBefore)
            throw helper.assertionException("nearby ally was not hurt by the mind-controlled enemy, health=" + ally.getHealth());
        helper.succeed();
    }
}