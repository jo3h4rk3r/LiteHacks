package unlimited.litehacks.mods;

import unlimited.litehacks.mods.combat.KillAura;
import unlimited.litehacks.mods.exploit.DeathExplorer;
import unlimited.litehacks.mods.exploit.PlayerCrasher;
import unlimited.litehacks.mods.exploit.ServerCrasher;
import unlimited.litehacks.mods.movement.*;
import unlimited.litehacks.mods.Module.Category;
import unlimited.litehacks.mods.render.NightVision;
import unlimited.litehacks.mods.render.SkyColor;
import unlimited.litehacks.mods.world.SmartTrees;
import unlimited.litehacks.mods.world.Timer;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static final ModuleManager INSTANCE = new ModuleManager();
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addModules();
    }


    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getEnabledModules() {
        List<Module> enabled = new ArrayList<>();
        for (Module module : modules) {
            if (module.isEnabled()) enabled.add(module);
        }
        return enabled;
    }


    public List<Module> getModulesInCategory(Category category) {
        List<Module> categoryModules = new ArrayList<>();

        for (Module mod : modules) {
            if (mod.getCategory() == category) {
                categoryModules.add(mod);
            }
        }

        return categoryModules;
    }



    private void addModules() {
        modules.add(new Flight());
        modules.add(new Sprint());
        modules.add(new SkyColor());
        modules.add(new Speed());
        modules.add(new PlayerCrasher());
        modules.add(new ServerCrasher());
        modules.add(new SmartTrees());
        modules.add(new KillAura());
        modules.add(new DeathExplorer());
        modules.add(new Timer());
        modules.add(new ElytraFly());
        modules.add(new NightVision());

    }


}
