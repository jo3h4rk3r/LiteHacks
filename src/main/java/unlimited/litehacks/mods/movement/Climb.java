package unlimited.litehacks.mods.movement;

import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class Climb extends Module {
    public Climb() {
        super("Climb", "Craw up walls", Category.MOVEMENT);
    }



    @Override
    public void onTick() {
        if (mc.player != null) {
            if (mc.player.horizontalCollision) {
                Vec3d velocity = mc.player.getVelocity();
                //if (velocity.y >= 0.2) {
                mc.player.setVelocity(velocity.x, 0.2, velocity.z);
            }

        }
        super.onTick();
    }
}
