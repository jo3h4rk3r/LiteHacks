package unlimited.litehacks.mods.combat;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class KillAura extends Module {

    public NumberSetting reachDistance = new NumberSetting("Distance", 0, 20, 1, 0.1);
    public ModeSetting auraType = new ModeSetting("Type", "Multi", "Aimbot", "Direct");

    public KillAura() {
        super("KillAura", "Aim bot", Category.COMBAT);
        //this.setKey(GLFW.GLFW_KEY_G);
        addSettings(reachDistance, auraType);
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

        super.onTick();
    }



}
