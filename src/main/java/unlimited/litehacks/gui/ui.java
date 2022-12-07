package unlimited.litehacks.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameModeSelectionScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import unlimited.litehacks.gui.clickgui.ClickGui;

import java.awt.*;

public class ui extends Screen {
    protected MinecraftClient mc = MinecraftClient.getInstance();

    public ui() {
        super(Text.translatable("user interface screen"));
    }




    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawCenteredText(matrices, mc.textRenderer, Text.literal("Test"), mouseX,mouseY, Color.WHITE.getRGB());



        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new OptionsScreen(this, this.client.options));
        }).dimensions(this.width / 2 - 100, 1 + 72 + 12, 98, 20).build());


    }







}
