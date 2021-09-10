package com.mystatus.application.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mystatus.application.config.ConfigHandler;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MySQLManager {
    QueryRunner runner;

    private MySQLManager(){
        // MySQL 8.0 +
        String driver = "com.mysql.cj.jdbc.Driver";
        DbUtils.loadDriver(driver);
        ConfigHandler cf = ConfigHandler.getInstance();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(getDBUrl(cf));
        dataSource.setUser(cf.getStringConfig("DB_Username"));
        dataSource.setPassword(cf.getStringConfig("DB_Password"));
        runner = new QueryRunner(dataSource);
    }

    public<T> List<T> SelectQuery(String sql, Class<T> tClass , Object... args) throws SQLException {
        ResultSetHandler<List<T>> h = new BeanListHandler<>(tClass);
        return runner.query(sql, h, args);
    }

    public<T> int UpdateQuery(String sql, Class<T> tClass , Object... args) throws SQLException {
        return runner.update(sql, args);
    }

    public<T> int InsertQuery(String sql, Class<T> tClass , Object... args) throws SQLException {
        return UpdateQuery(sql, tClass, args);
    }

    public<T> int DeleteQuery(String sql, Class<T> tClass , Object... args) throws SQLException {
        return UpdateQuery(sql, tClass, args);
    }


    private String getDBUrl(ConfigHandler cf){
        String localurl = cf.getStringConfig("DB_URL");
        String port = cf.getStringConfig("DB_Port");
        String dbname = cf.getStringConfig("DB_Name");
        String params = cf.getStringConfig("DB_Params");
        return "jdbc:mysql://" + localurl + ':' + port + '/' + dbname + '?' + params;
    }

}
