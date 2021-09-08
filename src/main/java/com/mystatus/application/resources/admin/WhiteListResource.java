package com.mystatus.application.resources.admin;

import com.mystatus.application.ServerManager;
import org.bukkit.Bukkit;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class WhiteListResource extends ServerResource {
    @Post
    public String addWhiteList(){
        ServerManager sm = ServerManager.getInstance();
        String username = getAttribute("username");
        if(isInWhiteList(username)) return "Player already in whitelist.";
        if(sm.executeCommand("whitelist add " + username)) return "succeed";
        return "Internal command error. Please contact Gare_TH.";
    }

    @Get
    public boolean whiteListCheck(){
        String username = getAttribute("username");
        return isInWhiteList(username);
    }

    // Will be removed.
    private boolean isInWhiteList(String username){
        return Bukkit.getWhitelistedPlayers().contains(Bukkit.getOfflinePlayer(username));
    }
}
