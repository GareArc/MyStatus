package com.mystatus.application.resources.admin;

import com.mystatus.application.config.ConfigHandler;

public class AdminUtil {
    private final String adminKey;
    private final String ownerKey;
    public AdminUtil(){
        adminKey = ConfigHandler.getInstance().getStringConfig("adminPass");
        ownerKey = ConfigHandler.getInstance().getStringConfig("ownerPass");
    }

    public boolean isAdmin(String pass){
        return pass.equals(adminKey) || pass.equals(ownerKey);
    }

    public boolean isOwner(String pass){
        return pass.equals(ownerKey);
    }
}
