package fr.obelouix.obecraft.client.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.function.Supplier;

public class ProgressWidget extends AbstractWidget {

    private final Screen screen;
    private final int u;
    private final int v;
    private Supplier<Float> getter;

    public ProgressWidget(Screen screen, Supplier<Float> getter, int x, int y, int width, int height, int u, int v) {
        super(x, y, width, height, TextComponent.EMPTY);
        this.screen = screen;
        this.getter = getter;
        this.u = u;
        this.v = v;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        pPoseStack.pushPose();

        float progress = getter.get();
        int yOffset = (int) (14 * (1.0f - progress));
        int height = (int) (14 * progress);

        blit(pPoseStack, x, y + yOffset, u, v + yOffset, width, height);
        pPoseStack.popPose();

        if (this.isHoveredOrFocused()) {
            this.renderToolTip(pPoseStack, pMouseX, pMouseY);
        }

    }

    @Override
    public void renderToolTip(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        if (isHovered && isActive()) {
            screen.renderTooltip(pPoseStack, new TranslatableComponent(""), pMouseX, pMouseY);
        }
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }
}
