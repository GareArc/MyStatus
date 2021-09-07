package com.mystatus.application.listeners;

import com.mystatus.application.services.LoginCountService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
    @EventHandler
    public void OnLogin(PlayerJoinEvent e){
        System.out.println("new player detected");
        LoginCountService.getInstance().increment();
    }
}
