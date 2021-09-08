package com.mystatus.application.listeners;

import com.mystatus.application.services.LoginCountService;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
    @EventHandler
    public void LoginCount(PlayerJoinEvent e){
        System.out.println("new player detected");
        LoginCountService.getInstance().increment();
    }

    @EventHandler
    public void SendPlaceHolderMsg(PlayerJoinEvent e){
        Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(null, "%server_name%"));
        Player p = e.getPlayer();
        p.sendMessage(ChatColor.GOLD + PlaceholderAPI.setPlaceholders(p, "%player_name%") + "just joined!");
    }
}
