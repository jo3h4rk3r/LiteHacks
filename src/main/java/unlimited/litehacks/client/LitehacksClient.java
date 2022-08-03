package unlimited.litehacks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import unlimited.litehacks.Litehacks;
import unlimited.litehacks.gui.ui;
@Environment(EnvType.CLIENT)
public class LitehacksClient implements ClientModInitializer {



    @Override
    public void onInitializeClient() {



    }

}
