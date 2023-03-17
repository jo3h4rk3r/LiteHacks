package unlimited.litehacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.GameVersion;
import net.minecraft.MinecraftVersion;
import net.minecraft.client.MinecraftClient;

import java.util.logging.LogManager;

public class Litehacks implements ModInitializer {
    public static Litehacks INSTANCE = new Litehacks();
    public static String VERSION = MinecraftVersion.create().getName();
    public static String ClientVersion = "v1.0.2";

    @Override
    public void onInitialize() {
        //HudRenderCallback.EVENT.register(new osd9());
        LogManager.getLogManager().getLogger("-=- LiteHacks Init 1 -=-");

    }


}
