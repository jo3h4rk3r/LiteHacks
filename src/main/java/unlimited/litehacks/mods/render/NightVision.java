package unlimited.litehacks.mods.render;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class NightVision extends Module {


    public NightVision() {
        super("NightVison", "Gives you night vision", Category.RENDER);
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


        super.onTick();
    }



}
