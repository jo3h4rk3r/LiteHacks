package unlimited.litehacks.mixin;

import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unlimited.litehacks.keybinds.KeybindingHandler;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.ModuleManager;
import unlimited.litehacks.mods.world.Timer;

import java.awt.desktop.SystemSleepEvent;
import java.util.Arrays;
import java.util.List;

import static unlimited.litehacks.mods.world.Timer.TimerActive;


@Mixin(RenderTickCounter.class)
public class MixinRenderTickCounter {

    @Shadow private float lastFrameDuration;
    @Shadow private float tickDelta;
    @Shadow private long prevTimeMillis;
    @Shadow private float tickTime;

    @Inject(method = "beginRenderTick", at = @At("HEAD"), cancellable = true)
    private void beginRenderTick(long timeMillis, CallbackInfoReturnable<Integer> ci) {
        List<Module> enabled = ModuleManager.INSTANCE.getEnabledModules();



        if (new Timer().isEnabled()) {

            System.out.println("test 2");
        }

        if (TimerActive) {
            System.out.println("test");
            this.lastFrameDuration = (float) (((timeMillis - this.prevTimeMillis) / this.tickTime)
                    * Timer.tickAmount.getValue());
            this.prevTimeMillis = timeMillis;
            this.tickDelta += this.lastFrameDuration;
            int i = (int) this.tickDelta;
            this.tickDelta -= i;

            ci.setReturnValue(i);
        }
    }


}