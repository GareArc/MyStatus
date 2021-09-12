package com.mystatus.application.listeners;

import com.Acrobot.ChestShop.Events.TransactionEvent;
import com.mystatus.application.database.MySQLManager;
import com.mystatus.application.database.Tables;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.SQLException;

public class TransactionListener implements Listener {
    @EventHandler
    public void adToDB(TransactionEvent e) throws SQLException {
        String sql = String.format("INSERT INTO %s (item, count, price, player, type, owner) VALUES(?, ?, ?, ?, ?, ?)",
                Tables.ECO.getName());
        MySQLManager m = MySQLManager.getInstance();
        m.modifyQuery(sql,
                e.getStock()[0].getType().name(),
                e.getStock()[0].getAmount(),
                e.getExactPrice().doubleValue(),
                e.getClient().getName(),
                e.getTransactionType().toString(),
                e.getOwnerAccount().getName());
    }

}
