package com.mystatus.application;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ServerManager {
    private final Server server;
    private Plugin myPlugin = null;
    private static ServerManager instance = null;

    public static ServerManager getInstance(){
        if(instance == null) instance = new ServerManager();
        return instance;
    }

    private ServerManager(){
        server = Bukkit.getServer();
    }

    public void setMyPlugin(Plugin p){
        myPlugin = p;
    }

    public Player getPlayerWithName(String name){
        return server.getPlayerExact(name);
    }

    public void sendTxtToAll(String txt){
        server.broadcastMessage(txt);
    }

    public boolean executeCommand(String cmd) {
        try{
            return server.getScheduler().callSyncMethod(myPlugin,
                () -> server.dispatchCommand(server.getConsoleSender(), cmd))
                .get();
        }catch (Exception e){
            return false;
        }
    }

    public String getPlaceHolderResult(String txt, Player p){
        return PlaceholderAPI.setPlaceholders(p, "%" + txt + "%");
    }
}
