package unlimited.litehacks.mods.movement;

import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class BoatFly extends Module {

    public NumberSetting boatSpeed = new NumberSetting("Speed", 0, 1, 0.3, 0.1);
    public BooleanSetting boatAntiKick = new BooleanSetting("Anti-Kick", true);

    public BoatFly() {
        super("BoatFly", "boat flight", Category.MOVEMENT);
        addSettings(boatSpeed, boatAntiKick);
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
        if (mc.player != null) {
            if (mc.player.hasVehicle()) {
                Entity vehicle = mc.player.getVehicle();
                assert vehicle != null;
                Vec3d velocity = vehicle.getVelocity();
                double motionY = mc.options.jumpKey.isPressed() ? boatSpeed.getValueFloat() : 0;
                vehicle.setVelocity(new Vec3d(velocity.x + boatSpeed.getValueFloat(), motionY, velocity.z + boatSpeed.getValueFloat()));
            }
        }
        super.onTick();
    }
}
