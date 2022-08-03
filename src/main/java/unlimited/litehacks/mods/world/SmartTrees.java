package unlimited.litehacks.mods.movement;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class SmartTrees extends Module {



    public SmartTrees() {
        super("SmartTrees", "Makes trees render faster", Category.WORLD);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;

    }

    @Override
    public void onDisable() {
        assert mc.player != null;

        super.onDisable();
    }

    @Override
    public void onTick() {
        assert mc.player != null;

        super.onTick();
    }



}
