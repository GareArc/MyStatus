package com.mystatus.application.listeners;

import com.Acrobot.ChestShop.Events.TransactionEvent;
import com.mystatus.application.database.EcoTableManager;
import com.mystatus.application.database.MySQLManager;
import com.mystatus.application.database.Tables;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class TransactionListener implements Listener {
    @EventHandler
    public void adToDB(TransactionEvent e){
        EcoTableManager m = MySQLManager.getInstance().ecoTableManager;
        try{
            int amount = Arrays.stream(e.getStock()).mapToInt(ItemStack::getAmount).sum();
            m.insertTransaction(
                    e.getStock()[0].getType().name(),
                    amount,
                    e.getExactPrice().doubleValue(),
                    e.getClient().getName(),
                    e.getTransactionType(),
                    e.getOwnerAccount().getName());
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

}
