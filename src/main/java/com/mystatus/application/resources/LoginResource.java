package com.mystatus.application.resources;

import com.mystatus.application.services.LoginCountService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class LoginResource extends ServerResource {
    @Get("json")
    public Representation getLoginCount(){
//        return 1;
        return LoginCountService.getInstance().getCount();
    }

}
