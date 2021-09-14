package com.mystatus.application.utils;

import com.Acrobot.Breeze.Utils.StringUtil;

import java.security.PublicKey;

public class CSUtils {
    private static CSUtils instance;

    private CSUtils(){}

    public static CSUtils getInstance(){
        if(instance == null) instance = new CSUtils();
        return instance;
    }

    public String parseShopSign(String[] lines){
        lines = StringUtil.stripColourCodes(lines);
        StringBuilder sb = new StringBuilder("======= 商店信息 =======\n");
        // process first line
        sb.append("拥有者: ");
        if(lines[0].equalsIgnoreCase("Admin Shop")){
            sb.append("系统商店").append("\n");
        }else sb.append(lines[0]).append("\n");
        // process fourth line
        sb.append("物品: ").append(lines[3]).append("\n");
        // process second line
        sb.append("单次交易数量: ").append(lines[1]).append("\n");
        // process third line
        String[] partition = lines[2].split(":");
        sb.append("购买价格: $").append(getPrice(partition[0])).append("\n");
        sb.append("出售价格: $").append(getPrice(partition[1])).append("\n");
        return sb.toString();
    }

    private String getPrice(String str){
        return str.replaceAll("[^.-0123456789]","");
    }

}
