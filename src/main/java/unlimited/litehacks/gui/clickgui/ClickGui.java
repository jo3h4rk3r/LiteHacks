package unlimited.litehacks.gui.clickgui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import unlimited.litehacks.mods.Module.Category;
import unlimited.litehacks.mods.render.Tracers;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {

    protected MinecraftClient mc = MinecraftClient.getInstance();
    public static final ClickGui INSTANCE = new ClickGui();

    private List<Frame> frames;

    public ClickGui() {
        super(Text.literal("Click Gui"));

        frames = new ArrayList<>();

        int offset = 20;
        for (Category category : Category.values()) {
            frames.add(new Frame(category, offset, 30, 100, 20));
            offset += 105;
        }

    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        if (mc.player == null) {
            this.renderBackground(matrix);
        }

        for (Frame frame : frames) {
            frame.render(matrix, mouseX, mouseY, delta);
            frame.updatePosition(mouseX, mouseY);
        }
        super.render(matrix, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
}
