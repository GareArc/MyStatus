package com.mystatus.application.resources.admin;

import com.mystatus.application.ServerManager;
import com.mystatus.application.exception.CustomException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.UUID;
import java.util.stream.Collectors;

public class WhiteListResource extends ServerResource {
    private final AdminUtil adminUtil = new AdminUtil();

    @Post
    public String addWhiteList() throws CustomException {
        String username = getAttribute("username");
        String auth = getAttribute("auth");

        if(auth == null) throw new CustomException("Password required.");
        if(!adminUtil.isAdmin(auth))  throw new CustomException("Authentication failed. Please check your password.");

        ServerManager sm = ServerManager.getInstance();
        if(isInWhiteList(username, null)) return "Player already in whitelist.";
        if(sm.executeCommand("whitelist add " + username)) return "succeed";
        throw new CustomException("Please contact Gare_TH.");
    }

    @Get
    public boolean whiteListCheck() throws CustomException {
        String uuidStr = getAttribute("uuid");
        UUID uuid;
        try{
            if(uuidStr == null){
                throw new CustomException("Please provide player UUID.");
            }
            uuid = UUID.fromString(uuidStr);
        }catch (IllegalArgumentException e){
            throw new CustomException("Invalid UUID.");
        }
        if(getAttribute("auth") != null) {
            throw new CustomException("GET method do not require authentication.");
        }
        return isInWhiteList(null, uuid);
    }


    private boolean isInWhiteList(String username, UUID uuid){
        if(uuid == null){
            return Bukkit.getWhitelistedPlayers().stream()
                    .map(OfflinePlayer::getName)
                    .collect(Collectors.toList())
                    .contains(username);
        }
        else{
            return Bukkit.getWhitelistedPlayers().stream()
                    .map(OfflinePlayer::getUniqueId)
                    .collect(Collectors.toList())
                    .contains(uuid);
        }
    }
}
