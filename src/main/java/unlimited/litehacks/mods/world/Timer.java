package unlimited.litehacks.mods.world;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unlimited.litehacks.keybinds.KeybindingHandler;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class Timer extends Module {
    public static NumberSetting tickAmount = new NumberSetting("Speed", 0, 20, 1, 0.1);
    public static boolean TimerActive = false;

    public Timer() {
        super("Timer", "Changes world tick speed", Category.WORLD);
        addSettings(tickAmount);
    }


    @Override
    public void onEnable() {
        assert mc.player != null;
        TimerActive = true;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        TimerActive = false;
        super.onDisable();
    }

    @Override
    public void onTick() {
        assert mc.player != null;

        super.onTick();
    }



}
