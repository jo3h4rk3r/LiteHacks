package unlimited.litehacks.mods.movement;

import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class Speed extends Module {
    private boolean jumping;
    public NumberSetting groundSpeed = new NumberSetting("Speed", 0, 10, 1, 0.1);
    public ModeSetting speedSetting = new ModeSetting("Mode", "Strafe", "Strafe", "onGround", "miniHop", "Bhop");

    public Speed() {
        super("Speed", "Run faster", Category.MOVEMENT);
        addSettings(groundSpeed, speedSetting);
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

        if (speedSetting.isMode("Strafe")) {
            if ((mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0) /*&& mc.player.isOnGround()*/) {
                if (!mc.player.isSprinting()) {
                    mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_SPRINTING));
                }

                mc.player.setVelocity(new Vec3d(0, mc.player.getVelocity().y, 0));
                mc.player.updateVelocity(groundSpeed.getValueFloat(),
                        new Vec3d(mc.player.sidewaysSpeed, 0, mc.player.forwardSpeed));

                double vel = Math.abs(mc.player.getVelocity().getX()) + Math.abs(mc.player.getVelocity().getZ());

                if (speedSetting.isMode("Strafe") && vel >= 0.12 && mc.player.isOnGround()) {
                    mc.player.updateVelocity(vel >= 0.3 ? 0.0f : 0.15f, new Vec3d(mc.player.sidewaysSpeed, 0, mc.player.forwardSpeed));
                    mc.player.jump();
                }
            }

            /* OnGround */
        } else if (speedSetting.isMode("onGround")) {
            if (mc.options.jumpKey.isPressed() || mc.player.fallDistance > 0.25)
                return;

            double speeds = 0.85 + groundSpeed.getValueFloat() / 30;

            if (jumping && mc.player.getY() >= mc.player.prevY + 0.399994D) {
                mc.player.setVelocity(mc.player.getVelocity().x, -0.9, mc.player.getVelocity().z);
                mc.player.setPos(mc.player.getX(), mc.player.prevY, mc.player.getZ());
                jumping = false;
            }

            if (mc.player.forwardSpeed != 0.0F && !mc.player.horizontalCollision) {
                if (mc.player.verticalCollision) {
                    mc.player.setVelocity(mc.player.getVelocity().x * speeds, mc.player.getVelocity().y, mc.player.getVelocity().z * speeds);
                    jumping = true;
                    mc.player.jump();
                    // 1.0379
                }

                if (jumping && mc.player.getY() >= mc.player.prevY + 0.399994D) {
                    mc.player.setVelocity(mc.player.getVelocity().x, -100, mc.player.getVelocity().z);
                    jumping = false;
                }

            }

            /* MiniHop */
        } else if (speedSetting.isMode("miniHop")) {
            {
                if (mc.player.horizontalCollision || mc.options.jumpKey.isPressed() || mc.player.forwardSpeed == 0)
                    return;

                double speeds = 0.9 + groundSpeed.getValueFloat() / 30;

                if (mc.player.isOnGround()) {
                    mc.player.jump();
                } else if (mc.player.getVelocity().y > 0) {
                    mc.player.setVelocity(mc.player.getVelocity().x * speeds, -1, mc.player.getVelocity().z * speeds);
                    mc.player.input.movementSideways += 1.5F;
                }
            }

                /* Bhop */
            } else if (speedSetting.isMode("Bhop")) {
            {
                if (mc.player.forwardSpeed > 0 && mc.player.isOnGround()) {
                    double speeds = 0.65 + groundSpeed.getValueFloat() / 30;

                    mc.player.jump();
                    mc.player.setVelocity(mc.player.getVelocity().x * speeds, 0.255556, mc.player.getVelocity().z * speeds);
                    mc.player.sidewaysSpeed += 3.0F;
                    mc.player.jump();
                    mc.player.setSprinting(true);
                }
            }

                super.onTick();
        }
    }
}





