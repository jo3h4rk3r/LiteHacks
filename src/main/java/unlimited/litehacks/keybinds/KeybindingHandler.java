package unlimited.litehacks.keybinds;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.ChatVisibility;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unlimited.litehacks.Litehacks;
import unlimited.litehacks.gui.clickgui.ClickGui;
import unlimited.litehacks.gui.ui;
import unlimited.litehacks.mixin.MixinKeyboard;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.ModuleManager;

public class KeybindingHandler {
    protected MinecraftClient mc = MinecraftClient.getInstance();
    public static KeybindingHandler INSTANCE = new KeybindingHandler();
    //public static KeybindingHandler OnTick = new KeybindingHandler();
    private boolean uiIsOpen = false;


    public KeybindingHandler() {

    }

    public void onTick() {
        if (mc.player !=null) {
            for (Module module : ModuleManager.INSTANCE.getEnabledModules()) {
                module.onTick();
            }
        }
    }

    public void onKeyPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        Screen currentScreen = mc.currentScreen;


        if (action == GLFW.GLFW_PRESS) {
            for (Module module : ModuleManager.INSTANCE.getModules()) {
                if (key == module.getKey()) module.toggle();
            }

            if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(ClickGui.INSTANCE);

        }



    }
}
