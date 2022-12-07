package unlimited.litehacks.mods.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import unlimited.litehacks.gui.clickgui.ClickGui;
import unlimited.litehacks.mods.Module;
import unlimited.litehacks.mods.settings.NumberSetting;
import unlimited.litehacks.util.*;

import java.awt.*;

public class Tracers extends Module {
    protected static MinecraftClient mc = MinecraftClient.getInstance();
    public static NumberSetting setTracerColor = new NumberSetting("Color", 0, 255, 1, 0.1);
    public static NumberSetting setTracerOpacity = new NumberSetting("Opq", 0, 255, 100, 0.1);
    public static NumberSetting setTracerThickness = new NumberSetting("Thickness", 0, 10, 5, 0.1);


    public Tracers() {
        super("Tracers", "draws tracers towards entities", Category.RENDER);
        addSettings(setTracerColor, setTracerThickness);
    }


    @Override
    public void onEnable() {
        assert mc.player != null;


        super.onDisable();
    }

    @Override
    public void onDisable() {


        super.onDisable();
    }

    @Override
    public void onTick() {
        assert mc.player != null;

        super.onTick();
    }











}




