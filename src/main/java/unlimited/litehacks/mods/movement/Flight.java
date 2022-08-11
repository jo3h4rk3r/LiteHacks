package unlimited.litehacks.mods.movement;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class Flight extends Module {

    public NumberSetting flySpeed = new NumberSetting("Speed", 0, 10, 1, 0.1);
    public BooleanSetting antiKick = new BooleanSetting("Anti-Kick", true);
    public ModeSetting testMode = new ModeSetting("Mode", "Test", "Test", "Test2", "Test3");

    public Flight() {
        super("Flight", "Toggles creative fly", Category.MOVEMENT);
        addSettings(flySpeed, antiKick, testMode);
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
            mc.player.getAbilities().setFlySpeed((flySpeed.getValueFloat()));
            mc.player.getAbilities().allowFlying = true;
        }
        super.onTick();
    }



}
