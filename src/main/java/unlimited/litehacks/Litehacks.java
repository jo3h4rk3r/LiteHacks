package unlimited.litehacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import java.util.logging.LogManager;

public class Litehacks implements ModInitializer {
    public static Litehacks INSTANCE = new Litehacks();

    @Override
    public void onInitialize() {
        //HudRenderCallback.EVENT.register(new osd9());
        LogManager.getLogManager().getLogger("-=- LiteHacks Init 1 -=-");

    }


}
