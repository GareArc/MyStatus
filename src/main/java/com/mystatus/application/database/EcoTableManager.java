package com.mystatus.application.database;

import com.Acrobot.ChestShop.Events.TransactionEvent;

import java.sql.SQLException;

public class EcoTableManager {
    private final MySQLManager dbM;

    public EcoTableManager(MySQLManager m){
        dbM = m;
        createEcoTable();
    }

    public void createEcoTable(){
        if(dbM.tableExists(Tables.ECO.getName())) return;
        String sql = "CREATE TABLE " + Tables.ECO.getName() + " ( " +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  item VARCHAR(45) NULL," +
                "  count INT NULL," +
                "  price DOUBLE NULL," +
                "  player VARCHAR(45) NULL," +
                "  type ENUM('buy', 'sell') NULL," +
                "  owner VARCHAR(45) NULL," +
                "  PRIMARY KEY (`id`));";
        try{
            dbM.createQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTransaction(String name, int amount, double price, String player,
                                  TransactionEvent.TransactionType type, String owner) throws SQLException {
        String sql = String.format("INSERT INTO %s (item, count, price, player, type, owner) VALUES(?, ?, ?, ?, ?, ?)",
                Tables.ECO.getName());
        dbM.modifyQuery(sql, name, amount, price, player, type.toString(), owner);
    }
}
