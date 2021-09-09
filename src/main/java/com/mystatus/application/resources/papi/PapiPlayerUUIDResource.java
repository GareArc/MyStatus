package com.mystatus.application.resources.papi;

import com.mystatus.application.ServerManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.UUID;

public class PapiPlayerUUIDResource extends ServerResource {
    @Get
    public String getPlaceHolder(){
        String uuid = getAttribute("uuid");
        String placeHolderStr = getAttribute("placeholder");
        if(uuid == null || placeHolderStr == null) return "Bad Request";

        Player p;
        try{
            p = Bukkit.getPlayer(UUID.fromString(uuid));
        }catch (IllegalArgumentException e){
            return "Invalid uuid";
        }

        if(p == null) return "Player not online.";
        if(!p.hasPlayedBefore()) return "Player has not played in the server. No data retrieved.";
        if(!p.isWhitelisted()) return "Player has not been registered in the server yet.";

        return ServerManager.getInstance().getPlaceHolderResult(placeHolderStr, p.getPlayer());

    }

}
