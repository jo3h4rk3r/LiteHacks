package unlimited.litehacks.gui.clickgui;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import unlimited.litehacks.gui.clickgui.setting.CheckBox;
import unlimited.litehacks.gui.clickgui.setting.Component;
import unlimited.litehacks.gui.clickgui.setting.ModeBox;
import unlimited.litehacks.gui.clickgui.setting.Slider;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;
import unlimited.litehacks.mods.settings.Setting;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static unlimited.litehacks.mods.render.UIColor.*;

public class ModuleButton {

    public Module module;
    public Frame parent;
    public int offset;
    public List<Component> components;
    public boolean extended;



    public ModuleButton(Module module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.extended = false;

        int setOffset =  parent.height;

        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                components.add(new CheckBox(setting, this, setOffset));
            } else if (setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, setOffset));
            } else if (setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, setOffset));
            }

            setOffset += parent.height;
        }
    }



    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrix, parent.x, parent.y + offset,
                parent.x + parent.width, parent.y + offset + parent.height, new Color((int) extendedred, (int) extendedgreen, (int) extendedblue, 160).getRGB());
        int BToffset =  ((parent.height / 2) - parent.mc.textRenderer.fontHeight / 2);


        if (isHovered(mouseX, mouseY)) DrawableHelper.fill(matrix, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 160).getRGB());
        parent.mc.textRenderer.drawWithShadow(matrix, module.getName(), parent.x + BToffset, parent.y + offset + BToffset, module.isEnabled() ? Color.GREEN.getRGB() : -1);


        if (extended) {
            for (Component component : components) {
                component.render(matrix, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {

        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                module.toggle();
            } else if (button == 1) {
                extended = !extended;
                parent.updateButtons();
            }
        }

        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }
    }



    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
        }
    }



    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }

}
