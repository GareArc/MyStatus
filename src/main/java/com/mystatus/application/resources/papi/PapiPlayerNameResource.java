package com.mystatus.application.resources.papi;

import com.mystatus.application.ServerManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class PapiPlayerNameResource extends ServerResource {

    @Get
    public String getPlaceHolder(){
        String username = getAttribute("username");
        String placeHolderStr = getAttribute("placeholder");
        if(username == null || placeHolderStr == null) return "Bad Request";

        OfflinePlayer p = getTarget(username);
        if(p == null){
            return "Player not exist or currently in the respawn event.";
        }
        return ServerManager.getInstance().getPlaceHolderResult(placeHolderStr, p.getPlayer());
    }

    private OfflinePlayer getTarget(String name){
        List<OfflinePlayer> pl = Bukkit.getWhitelistedPlayers().stream()
                .filter(offlinePlayer -> Objects.equals(offlinePlayer.getName(), name))
                .collect(Collectors.toList());
        return pl.size() == 0? null : pl.get(0);
    }

}
