package com.mystatus;

import com.mystatus.application.RESTApplication;
import com.mystatus.application.ServerManager;
import com.mystatus.application.command.CSInfoCmd;
import com.mystatus.application.config.ConfigHandler;
import com.mystatus.application.database.MySQLManager;
import com.mystatus.application.listeners.ClickShopListener;
import com.mystatus.application.listeners.TransactionListener;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class MyStatus extends JavaPlugin {


    /**
     * When plugin is enabled.
     * */
    @Override
    public void onEnable() {
        // Register general events
        registerEvent(new ClickShopListener());

        // ConfigHandler
        ConfigHandler.getInstance().setupConfig(this);

        // Server Manager
        ServerManager.getInstance().setMyPlugin(this);

        // DB Application
        initDBApplication(ConfigHandler.getInstance());

        // Cmd Application
        initCmds();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MyStatus enabled.");
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
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "MyStatus disabled.");
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

    private void initDBApplication(ConfigHandler c){
        Boolean enable = c.getBooleanConfig("DB_Enable");
        if (enable == null || !enable) return;
        // Load Database
        MySQLManager.getInstance();
        // Register Listeners
        registerEvent(new TransactionListener());
    }
    private void initCmds(){
        registerCmd("csinfo", new CSInfoCmd());
    }

    private void registerCmd(String cmdName, CommandExecutor executor){
        PluginCommand cmd = getCommand(cmdName);
        if(cmd == null) return;
        cmd.setExecutor(executor);
    }

    private void registerEvent(Listener l){
        getServer().getPluginManager().registerEvents(l, this);
    }
}
