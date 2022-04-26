package fr.obelouix.obecraft.client.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.obelouix.obecraft.energy.EnergySync;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class EnergyWidget extends AbstractWidget {
    private static final ResourceLocation WIDGETS = new ResourceLocation("obecraft", "textures/gui/widgets.png");
    private final Supplier<EnergySync> getEnergy;
    private final Screen screen;

    public EnergyWidget(Screen screen, Supplier<EnergySync> getEnergy, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.getEnergy = getEnergy;
        this.screen = screen;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        EnergySync energySync = getEnergy.get();
        if (energySync.energy() <= 0) return;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderTexture(0, WIDGETS);

        float filledVolume = energySync.energy() / (float) energySync.capacity();
        int renderableHeight = (int) (filledVolume * height);

        pPoseStack.pushPose();
        pPoseStack.translate(0, height - 16, 0);
        for (int i = 0; i < Math.ceil(renderableHeight / 16f); i++) {
            int drawingHeight = Math.min(16, renderableHeight - 16 * i);
            int notDrawingHeight = 16 - drawingHeight;
            blit(pPoseStack, x, y + notDrawingHeight, screen.getBlitOffset(), 0, 128 + notDrawingHeight, width, drawingHeight, 256, 256);
            pPoseStack.translate(0, -16, 0);
        }

        RenderSystem.setShaderColor(1, 1, 1, 1);
        pPoseStack.popPose();

        renderToolTip(pPoseStack, pMouseX, pMouseY);
    }

    public void renderToolTip(PoseStack poseStack, int mouseX, int mouseY) {
        if (isHovered(mouseX, mouseY)) {
            EnergySync energy = getEnergy.get();
            screen.renderTooltip(poseStack, new TranslatableComponent(""), mouseX, mouseY);
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

    protected boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

}
