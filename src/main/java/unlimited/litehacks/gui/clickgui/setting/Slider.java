package unlimited.litehacks.gui.clickgui.setting;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import unlimited.litehacks.gui.clickgui.ModuleButton;
import unlimited.litehacks.mods.settings.NumberSetting;
import unlimited.litehacks.mods.settings.Setting;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Component {

    public NumberSetting numSet = (NumberSetting)setting;

    private boolean sliding = false;

    public Slider(Setting setting, ModuleButton parent, int offset) {

        super(setting, parent, offset);
        this.numSet = (NumberSetting)setting;
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrix, parent.parent.x, parent.parent.y + parent.offset + offset,
                parent.parent.x + parent.parent.width,
                parent.parent.y + parent.offset + offset + parent.parent.height, new Color(0, 0, 0, 120).getRGB());

        double diff = Math.min(parent.parent.width, Math.max(0, mouseX - parent.parent.x));
        int renderWidth = (int) (parent.parent.width * (numSet.getValue() - numSet.getMin()) / (numSet.getMax() - numSet.getMin()));

        DrawableHelper.fill(matrix, parent.parent.x, parent.parent.y + parent.offset + offset,
                parent.parent.x + renderWidth,
                parent.parent.y + parent.offset + offset + parent.parent.height, Color.RED.getRGB());


        if (sliding) {

            if (diff == 0) {
                numSet.setValue(numSet.getMin());
                //numSet.increment(true);
            } else {
                numSet.setValue(roundToPlace(((diff / parent.parent.width) * (numSet.getMax() - numSet.getMin() + numSet.getMin())), 2));
            }
        }

        int BToffset =  ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);

        mc.textRenderer.drawWithShadow(matrix, numSet.getName() + ": " + roundToPlace(numSet.getValue(), 2),
                parent.parent.x + BToffset, parent.parent.y + parent.offset + offset + BToffset, -1);


        super.render(matrix, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) sliding = true;
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        sliding = false;
        super.mouseReleased(mouseX, mouseY, button);
    }



    private double roundToPlace(double value, int place) {
        if (place < 0) {
            return value;
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
