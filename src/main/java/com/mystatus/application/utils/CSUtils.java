package com.mystatus.application.utils;

import com.Acrobot.Breeze.Utils.StringUtil;
import com.mystatus.application.config.ConfigHandler;

import java.security.PublicKey;

public class CSUtils {
    private static CSUtils instance;

    private final String infoLine1;
    private final String infoLine2;
    private final String infoLine3;
    private final String infoLine4;
    private final String infoLine5;
    private final String infoLine6;

    private CSUtils(){
        ConfigHandler c = ConfigHandler.getInstance();
        infoLine1 = c.getStringConfig("CS_Info_Line_1");
        infoLine2 = c.getStringConfig("CS_Info_Line_2");
        infoLine3 = c.getStringConfig("CS_Info_Line_3");
        infoLine4 = c.getStringConfig("CS_Info_Line_4");
        infoLine5 = c.getStringConfig("CS_Info_Line_5");
        infoLine6 = c.getStringConfig("CS_Info_Line_6");
    }

    public static CSUtils getInstance(){
        if(instance == null) instance = new CSUtils();
        return instance;
    }

    public String parseShopSign(String[] lines){
        lines = StringUtil.stripColourCodes(lines);
        StringBuilder sb = new StringBuilder(infoLine1).append("\n");
        // process second line
        sb.append(infoLine2.replace("%owner%", lines[0])).append("\n");
        // process third line
        sb.append(infoLine3.replace("%item%", lines[3])).append("\n");
        // process fourth line
        sb.append(infoLine4.replace("%quantity%", lines[1])).append("\n");
        // process fifth line
        String[] partition = lines[2].split(":");
        sb.append(infoLine5
                .replace("%buy%", getPrice(partition[0]))
                .replace("%sell%", getPrice(partition[1])))
                .append("\n");
        // process sixth line
        sb.append(infoLine6).append("\n");
        return sb.toString();
    }

    private String getPrice(String str){
        return str.replaceAll("[^.-0123456789]","");
    }

}
