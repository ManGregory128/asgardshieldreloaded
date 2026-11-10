package me.mangregory.asr.forge.client;

import com.teamresourceful.resourcefulconfig.client.CategoriesWidget;
import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.client.ConfigValuesWidget;
import com.teamresourceful.resourcefulconfig.client.TooltipAccessor;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * RC 2.x always renders the left "Categories" panel (via a private field in ConfigScreen.render),
 * even when the config has no sub-configs, leaving it empty. Hide it in that case and let the
 * values list take the center of the screen.
 */
public class AsrConfigScreen extends ConfigScreen {

    private static final String FILE_EXT = ".toml";

    private boolean hideCategories;

    public AsrConfigScreen(Screen lastScreen, ResourcefulConfig config) {
        super(lastScreen, null, config);
    }

    @Override
    protected void init() {
        super.init();
        hideCategories = this.config.getSubConfigs().isEmpty();
        if (hideCategories) {
            CategoriesWidget categories = null;
            ConfigValuesWidget list = null;
            for (var child : this.children()) {
                if (child instanceof CategoriesWidget c) categories = c;
                if (child instanceof ConfigValuesWidget l) list = l;
            }
            if (categories != null) this.removeWidget(categories);
            if (list != null) {
                // RC sizes the values list at 0.64x screen width beside the categories panel;
                // stretch it across the full width now that the panel is gone (row width tracks list width)
                var rect = list.getRectangle();
                int margin = Math.max(10, this.width / 20);
                list.updateSize(this.width - margin * 2, rect.height(), rect.top(), rect.top() + rect.height());
                list.setLeftPos(margin);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!hideCategories) {
            super.render(guiGraphics, mouseX, mouseY, partialTick);
            return;
        }
        // Replicates ConfigScreen.render minus the categories panel (which it draws via a private field)
        this.renderBackground(guiGraphics);
        Set<Renderable> rendered = new HashSet<>();
        ConfigValuesWidget list = null;
        for (var child : this.children()) {
            if (child instanceof CategoriesWidget) continue;
            if (child instanceof ConfigValuesWidget l) list = l;
            if (child instanceof Renderable r && rendered.add(r)) r.render(guiGraphics, mouseX, mouseY, partialTick);
        }
        Component options = Component.literal("Options").withStyle(ChatFormatting.BOLD);
        guiGraphics.drawString(this.font, options, this.width / 2 - this.font.width(options) / 2, 11, 0xFFFFFF);
        Component fileName = Component.literal(getFileName() + FILE_EXT);
        guiGraphics.drawString(this.font, fileName, this.width - 10 - this.font.width(fileName), this.height - 20, 5592405);
        if (list != null) {
            list.getMouseOver(mouseX, mouseY).map(TooltipAccessor::getTooltip)
                    .ifPresent(tooltip -> guiGraphics.renderTooltip(this.font, tooltip, mouseX, mouseY));
        }
    }
}
