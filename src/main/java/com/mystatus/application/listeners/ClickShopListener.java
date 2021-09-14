package com.mystatus.application.listeners;

import com.Acrobot.ChestShop.Signs.ChestShopSign;
import com.mystatus.application.utils.CSUtils;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Acrobot.ChestShop.Utils.uBlock;

public class ClickShopListener implements Listener {
    @EventHandler
    public void OnClickShop(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        if(a != Action.LEFT_CLICK_BLOCK) return;

        Block b = e.getClickedBlock();
        if(b == null) return;
        if( b.getBlockData() instanceof Chest || b.getBlockData() instanceof DoubleChest){
            Sign s = uBlock.getConnectedSign(b);
            if(s == null) return;
            if(!ChestShopSign.isValid(s)) return;

            String[] lines = s.getLines();
            p.sendMessage(ChatColor.DARK_GREEN + CSUtils.getInstance().parseShopSign(lines));
        }
    }

}
