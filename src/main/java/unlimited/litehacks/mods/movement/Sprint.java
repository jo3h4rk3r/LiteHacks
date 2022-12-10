package unlimited.litehacks.mods.movement;

import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Keeps your sprint on", Category.MOVEMENT);
    }







    @Override
    public void onTick() {
        if (mc.player != null) {
            mc.player.setSprinting(true);
        }
        super.onTick();
    }
}
