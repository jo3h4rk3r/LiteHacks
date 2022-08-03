package unlimited.litehacks.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unlimited.litehacks.osd.Hud;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void renderHud(MatrixStack matrix, float tickDelta, CallbackInfo ci) {
        Hud.render(matrix, tickDelta);
    }
}
