package unlimited.litehacks.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unlimited.litehacks.keybinds.KeybindingHandler;
import unlimited.litehacks.mods.render.SkyColor;

import java.awt.*;

import static net.minecraft.util.math.MathHelper.hsvToRgb;

@Mixin(ClientWorld.class)
public class MixinClientWorld {
    float h = 0;
    float i = 0;
    float j = 0;


    @Inject(method = "getSkyColor", at = @At("HEAD"), cancellable = true)
    public void getSkyColor(Vec3d cameraPos, float tickDelta, CallbackInfoReturnable<Vec3d> ci) {


        SkyColor.onSkyColor(cameraPos, tickDelta, ci);



    }



    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void onTick(CallbackInfo ci) {
        KeybindingHandler.INSTANCE.onTick();
    }




}
