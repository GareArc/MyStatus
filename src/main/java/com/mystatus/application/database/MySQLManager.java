package com.mystatus.application.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mystatus.application.config.ConfigHandler;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLManager {
    private final QueryRunner runner;
    private static MySQLManager instance = null;

    private MySQLManager(){
        // MySQL 8.0 +
        String driver = "com.mysql.cj.jdbc.Driver";
        DbUtils.loadDriver(driver);
        runner = new QueryRunner(getDataSource());
    }

    public static MySQLManager getInstance(){
        if(instance == null) instance = new MySQLManager();
        return instance;
    }

    public void createQuery(String sql) throws SQLException {
        runner.update(sql);
    }

    public<T> List<T> selectQuery(String sql, Class<T> tClass , Object... args) throws SQLException {
        ResultSetHandler<List<T>> h = new BeanListHandler<>(tClass);
        return runner.query(sql, h, args);
    }
    
    public int modifyQuery(String sql, Object... args) throws SQLException {
        return runner.update(sql, args);
    }

    private MysqlDataSource getDataSource(){
        ConfigHandler cf = ConfigHandler.getInstance();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(cf.getStringConfig("DB_Server"));
        dataSource.setDatabaseName(cf.getStringConfig("DB_Name"));
        dataSource.setUser(cf.getStringConfig("DB_Username"));
        dataSource.setPort(cf.getIntegerConfig("DB_Port"));
        dataSource.setPassword(cf.getStringConfig("DB_Password"));
        try {
            dataSource.setCharacterEncoding("UTF-8");
            dataSource.setServerTimezone("UTC");
            dataSource.setRequireSSL(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }

    // Will be moved to other class.
    private void createTable(){
        if(tableExists("mystatusdb")) return;
        String sql = "CREATE TABLE mystatusdb (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  item VARCHAR(45) NULL," +
                "  count INT NULL," +
                "  price DECIMAL(10, 2) NULL," +
                "  player VARCHAR(45) NULL," +
                "  type ENUM('buy', 'sell') NULL," +
                "  owner VARCHAR(45) NULL," +
                "  PRIMARY KEY (`id`));";
        try{
            createQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableExists(String tableName) {
        try{
            DatabaseMetaData meta = runner.getDataSource().getConnection().getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

}
