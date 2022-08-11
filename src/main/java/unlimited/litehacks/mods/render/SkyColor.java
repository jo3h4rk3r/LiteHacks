package unlimited.litehacks.mods.render;

import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class SkyColor extends Module {
    public static NumberSetting SkyColorValue = new NumberSetting("Color", 0, 10, 0, 0.1);
    /*
    public static NumberSetting SkyColorRedValue = new NumberSetting("Red", 0, 1, 0, 0.1);
    public static NumberSetting SkyColorGreenValue = new NumberSetting("Green", 0, 1, 0, 0.1);
    public static NumberSetting SkyColorBlueValue = new NumberSetting("Blue", 0, 1, 0, 0.1);

     */
    public static boolean skyColorEnabled = false;
    public static float red = 0;
    public static float green = 0;
    public static float blue = 0;


    public SkyColor() {
        super("SkyColor", "Changes the color of the sky", Category.RENDER);
        addSettings(SkyColorValue);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;

        skyColorEnabled = true;

    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        skyColorEnabled = false;

        super.onDisable();
    }

    @Override
    public void onTick() {
        assert mc.player != null;

        super.onTick();
    }

    public static void onSkyColor(Vec3d cameraPos, float tickDelta, CallbackInfoReturnable<Vec3d> ci) {
        if (SkyColor.skyColorEnabled) {

            float g = SkyColorValue.getValueFloat();

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




            ci.setReturnValue(new Vec3d(red, green, blue));

        }


    }

}
