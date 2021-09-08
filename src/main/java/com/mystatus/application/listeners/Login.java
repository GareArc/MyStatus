package com.mystatus.application.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Login implements Listener {

    @EventHandler
    public void login(PlayerJoinEvent e){
        Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), "%server_name%"));
    }
}
