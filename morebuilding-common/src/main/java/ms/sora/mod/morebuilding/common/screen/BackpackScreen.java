package ms.sora.mod.morebuilding.common.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import ms.sora.mod.morebuilding.common.MBInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * BackpackScreen is a screen class of the Backpack.
 * <p>
 * This class contains properties and behaviors of the Backpack screen.
 *
 * @since 0.1.0
 */
public class BackpackScreen extends HandledScreen<BackpackScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(MBInfo.MOD_ID, "textures/gui/container/backpack.png");

    /**
     * Constructs a screen.
     *
     * @param handler   the screen handler
     * @param inventory the player inventory
     * @param title     the title of this
     */
    public BackpackScreen(BackpackScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    /**
     * Draws background.
     *
     * @param graphics the graphic context
     * @param delta    the delta since the last tick
     * @param mouseX   the mouse X-coordinate on the screen
     * @param mouseY   the mouse Y-coordinate on the screen
     */
    @Override
    protected void drawBackground(GuiGraphics graphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    /**
     * Renders this screen.
     *
     * @param graphics the graphic context
     * @param mouseX   the mouse X-coordinate on the screen
     * @param mouseY   the mouse Y-coordinate on the screen
     * @param delta    the delta since the last tick
     */
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        drawMouseoverTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
