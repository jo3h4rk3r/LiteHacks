package unlimited.litehacks.mods;

import net.minecraft.client.MinecraftClient;
import unlimited.litehacks.mods.settings.Setting;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private Category category;
    private String displayName;
    private String name;
    private String description;
    private boolean enabled;
    private int key;

    private List<Setting> settings = new ArrayList<>();

    protected MinecraftClient mc = MinecraftClient.getInstance();


    public Module(String name, String description, Category category) {

        this.displayName = name;
        this.name = name;
        this.description = description;
        this.category = category;

    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public void addSettings(Setting... settings) {
        for (Setting setting : settings) addSetting(setting);
    }

    public void toggle(){
        this.enabled = !this.enabled;

        if (enabled){
            onEnable();
        } else {
            onDisable();
        }


    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled){
            onEnable();
        } else {
            onDisable();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Category {
        RENDER("RENDER"),
        WORLD("WORLD"),
        COMBAT("COMBAT"),
        EXPLOIT("EXPLOIT"),
        MOVEMENT("MOVEMENT");


        public String name;

        private Category(String name) {
            this.name = name;
        }

    }



}
