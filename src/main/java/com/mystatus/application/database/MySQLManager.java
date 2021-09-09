package com.mystatus.application.database;

import com.mystatus.application.config.ConfigHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLManager {
    private final String driver = "com.mysql.cj.jdbc.Driver"; // MySQL 8.0 +
    private final String serverUrl;
    private final String port;
    private final String dbname;
    private final String params;
    private final String username;
    private final String password;
    private Connection connection = null;
    private boolean hasConnected = false;
    private static MySQLManager instance = null;

    private MySQLManager() {
        serverUrl = ConfigHandler.getInstance().getStringConfig("DB_URL");
        port = ConfigHandler.getInstance().getStringConfig("DB_Port");
        dbname = ConfigHandler.getInstance().getStringConfig("DB_Name");
        params = ConfigHandler.getInstance().getStringConfig("DB_Params");
        username = ConfigHandler.getInstance().getStringConfig("DB_Username");
        password = ConfigHandler.getInstance().getStringConfig("DB_Password");
        connectToDataBase();
    }

    public static MySQLManager getInstance(){
        if(instance == null) instance = new MySQLManager();
        return instance;
    }


    /**
     * Perform ADD, DELETE and MODIFY operations.
     * */
    public int updateBySQL(String sql, List<Object> params) {
        if(!hasConnected || sql == null) return -1;

        PreparedStatement pst = null;
        int updateCount = 0;

        try {
            pst = connection.prepareStatement(sql);
            if(params != null && !params.isEmpty()){
                for(int i=0;i< params.size(); i++){
                    pst.setObject(i+1, params.get(i)); // mysql index starts from 1.
                }
            }
            // Perform operation
            updateCount = pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            release(null, pst, null);
        }
        return updateCount;

    }

    public<T> T getSingleResult(String sql, List<Object> objects, Class<T> tClass){
        T object = null;
        try{
            ResultSet resultSet = setupPreparedStatement(sql, objects);
            if(resultSet.next()){
                object = getObject(resultSet, tClass);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return object;
    }

    public<T> List<T> getMultiResults(String sql, List<Object> objects, Class<T> tClass){
        List<T> tList = new ArrayList<>();
        try{
            ResultSet resultSet = setupPreparedStatement(sql, objects);
            while(resultSet.next()){
                T object = getObject(resultSet, tClass);
                tList.add(object);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return tList;
    }

    private<T> T getObject(ResultSet resultSet, Class<T> tClass) throws Exception {
        T object = tClass.getDeclaredConstructor().newInstance();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for(int i = 0; i < resultSetMetaData.getColumnCount(); i++){
            // Get column name
            String colName = resultSetMetaData.getColumnName(i + 1); // MySQL index starts from 1.
            PropertyDescriptor pd = new PropertyDescriptor(colName, tClass);
            Method method = pd.getWriteMethod();
            method.invoke(object, resultSet.getObject(colName));
        }
        return object;
    }

    private ResultSet setupPreparedStatement(String sql, List<Object> objects){
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = connection.prepareStatement(sql);
            if (objects != null && !objects.isEmpty()) {
                for (int i = 0; i < objects.size(); i++) {
                    pst.setObject(i + 1, objects.get(i));
                }
            }
            resultSet = pst.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            release(null, pst, null);
        }
        return resultSet;
    }

    private void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private String getDBURL(){
        return "jdbc:mysql://" + serverUrl + ':' + port + '/' + dbname + '?' + params;
    }

    private void connectToDataBase(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(getDBURL(), username, password);
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        hasConnected = true;
    }




}
