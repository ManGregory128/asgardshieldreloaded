package me.mangregory.asr.gametest;

import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.BlockBasedTestInstance;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.Identifier;

import java.util.function.Consumer;

/**
 * A code-registered game test: wraps a {@code Consumer<GameTestHelper>} body, running in the
 * built-in empty structure with a no-op environment. Extends {@link BlockBasedTestInstance} so the
 * registry codec (used when syncing test instances to clients at login) handles it natively.
 */
public class AsrGameTestInstance extends BlockBasedTestInstance {
    private final Consumer<GameTestHelper> body;

    public AsrGameTestInstance(Holder<TestEnvironmentDefinition<?>> environment, Consumer<GameTestHelper> body, int maxTicks) {
        super(new TestData<>(environment, Identifier.withDefaultNamespace("empty"), maxTicks, 0, true));
        this.body = body;
    }

    @Override
    public void run(GameTestHelper helper) {
        body.accept(helper);
    }
}
