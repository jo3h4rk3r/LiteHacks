package unlimited.litehacks.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unlimited.litehacks.mods.render.SkyColor;

import java.awt.*;

import static net.minecraft.util.math.MathHelper.hsvToRgb;

@Mixin(ClientWorld.class)
public class MixinClientWorld {


    @Inject(method = "getSkyColor", at = @At("HEAD"), cancellable = true)
    public void getSkyColor(Vec3d cameraPos, float tickDelta, CallbackInfoReturnable<Vec3d> ci) {

     //   int scv = SkyColor.SkyColorValue.getValueInt();



        if (SkyColor.skyColorEnabled) {

            int color = Color.HSBtoRGB(1, SkyColor.SkyColorValue.getValueFloat(), 1);




            //Color setColor = Color.getHSBColor(color, color, color);
            ci.setReturnValue(new Vec3d(color, color, color));
            //ci.setReturnValue(new Vec3d(color / 255d, color / 255d, color / 255d));
        }

    }




}
