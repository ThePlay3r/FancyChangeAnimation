package me.pljr.fancychangeanimation;

import me.pljr.fancychangeanimation.listeners.FoodLevelChangeListener;
import me.pljr.fancychangeanimation.listeners.PlayerExpChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FancyChangeAnimation extends JavaPlugin {
    private static FancyChangeAnimation instance;
    private static int speed;

    @Override
    public void onEnable() {
        instance = this;
        setupConfig();
        loadListeners();
    }

    private void setupConfig(){
        saveDefaultConfig();
        speed = getConfig().getInt("speed");
    }

    private void loadListeners(){
        getServer().getPluginManager().registerEvents(new PlayerExpChangeListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
    }

    public static int getSpeed() {
        return speed;
    }

    public static FancyChangeAnimation getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
