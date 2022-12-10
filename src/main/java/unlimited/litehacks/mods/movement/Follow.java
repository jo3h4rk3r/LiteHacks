package unlimited.litehacks.mods.movement;

import com.google.common.collect.Streams;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
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

public class Follow extends Module {

    public NumberSetting followDistance = new NumberSetting("Distance", 0, 20, 5, 0.1);
    public NumberSetting stopDistance = new NumberSetting("Stop range", 0, 10, 2, 0.1);
    public BooleanSetting MiddleClick = new BooleanSetting("MiddleClick", false);
    public BooleanSetting autoClimb = new BooleanSetting("Climb", true);
    //public BooleanSetting onTarget = new BooleanSetting("Target", true);
    public ModeSetting entityType = new ModeSetting("Type", "All", "All", "Players", "Mobs");
    private boolean buttonHeld = false;
    public Entity targeted;
    private int counter = 0;
    private int following = 0;
    private boolean doodoo = false;

    public Follow() {
        super("Follow", "Follow entities", Category.MOVEMENT);

        addSettings(followDistance, stopDistance, MiddleClick, autoClimb, entityType);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;

    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        mc.options.forwardKey.setPressed(false);
        super.onDisable();
    }

    @Override
    public void onTick() {



        if (MiddleClick.isEnabled()) {


            int button = GLFW.GLFW_MOUSE_BUTTON_MIDDLE;
            if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), button) == GLFW.GLFW_PRESS && !buttonHeld) {
                buttonHeld = true;
                Optional<Entity> entity = DebugRenderer.getTargetedEntity(mc.player, 200);
                if (entity.isPresent()) {
                    Entity e = entity.get();
                    if (e instanceof PlayerEntity && entityType.isMode("Players")) {
                        targeted = e;

                        following++;

                        System.out.println(following + " Players");


                    } else if (entityType.isMode("All")) {

                        targeted = e;

                        following++;

                        System.out.println(following + " All");


                    } else if (entityType.isMode("Mobs") && e instanceof MobEntity) {
                        targeted = e;

                        following++;

                        System.out.println(following + " Mobs");
                    }


                }
            } else if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), button) == GLFW.GLFW_RELEASE) {
                buttonHeld = false;
            }


            if (following == 1 && targeted.isAlive() && targeted.isAttackable() && !mc.player.isInRange(targeted, stopDistance.getValueInt())) {
                Entity e = targeted;


                if (autoClimb.isEnabled()) {



                    if (mc.player.horizontalCollision) {
                        Vec3d velocity = mc.player.getVelocity();
                        //if (velocity.y >= 0.2) {
                        mc.player.setVelocity(velocity.x, 0.2, velocity.z);
                    }



                }
                if (mc.player.horizontalCollision && mc.player.isOnGround()) {
                    mc.player.jump();
                }

                // swim up if necessary
                if (mc.player.isTouchingWater() && mc.player.getY() < targeted.getY()) {
                    mc.player.setVelocity(mc.player.getVelocity().add(0, 0.04, 0));
                }

                facePosAuto(e.getX(), e.getY() + e.getHeight() / 2, e.getZ());
                mc.options.forwardKey.setPressed(true);
                doodoo = true;


            } else if (doodoo) {
                //following = 0;
                mc.options.forwardKey.setPressed(false);
                doodoo = false;
            }

            if (following > 1) {
                following = 0;
            }
        } else {
            for (Entity e : getEntities()) {
                if (e.isAlive()) {
                    if (e instanceof PlayerEntity && entityType.isMode("Players")) {
                        if (e.isAlive() && e.isAttackable() && !mc.player.isInRange(e, stopDistance.getValueInt())) {
                           // Entity e = targeted;
                            mc.options.forwardKey.setPressed(true);
                            facePosAuto(e.getX(), e.getY() + e.getHeight() / 2, e.getZ());
                            doodoo = true;
                        } else if (doodoo) {
                            mc.options.forwardKey.setPressed(false);
                            doodoo = false;
                        }
                    } else if (entityType.isMode("All")) {
                        if (e.isAlive() && e.isAttackable() && !mc.player.isInRange(e, stopDistance.getValueInt())) {
                         //   Entity e = targeted;
                            mc.options.forwardKey.setPressed(true);
                            facePosAuto(e.getX(), e.getY() + e.getHeight() / 2, e.getZ());
                            doodoo = true;
                        } else if (doodoo) {
                            System.out.println("Is player deiasodh" + doodoo);
                            mc.options.forwardKey.setPressed(false);
                            doodoo = false;
                        }
                    } else if (entityType.isMode("Mobs") && e instanceof MobEntity) {
                        if (e.isAlive() && e.isAttackable() && !mc.player.isInRange(e, stopDistance.getValueInt())) {
                           // Entity e = targeted;
                            mc.options.forwardKey.setPressed(true);
                            facePosAuto(e.getX(), e.getY() + e.getHeight() / 2, e.getZ());
                            doodoo = true;
                        } else if (doodoo) {
                            mc.options.forwardKey.setPressed(false);
                            doodoo = false;
                        }
                    }
                } else {
                    mc.options.forwardKey.setPressed(false);
                    doodoo = false;
                }
            }
        }

            


            /*
            if (following == 1 && targeted.world.isClient) {
                //mc.options.forwardKey.setPressed(false);
               // System.out.println("test " + targeted.getPos());
            } else {
               // System.out.println("then wtf lmao");
            }

             */




        super.onTick();
    }


    private List<Entity> getEntities() {
        Stream<Entity> targets;

            assert mc.world != null;
            targets = Streams.stream(mc.world.getEntities());

        Comparator<Entity> comparator;
            assert mc.player != null;
            comparator = Comparator.comparing(mc.player::distanceTo);

        assert mc.player != null;
        return targets.filter(e -> (mc.player.distanceTo(e) <= followDistance.getValue() && e.isAlive()
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
