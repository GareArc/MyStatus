package com.mystatus.application.resources.papi;

import com.mystatus.application.ServerManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.UUID;

public class PapiPlayerResource extends ServerResource {

    @Get
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
        return ServerManager.getInstance().getPlaceHolderResult(placeHolderStr, p);
    }

}
