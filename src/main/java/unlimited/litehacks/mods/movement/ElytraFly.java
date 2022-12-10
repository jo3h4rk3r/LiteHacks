package unlimited.litehacks.mods.movement;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class ElytraFly extends Module {

    public NumberSetting elyFlySpeed = new NumberSetting("Speed", 0, 5, 1, 0.1);
    public BooleanSetting elyAntiKick = new BooleanSetting("Anti-Kick", true);
    //public ModeSetting testMode = new ModeSetting("Mode", "Test", "Test", "Test2", "Test3");

    public ElytraFly() {
        super("ElytraFly", "Infinite elytra flight", Category.MOVEMENT);
        addSettings(elyFlySpeed, elyAntiKick);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;
        //mc.player.sendMessage(Text.literal("Flight enabled"));
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        //mc.player.sendMessage(Text.literal("Flight disabled"));

        super.onDisable();
    }

    @Override
    public void onTick() {
        if (mc.player != null) {
            if (mc.player.isFallFlying()) {
                Vec3d vec3d = new Vec3d(0, 0, 0).rotateY(-(float) Math.toRadians(mc.player.getYaw()));

                if (mc.options.jumpKey.isPressed()) vec3d = vec3d.add(0, elyFlySpeed.getValue(), 0).rotateY(-(float) Math.toRadians(mc.player.getYaw()));;
                if (mc.options.sneakKey.isPressed()) vec3d = vec3d.add(0, - elyFlySpeed.getValue(), 0).rotateY(-(float) Math.toRadians(mc.player.getYaw()));;
                if (mc.options.forwardKey.isPressed()) vec3d = vec3d.add(0, 0, elyFlySpeed.getValue()).rotateY(-(float) Math.toRadians(mc.player.getYaw()));;
                if (mc.options.backKey.isPressed()) vec3d = vec3d.add(0, 0, - elyFlySpeed.getValue()).rotateY(-(float) Math.toRadians(mc.player.getYaw()));;
                if (mc.options.rightKey.isPressed()) vec3d = vec3d.add(- elyFlySpeed.getValue(), 0, 0).rotateY(-(float) Math.toRadians(mc.player.getYaw()));;
                if (mc.options.leftKey.isPressed()) vec3d = vec3d.add(elyFlySpeed.getValue(), 0, 0).rotateY(-(float) Math.toRadians(mc.player.getYaw()));;

                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                        mc.player.getX() + vec3d.x, mc.player.getY() - 0.01, mc.player.getZ() + vec3d.z, false));

                mc.player.setVelocity(vec3d.x, vec3d.y, vec3d.z);
            }
        }
        super.onTick();
    }



}
