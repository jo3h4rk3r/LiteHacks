package unlimited.litehacks.mods.movement;

import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Keeps your sprint on", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        mc.player.setSprinting(true);
        super.onTick();
    }
}
