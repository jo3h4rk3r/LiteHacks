package unlimited.litehacks.mods.movement;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class SkyColor extends Module {

    public static NumberSetting SkyColorValue = new NumberSetting("Color", 0, 255, 100, 0.1);
    public static boolean skyColorEnabled = false;

    public SkyColor() {
        super("SkyColor", "Changes the color of the sky", Category.RENDER);
        addSettings(SkyColorValue);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;

        skyColorEnabled = true;
        //mc.player.sendMessage(Text.literal("Flight enabled"));
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        skyColorEnabled = false;
        //mc.player.sendMessage(Text.literal("Flight disabled"));
        super.onDisable();
    }

    @Override
    public void onTick() {
        assert mc.player != null;

        super.onTick();
    }



}
