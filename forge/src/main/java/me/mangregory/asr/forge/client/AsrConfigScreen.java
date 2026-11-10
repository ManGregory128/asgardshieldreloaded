package me.mangregory.asr.forge.client;

import com.teamresourceful.resourcefulconfig.client.CategoriesWidget;
import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.client.ConfigValuesWidget;
import com.teamresourceful.resourcefulconfig.client.TooltipAccessor;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * RC 2.x always renders the left "Categories" panel (populated from sub-configs only) and sizes
 * the values list at 0.64x screen width beside it. We have no sub-configs, so use a full-width
 * layout without the panel instead: our own values list whose rows span the full width.
 */
public class AsrConfigScreen extends ConfigScreen {

    private static final String FILE_EXT = ".toml";
    private static final int SIDE_MARGIN_MIN = 10;
    private static final int TOP = 32;
    private static final int BOTTOM_PAD = 32;
    private static final int ITEM_HEIGHT = 25;
    private static final int ROW_PAD = 16;

    private boolean fullWidth;
    private FullWidthValuesList valuesList;

    public AsrConfigScreen(Screen lastScreen, ResourcefulConfig config) {
        super(lastScreen, null, config);
    }

    @Override
    protected void init() {
        fullWidth = this.config.getSubConfigs().isEmpty();
        if (!fullWidth) {
            super.init();
            return;
        }
        int margin = Math.max(SIDE_MARGIN_MIN, this.width / 20);
        valuesList = new FullWidthValuesList(this.minecraft, this.width - margin * 2,
                this.height, TOP, this.height - BOTTOM_PAD, ITEM_HEIGHT);
        valuesList.setLeftPos(margin);
        valuesList.addSmall(this.config.getEntries(), this.config.getButtons());
        this.addWidget(valuesList);
        this.createFooter();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!fullWidth) {
            super.render(guiGraphics, mouseX, mouseY, partialTick);
            return;
        }
        this.renderBackground(guiGraphics);
        if (valuesList != null) valuesList.render(guiGraphics, mouseX, mouseY, partialTick);
        // footer buttons and any other renderable children (but not RC's private list/categories widgets)
        Set<Renderable> rendered = new HashSet<>();
        for (var child : this.children()) {
            if (child instanceof ConfigValuesWidget || child instanceof CategoriesWidget) continue;
            if (child instanceof Renderable r && rendered.add(r)) r.render(guiGraphics, mouseX, mouseY, partialTick);
        }
        Component options = Component.literal("Options").withStyle(ChatFormatting.BOLD);
        guiGraphics.drawString(this.font, options, this.width / 2 - this.font.width(options) / 2, 11, 0xFFFFFF);
        Component fileName = Component.literal(getFileName() + FILE_EXT);
        guiGraphics.drawString(this.font, fileName, this.width - 10 - this.font.width(fileName), this.height - 20, 5592405);
        if (valuesList != null) {
            valuesList.getMouseOver(mouseX, mouseY).map(TooltipAccessor::getTooltip)
                    .ifPresent(tooltip -> guiGraphics.renderTooltip(this.font, tooltip, mouseX, mouseY));
        }
    }

    /**
     * Values list whose rows span the full list width (left-aligned labels, widgets on the right),
     * instead of RC's fixed row width which leaves the rows centered at the old 0.64x size.
     */
    private static class FullWidthValuesList extends ConfigValuesWidget {

        FullWidthValuesList(Minecraft minecraft, int width, int height, int top, int bottom, int itemHeight) {
            super(minecraft, width, height, top, bottom, itemHeight);
        }

        @Override
        public int getRowWidth() {
            return this.width - ROW_PAD;
        }
    }
}
