package com.mystatus;

import com.mystatus.application.RESTApplication;
import com.mystatus.application.ServerManager;
import com.mystatus.application.config.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class MyStatus extends JavaPlugin {


    /**
     * When plugin is enabled.
     * */
    @Override
    public void onEnable() {
        // ConfigHandler
        ConfigHandler.getInstance().setupConfig(this);

        // Server Manager
        ServerManager.getInstance().setMyPlugin(this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin enabled.");
        try{
            start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * When plugin is disabled.
     * */
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Plugin disabled.");
    }

    /**
     * Start REST application on port.
     * */
    public void start() throws Exception {
        Component component = new Component();
        ConfigHandler configHandler = ConfigHandler.getInstance();

        component.getServers().add(Protocol.HTTP, configHandler.getIntegerConfig("port"));

        component.getDefaultHost().attach("/" + configHandler.getStringConfig("prefix"), new RESTApplication());

        // Start
        component.start();
    }
}
