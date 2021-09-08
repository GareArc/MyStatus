package com.mystatus.application.resources.papi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.restlet.resource.Get;

import java.util.UUID;

public class PapiPlayerResource extends PapiResource {

    @Get("txt")
    public String getPlaceHolder(){
        String uuid = getAttribute("uuid");
        String placeHolderStr = getAttribute("placeholder");
        Player p;
        try{
            p = Bukkit.getPlayer(UUID.fromString(uuid));
        }catch (IllegalArgumentException iae){
            return "Invalid UUID.";
        }
        if(p == null){
            return "Player not exist or currently in the respawn event.";
        }
        return PlaceholderAPI.setPlaceholders(p, buildPlaceHolderStr(placeHolderStr));
    }

}
