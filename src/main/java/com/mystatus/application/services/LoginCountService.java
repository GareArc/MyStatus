package com.mystatus.application.services;

import me.clip.placeholderapi.PlaceholderAPI;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;

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

    public Representation getCount(){
        return new JacksonRepresentation<>(count);
    }

    public String getServerName(){
        return PlaceholderAPI.setPlaceholders(null, "%server_name%");
    }

}
