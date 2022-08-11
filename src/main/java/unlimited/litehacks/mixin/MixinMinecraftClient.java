package unlimited.litehacks.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unlimited.litehacks.keybinds.KeybindingHandler;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci){
        //KeybindingHandler.INSTANCE.onTick();

    }

}
