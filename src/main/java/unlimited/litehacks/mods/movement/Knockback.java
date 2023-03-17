package unlimited.litehacks.mods.movement;

import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class Knockback extends Module {
    private boolean jumping;
    public NumberSetting velocity = new NumberSetting("velocity", 0, 10, 0, 0.1);


    public Knockback() {
        super("Knockback", "Change player knockback to entities", Category.MOVEMENT);
        addSettings(velocity);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;


        super.onEnable();
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        mc.player.takeKnockback(0.5, 0.0, 0.0);

        super.onDisable();
    }

    @Override
    public void onTick() {
        if (mc.player != null) {

            mc.player.takeKnockback(velocity.getValueFloat(), 0, 0);
            mc.player.setVelocity(velocity.getValueFloat(), velocity.getValueFloat(), velocity.getValueFloat());
            //mc.player.takeKnockback(velocity.getValue(), 0, 0);

        }
        super.onTick();

    }
}






