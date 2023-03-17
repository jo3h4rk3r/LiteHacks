package unlimited.litehacks.mods.movement;

import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.Setting;

import java.util.List;

public class NoFall extends Module {
    public ModeSetting NoFallMode = new ModeSetting("Mode", "Simple", "Simple", "Packet");

    public NoFall() {
        super("NoFall", "eeee", Category.MOVEMENT);
        addSettings(NoFallMode);
    }


    @Override
    public void onTick() {
        if (mc.player != null) {
            if (mc.player.fallDistance > 2.5f && NoFallMode.isMode("Simple")) {
                if (mc.player.isFallFlying())
                    return;
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
            }
            if (mc.player.fallDistance > 2.5f && NoFallMode.isMode("packet")) {
                if (mc.world.getBlockState(mc.player.getBlockPos().add(
                        0, (int) (-1.5 + (mc.player.getVelocity().y * 0.1)), 0)).getBlock() != Blocks.AIR) {
                    mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(false));
                    mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                            mc.player.getX(), mc.player.getY() - 420.69, mc.player.getZ(), true));
                    mc.player.fallDistance = 0;
                }
            }
        }
        super.onTick();
    }
}