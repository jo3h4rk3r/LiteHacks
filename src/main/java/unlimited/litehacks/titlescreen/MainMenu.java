package unlimited.litehacks.titlescreen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

public class MainMenu extends Screen {
    protected MinecraftClient mc = MinecraftClient.getInstance();


    public MainMenu() {
        super(Text.translatable("narrator.screen.title"));
    }


    public void init(){



        int l = this.height / 4 + 48;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l, 200, 20, Text.translatable("menu.singleplayer"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new SelectWorldScreen(this));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 25, 200, 20, Text.translatable("menu.multiplayer"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new MultiplayerScreen(this));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 72 + 12, 98, 20, Text.translatable("menu.options"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new OptionsScreen(this, this.client.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, l + 72 + 12, 98, 20, Text.translatable("menu.quit"), (button) -> {
            assert this.client != null;
            this.client.scheduleStop();
        }));
        /*
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 2 * 1, 200, 20, Text.translatable("menu.multiplayer"), (button) -> {
            Screen screen = this.client.options.skipMultiplayerWarning ? new MultiplayerScreen(this) : new MultiplayerWarningScreen(this);
            this.client.setScreen((Screen)screen);
        }));

         */

    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
       // int x = 3,
      //          y = 3 - 10,
      //          w = width - width / 4,
     //           h = height - height / 4;
     //   matrices.push();
     //   matrices.scale(3, 3, 0);
       // drawStringWithShadow(matrices, this.textRenderer, "L I T E H A C K S", (x + w/2 - 106)/3, (y + h/4 - 1)/3, getRainbow(1,2,10,0));

        this.renderBackground(matrices);
        fill(matrices, 0,0,0,0,0);

        drawTextWithShadow(matrices, mc.textRenderer, Text.literal("LiteHacks v1.0 fabric 1.19"), 10, 10, Color.WHITE.getRGB());

/*
        int copyWidth = this.textRenderer.getWidth("Copyright Mojang AB. Do not distribute!") + 2;
        textRenderer.drawWithShadow(matrices, "Copyright Mojang AB. Do not distribute!", width - copyWidth, height - 13, -1);

 */
        super.render(matrices, mouseX, mouseY, delta);

    }

    private static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }

}
