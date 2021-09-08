package com.mystatus.application.listeners;

import com.mystatus.application.IoCFactory;
import com.mystatus.application.ServerManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
    @EventHandler
    public void SendPlaceHolderMsg(PlayerJoinEvent e){
        Player p = e.getPlayer();
        IoCFactory.getInstance().getServerManager().sendTxtToAll(ChatColor.GOLD + PlaceholderAPI.setPlaceholders(p, "%player_uuid%"));
    }
}
