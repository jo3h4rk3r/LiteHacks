package unlimited.litehacks.osd;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.PlayPingS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unlimited.litehacks.gui.clickgui.ClickGui;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.ModuleManager;
import unlimited.litehacks.util.FabricReflect;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Hud {
    private static MinecraftClient mc = MinecraftClient.getInstance();
    private static long prevTime = 0;
    public static String tps;
    public static long lastPacket = 0;
    private static double rawTps = 20;
    private static int counter = 0;
    private static String ping = "null";

    public static void render(MatrixStack matrix, float tickDelta) {
        int fps = (int) FabricReflect.getFieldValue(MinecraftClient.getInstance(), "field_1738", "currentFps");
        int time = (int) (System.currentTimeMillis() - lastPacket);
        assert mc.player != null;





        Vec3d vec = mc.player.getPos();
        BlockPos pos = mc.player.getBlockPos();
        assert mc.world != null;
        boolean nether = mc.world.getRegistryKey().getValue().getPath().contains("nether");
        BlockPos pos2 = nether ? new BlockPos(vec.getX() * 8, vec.getY(), vec.getZ() * 8)
                : new BlockPos(vec.getX() / 8, vec.getY(), vec.getZ() / 8);

        String playerPos = "§9POS: " + (nether ? "\u00a74" : "\u00a7b") + pos.getX() + " " + pos.getY() + " " + pos.getZ()
                + " \u00a77[" + (nether ? "\u00a7b" : "\u00a74") + pos2.getX() + " " + pos2.getY() + " " + pos2.getZ() + "\u00a77]";
        PlayerListEntry playerEntry = mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId());

        int tx = (int) mc.player.networkHandler.getConnection().getAveragePacketsSent();
        int rx = (int) mc.player.networkHandler.getConnection().getAveragePacketsReceived();
        int latency = playerEntry == null ? 0 : playerEntry.getLatency();
        if (latency < 100) ping = "§a" + latency + "ms";
        if (latency > 100) ping = "§6" + latency + "ms";
        if (latency > 200) ping = "§c" + latency + "ms";
        if (latency > 400) ping = "§4" + latency + "ms";





        mc.textRenderer.drawWithShadow(matrix, "§5§lLiteHacks v1.0", 10, 10, -1);
        mc.textRenderer.drawWithShadow(matrix, "§b§lFPS: §f" + fps, 10, 30, -1);
      //  mc.textRenderer.drawWithShadow(matrix, "§f§m              ", 10, 36, -1);
        mc.textRenderer.drawWithShadow(matrix, "§3§lTPS: " + tps, 10, 50, -1);
        mc.textRenderer.drawWithShadow(matrix, "§3§lPING: " + ping, 10, 60, -1);
        mc.textRenderer.drawWithShadow(matrix, "§9§lTX: §f" + tx, 10, 80, -1);
        mc.textRenderer.drawWithShadow(matrix, "§9§lRX: §f" + rx, 10, 90, -1);
        mc.textRenderer.drawWithShadow(matrix, playerPos, mc.getWindow().getScaledWidth() / 2 - 72, 10, -1);
       // mc.textRenderer.drawWithShadow(matrix, mc.fpsDebugString, 200, 10, -1);

           // if (time > 7500) time = 1200;
            if (time >= 1000) {
                counter++;
                if (counter == 1200) {
                    counter = 0;
                }
                String suffix = counter >= 800 ? "...." : counter >= 500 ? "..." : counter >= 300 ? ".." : counter >= 100 ? "." : "" ;
                mc.textRenderer.drawWithShadow(matrix, "§cConnection lost! Reconnecting" + suffix, mc.getWindow().getScaledWidth() / 2 - 75, 25, -1);
            }


        renderArrayList(matrix);
    }

    public static void renderArrayList(MatrixStack matrix) {
        double index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();

        List<Module> enabled = ModuleManager.INSTANCE.getEnabledModules();

        enabled.sort(Comparator.comparingInt(m -> mc.textRenderer.getWidth(((Module)m).getDisplayName())).reversed());

        for (Module module : enabled) {
            mc.textRenderer.drawWithShadow(matrix, module.getDisplayName(),
                    (sWidth - 10) - mc.textRenderer.getWidth(module.getDisplayName()),
                    (float) (10 + (index * mc.textRenderer.fontHeight)), getRainbow(1,2,20, 0));
            index++;
            index = (index + 0.1);
        }
    }

    private static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }

    public static void onPacket(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci) {
        lastPacket = System.currentTimeMillis();

        if (packet instanceof WorldTimeUpdateS2CPacket) {
            long time = System.currentTimeMillis();
            long timeOffset = Math.abs(1000 - (time - prevTime)) + 1000;
            rawTps = Math.round(MathHelper.clamp(20 / (timeOffset / 1000d), 0, 20) * 100d) / 100d;
            int time2 = (int) (System.currentTimeMillis() - lastPacket);
            String suffix = time2 >= 7500 ? "...." : time2 >= 5000 ? "..." : time2 >= 2500 ? ".." : time2 >= 1200 ? ".." : "";

            if (rawTps > 18) tps = "§a" + rawTps + suffix;
            if (rawTps < 18) tps = "§6" + rawTps + suffix;
            if (rawTps < 16) tps = "§c" + rawTps + suffix;
            if (rawTps < 15) tps = "§4" + rawTps + suffix;



            prevTime = time;
        }
    }

}
