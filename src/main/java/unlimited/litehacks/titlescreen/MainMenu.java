package unlimited.litehacks.titlescreen;

import net.minecraft.GameVersion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unlimited.litehacks.gui.clickgui.ClickGui;

import java.awt.*;
import java.util.List;

import static unlimited.litehacks.Litehacks.ClientVersion;
import static unlimited.litehacks.Litehacks.VERSION;

public class MainMenu extends Screen {
    protected MinecraftClient mc = MinecraftClient.getInstance();


    public MainMenu() {
        super(Text.translatable("narrator.screen.title"));
    }


    public void init() {


        int l = this.height / 4 + 48;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.singleplayer"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new SelectWorldScreen(this));
        }).dimensions(this.width / 2 - 100, l, 200, 20).build());


        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.multiplayer"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new MultiplayerScreen(this));
        }).dimensions(this.width / 2 - 100, l + 25, 200, 20).build());


        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new OptionsScreen(this, this.client.options));
        }).dimensions(this.width / 2 - 100, l + 45 + 12, 98, 20).build());


        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quit"), (button) -> {
            assert this.client != null;
            this.client.scheduleStop();
        }).dimensions(this.width / 2 + 2, l + 45 + 12, 98, 20).build());


        this.addDrawableChild(ButtonWidget.builder(Text.translatable("Gui"), (button) -> {
            assert this.client != null;
            assert this.client.currentScreen != null;
            this.client.setScreen(new ClickGui());

        }).dimensions(this.width / 2 - 100, l + 75 + 12, 98, 20).build());



        /*
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new OptionsScreen(this, this.client.options));
        }).dimensions(this.width / 2 - 100, 1 + 72 + 12, 98, 20).build());

         */


        /*
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 2 * 1, 200, 20, Text.translatable("menu.multiplayer"), (button) -> {
            Screen screen = this.client.options.skipMultiplayerWarning ? new MultiplayerScreen(this) : new MultiplayerWarningScreen(this);
            this.client.setScreen((Screen)screen);
        }));

         */

    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        //drawTextWithShadow(matrices, mc.textRenderer, Text.literal("version: v1.0.1 fabric 1.19.3"), 10, 10, Color.WHITE.getRGB());
        drawTextWithShadow(matrices, mc.textRenderer, Text.literal("version: " + ClientVersion + " fabric " + VERSION), 10, 10, Color.WHITE.getRGB());
        renderTitle(matrices, mouseX, mouseY);
        int copyWidth = this.textRenderer.getWidth("Copyright Mojang AB. Do not distribute!") + 2;
        textRenderer.drawWithShadow(matrices, "Copyright Mojang AB. Do not distribute!", width - copyWidth, height - 13, -1);
        super.render(matrices, mouseX, mouseY, delta);
    }


    public void renderTitle(MatrixStack matrix, int mouseX, int mouseY) {
        int x1 = mc.getWindow().getScaledWidth() / 2 - 20;
        int y1 = mc.getWindow().getScaledHeight() / 4;
        int x = x1,
                y = y1 - 10,
                w = width - width,
                h = height - height;
        matrix.push();
        matrix.scale(3, 3, 0);
        drawStringWithShadow(matrix, this.textRenderer, "L I T E H A C K S", (x + w/2 - 106)/3, (y + h/4 - 1)/3, getRainbow(1, 2, 10, 0));
        matrix.pop();
    }

    private static int getRainbow ( float sat, float bri, double speed, int offset){
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }
}

