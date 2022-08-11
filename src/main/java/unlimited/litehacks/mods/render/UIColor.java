package unlimited.litehacks.mods.render;

import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.NumberSetting;

import java.awt.*;

public class UIColor extends Module {

    public static NumberSetting tabColorValue = new NumberSetting("TAB", 0, 5, 0, 0.1);
    public static NumberSetting extendedColorValue = new NumberSetting("Extended", 0, 255, 5, 0.1);
    public static float tabred = 0;
    public static float tabgreen = 0;
    public static float tabblue = 0;
    public static float extendedred = 0;
    public static float extendedgreen = 0;
    public static float extendedblue = 0;

    public UIColor() {
        super("UI Color", "Change UI color", Category.RENDER);
        addSettings(tabColorValue, extendedColorValue);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;

        super.onEnable();
    }

    @Override
    public void onDisable() {
        assert mc.player != null;

        super.onDisable();
    }

    @Override
    public void onTick() {
        assert mc.player != null;

        float g = tabColorValue.getValueFloat();
        float b = extendedColorValue.getValueFloat();



            tabred = g;
            tabgreen = g;
            tabblue = g;

            extendedgreen = b;
            extendedred = b;
            extendedblue = b;


        /*
        if (g < 4) {
            red = g;
            green = g - 3;
        } else if (g > 4) {
            green = g - 2;
            if (green > 6) {
                green = 0;
            }
            red = g - 4;
        } else {
            red = 0;

            green = (float) (g - 2.9);
        }

        if (green > 3.8) {
            blue = (float) (g - 6.2);
        }

        if (g > 5.5) {
            if (g > 7) {
                blue = g - 5;
            }
            if (g < 7) {
                blue = g;
                green = g - 3;
                red = g - 6;
            }
        } else {
            blue = 0;
        }

         */








       // System.out.println("RED: " + red + " | GREEN: " + green + " | BLUE: " + blue + " TESTG " + g);


        super.onTick();
    }
}
