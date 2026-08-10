package me.qq3393525847.sxitemexpansion;

import me.qq3393525847.sxitemexpansion.command.MainCommand;
import me.qq3393525847.sxitemexpansion.config.ConfigManager;
import me.qq3393525847.sxitemexpansion.listener.SXItemSpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SXItemExpansion extends JavaPlugin {

    private ConfigManager configmanager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();

        configmanager = new ConfigManager(this);

        new MainCommand(this).register(getLifecycleManager());

        Bukkit.getPluginManager().registerEvents(new SXItemSpawnListener(this), this);
    }

    public ConfigManager getConfigManager() {
        return configmanager;   
    }

    @Override
    public void onDisable() {}
}
