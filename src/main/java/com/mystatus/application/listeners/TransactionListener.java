package com.mystatus.application.listeners;

import com.Acrobot.ChestShop.Events.TransactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Test file for the meaning of "exact price". Will be deleted later.
 * */
public class TransactionListener implements Listener {
    @EventHandler
    public void notifyExactPrice(TransactionEvent e){
        e.getClient().sendMessage("The exact price of this transaction is " + e.getExactPrice());
    }

}
