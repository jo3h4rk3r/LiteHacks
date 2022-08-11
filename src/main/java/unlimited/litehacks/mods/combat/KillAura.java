package unlimited.litehacks.mods.combat;

import com.google.common.collect.Streams;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KillAura extends Module {

    public NumberSetting reachDistance = new NumberSetting("Distance", 0, 20, 5, 0.1);
    public NumberSetting hitDelay = new NumberSetting("Delay", 0, 20, 1, 0.1);
    public BooleanSetting newPvP = new BooleanSetting("1.9 PvP", true);
    public ModeSetting auraType = new ModeSetting("Type", "Direct", "Direct", "Multi", "Aimbot");
    private int delay = 0;


    //ServerPlayerEntity playerEntity = mc.player;
    //public ServerPlayerEntity targetedPlayer = EntityArgumentType.getPlayer(context, "player");
    // String playerName = playerEntity.getGameProfile().getName();
    // String targetName = targetedPlayer.getGameProfile().getName();


    public KillAura() {
        super("KillAura", "Aim bot", Category.COMBAT);
        //this.setKey(GLFW.GLFW_KEY_G);
        addSettings(reachDistance, hitDelay, newPvP, auraType);
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
        for (Entity e : getEntities()) {


            if (auraType.isMode("Aimbot")) {
                facePosAuto(e.getX(), e.getY() + e.getHeight() / 2, e.getZ());
            }

            delay++;
            int reqDelay = (int) Math.rint(20 / hitDelay.getValue());

            boolean cooldownDone;

            if (newPvP.isEnabled()) {
                assert mc.player != null;
                cooldownDone = mc.player.getAttackCooldownProgress(mc.getTickDelta()) == 1.0f;
            } else {
                cooldownDone = (delay > reqDelay || reqDelay == 0);
            }

            if (cooldownDone) {
                if (mc.interactionManager != null) {
                    if (mc.player != null) {

                            mc.interactionManager.attackEntity(mc.player, e);
                            mc.player.swingHand(Hand.MAIN_HAND);

                    }
                }
            }
        }
        super.onTick();
    }



    private List<Entity> getEntities() {
        Stream<Entity> targets;

/*
        if (auraType.isMode("Aimbot")) {
            Optional<Entity> entity = DebugRenderer.getTargetedEntity(mc.player, 7);
            if (entity.isEmpty()) {
                return Collections.emptyList();
            }

            System.out.println("Aimbot");
            targets = Stream.of(entity.get());
        } else

 */

            if (auraType.isMode("Direct")) {
                Optional<Entity> entity = DebugRenderer.getTargetedEntity(mc.player, 7);
                if (entity.isEmpty()) {
                    return Collections.emptyList();
                }
                targets = Stream.of(entity.get());
            } else {
                assert mc.world != null;
                targets = Streams.stream(mc.world.getEntities());
            }




        Comparator<Entity> comparator;
        if (auraType.isMode("Multi")) {
            comparator = Comparator.comparing(e -> {
                Vec3d center = e.getBoundingBox().getCenter();


                double diffX = center.x - mc.player.getX();
                double diffY = center.y - mc.player.getEyeY();
                double diffZ = center.z - mc.player.getZ();

                double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);

                float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
                float pitch = (float) -Math.toDegrees(Math.atan2(diffY, diffXZ));

                return Math.abs(MathHelper.wrapDegrees(yaw - mc.player.getYaw())) + Math.abs(MathHelper.wrapDegrees(pitch - mc.player.getPitch()));
            });
        } else {
            assert mc.player != null;
            comparator = Comparator.comparing(mc.player::distanceTo);
        }






        assert mc.player != null;
        return targets.filter(e -> (mc.player.distanceTo(e) <= reachDistance.getValue() && e.isAlive()
                && isAttackable(e))).sorted(comparator).collect(Collectors.toList());
    }

    public static boolean isAttackable(Entity e) {
        return (e instanceof LivingEntity || e instanceof ShulkerBulletEntity || e instanceof AbstractFireballEntity)
                && e.isAlive()
                && e != MinecraftClient.getInstance().player
                && !(e instanceof ArmorStandEntity
                && !e.isConnectedThroughVehicle(MinecraftClient.getInstance().player));
    }

    public void facePosAuto(double x, double y, double z) {
            facePos(x, y, z);
    }

    public void facePos(double x, double y, double z) {
        float[] rot = getViewingRotation(mc.player, x, y, z);

        mc.options.forwardKey.isPressed();

        mc.player.setYaw(mc.player.getYaw() + MathHelper.wrapDegrees(rot[0] - mc.player.getYaw()));
        mc.player.setPitch(mc.player.getPitch() + MathHelper.wrapDegrees(rot[1] - mc.player.getPitch()));
    }

    public static float[] getViewingRotation(Entity entity, double x, double y, double z) {
        double diffX = x - entity.getX();
        double diffY = y - entity.getEyeY();
        double diffZ = z - entity.getZ();

        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);

        return new float[] {
                (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90f,
                (float) -Math.toDegrees(Math.atan2(diffY, diffXZ)) };
    }


}
