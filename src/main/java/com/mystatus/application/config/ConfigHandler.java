package com.mystatus.application.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHandler {
    private static ConfigHandler instance = null;
    private FileConfiguration config;

    private ConfigHandler(){}

    public static ConfigHandler getInstance(){
        if(instance == null) instance = new ConfigHandler();
        return instance;
    }

    /**
     * Initialize config file. Must be called when loading this plugin.
     * */
    public void setupConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public Boolean getBooleanConfig(String configName){
        return config.getBoolean(configName);
    }
    public String getStringConfig(String configName){
        return config.getString(configName);
    }
    public Integer getIntegerConfig(String configName){
        return config.getInt(configName);
    }
}
