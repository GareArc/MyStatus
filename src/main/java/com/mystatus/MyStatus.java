package com.mystatus;

import com.mystatus.application.RESTApplication;
import com.mystatus.application.listeners.LoginListener;
import com.mystatus.application.services.LoginCountService;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.Restlet;

public class MyStatus extends JavaPlugin {


    /**
     * When plugin is enabled.
     * */
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LoginListener(), this);

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

        // Default port is 7850.  Will be configurable in the future.
        component.getServers().add(Protocol.HTTP, 7850);
        // Default prefix is "api". Will be configurable in the future.
        component.getDefaultHost().attach("/" + "api", new RESTApplication());

        // Start
        component.start();
    }
}
