package unlimited.litehacks.mods.movement;

import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class Flight extends Module {

    public NumberSetting flySpeed = new NumberSetting("Speed", 0, 1, 0.1, 0.1);
    public BooleanSetting antiKick = new BooleanSetting("Anti-Kick", true);
    public ModeSetting flyMode = new ModeSetting("Type", "Creative", "Creative", "HomeMade", "JetPack");

    public Flight() {
        super("Flight", "Toggles creative fly", Category.MOVEMENT);
        addSettings(flySpeed, antiKick, flyMode);
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
        mc.player.getAbilities().allowFlying = false;
        mc.player.getAbilities().flying = false;
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (mc.player != null) {
            if (flyMode.isMode("Vanilla")) {
                mc.player.getAbilities().setFlySpeed(0.05F);
                mc.player.getAbilities().allowFlying = true;
                mc.player.getAbilities().flying = true;
            } else if (flyMode.isMode("HomeMade")){
                mc.player.getAbilities().setFlySpeed((flySpeed.getValueFloat()));
                mc.player.getAbilities().allowFlying = true;
            } else if (flyMode.isMode("JetPack")) {

                double speed = 0.1;

                if (InputUtil.isKeyPressed(mc.getWindow().getHandle(), InputUtil.fromTranslationKey(mc.options.jumpKey.getBoundKeyTranslationKey()).getCode())) {
                    mc.player.jump();
                    //System.out.println("Bro wtf? ! XD");

                } else {
                    if (InputUtil.isKeyPressed(mc.getWindow().getHandle(), InputUtil.fromTranslationKey(mc.options.jumpKey.getBoundKeyTranslationKey()).getCode())) {
                        mc.player.updatePosition(mc.player.getX() - speed / 10f, mc.player.getY() - speed / 10f, mc.player.getZ() - speed / 10f);

                    }
                }


                Vec3d forward = new Vec3d(0, 0, speed).rotateY(-(float) Math.toRadians(mc.player.getYaw()));
                Vec3d strafe = forward.rotateY((float) Math.toRadians(90));

                if (mc.options.leftKey.isPressed())
                    mc.player.setVelocity(mc.player.getVelocity().add(strafe.x, 0, strafe.z));
                if (mc.options.rightKey.isPressed())
                    mc.player.setVelocity(mc.player.getVelocity().add(-strafe.x, 0, -strafe.z));



                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));





            }
        }
        super.onTick();
    }



}
