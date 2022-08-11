package unlimited.litehacks.mods.movement;

import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", "sticky walk key", Category.MOVEMENT);
    }


    @Override
    public void onEnable() {

        mc.options.forwardKey.setPressed(true);

        super.onEnable();
    }

    @Override
    public void onDisable() {

        mc.options.forwardKey.setPressed(false);

        super.onDisable();
    }

    @Override
    public void onTick() {

        super.onTick();
    }
}
