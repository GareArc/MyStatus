package com.mystatus.application;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.springframework.stereotype.Component;

@Component
public class ServerManager {
    private Server server;
    private Plugin myPlugin = null;

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
}
