package unlimited.litehacks.gui.clickgui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import unlimited.litehacks.gui.clickgui.setting.Component;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.Module.Category;
import unlimited.litehacks.mods.ModuleManager;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame {

    public int x, y, width, height, dragX, dragY;
    public Category category;
    public boolean dragging, extended;

    private List<ModuleButton> buttons;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Frame (Category category, int x, int y, int width, int height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dragging = false;
        this.extended = false;

        buttons = new ArrayList<>();

        int offset = height;
        for (Module mod : ModuleManager.INSTANCE.getModulesInCategory(category)) {
           buttons.add(new ModuleButton(mod, this, offset));
            offset += height;
        }
    }


    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrix, x, y, x + width, y + height, Color.BLACK.getRGB());
        int offset =  ((height / 2) - mc.textRenderer.fontHeight / 2);


        mc.textRenderer.drawWithShadow(matrix, category.name, x + (offset), y + (offset), -1);
        mc.textRenderer.drawWithShadow(matrix, extended ? "-" : "+", x + width - ((height / 2) - mc.textRenderer.fontHeight / 2) - 2 - mc.textRenderer.getWidth("+"), y + (offset), -1);

        if (extended) {
            for (ModuleButton button : buttons) {
                button.render(matrix, mouseX, mouseY, delta);
            }
        }


    }


    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) (mouseX - x);
                dragY = (int) (mouseY - y);
            } else if (button == 1) {
                extended = !extended;
            }
        }


        if (extended) {
            for (ModuleButton bruh : buttons) {
                bruh.mouseClicked(mouseX, mouseY, button);
            }
        }


    }



    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && dragging == true) dragging = false;

        for (ModuleButton mb : buttons) {
            mb.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public void updatePosition(double mouseX, double mouseY) {
        if (dragging) {
            x = (int) (mouseX - dragX);
            y = (int) (mouseY - dragY);
        }
    }

    public void updateButtons() {
        int offset = height;

        for (ModuleButton button : buttons) {
            button.offset = offset;
            offset += height;

            if (button.extended) {
                for (Component component : button.components) {
                    if (component.setting.isVisible()) offset += height;
                }
            }
        }
    }
}
