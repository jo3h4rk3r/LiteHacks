package unlimited.litehacks.mods.render;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.BooleanSetting;
import unlimited.litehacks.mods.settings.ModeSetting;
import unlimited.litehacks.mods.settings.NumberSetting;

public class NightVision extends Module {
    public static NumberSetting gammaValue = new NumberSetting("Gamma", 0, 12, 9, 0.1);
    public ModeSetting visionMode = new ModeSetting("Mode", "Potion", "Potion", "Gamma");

    public NightVision() {
        super("NightVison", "Gives you night vision", Category.RENDER);
        addSettings(gammaValue, visionMode);
    }

    @Override
    public void onEnable() {
        assert mc.player != null;


        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (mc.options.getGamma().getValue() > 1) {
            double g = mc.options.getGamma().getValue();

            while (g > 1) {
                double nextStep = Math.max(g - 1.6, 1);
                mc.options.getGamma().setValue(nextStep);
                g -= 1.6;
            }
        }


        if (mc.player != null) {
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }


        super.onDisable();
    }

    @Override
    public void onTick() {
        if (mc.player != null) {

            if (visionMode.isMode("Gamma")) {
                if (mc.options.getGamma().getValue() < gammaValue.getValue()) {
                    mc.options.getGamma().setValue(Math.min(mc.options.getGamma().getValue() + 1, gammaValue.getValue()));
                } else if (mc.options.getGamma().getValue() > gammaValue.getValueFloat()) {
                    mc.options.getGamma().setValue(Math.max(mc.options.getGamma().getValue() - 1, gammaValue.getValue()));
                }
            } else if (visionMode.isMode("Potion")) {
                mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 500, 0));
            }
        }


        super.onTick();
    }



}
