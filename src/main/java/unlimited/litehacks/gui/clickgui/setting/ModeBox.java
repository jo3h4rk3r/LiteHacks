package unlimited.litehacks.gui.clickgui.setting;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import unlimited.litehacks.gui.clickgui.ModuleButton;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.Setting;

import java.awt.*;

public class ModeBox extends Component{

    private ModeSetting modeSet = (ModeSetting) setting;

    public ModeBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrix, parent.parent.x, parent.parent.y + parent.offset + offset,
                parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(0, 0, 0, 120).getRGB());
        int BToffset =  ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);

        mc.textRenderer.drawWithShadow(matrix, modeSet.getName() + ": " + modeSet.getMode(),
                parent.parent.x + BToffset, parent.parent.y + parent.offset + offset + BToffset, -1);

        super.render(matrix, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) modeSet.cycle();
        super.mouseClicked(mouseX, mouseY, button);
    }







}
