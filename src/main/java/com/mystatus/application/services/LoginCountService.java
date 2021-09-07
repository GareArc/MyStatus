package com.mystatus.application.services;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class LoginCountService {
    private int count = 0;
    private static LoginCountService instance = null;

    public static LoginCountService getInstance(){
        if(instance == null){
            instance = new LoginCountService();
        }
        return instance;
    }

    public void increment(){
        count++;
    }

    @Get
    public Representation getCount(){
        return new JacksonRepresentation<>(count);
    }

}
