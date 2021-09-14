package com.mystatus.application.command;

import com.Acrobot.ChestShop.Signs.ChestShopSign;
import com.mystatus.application.utils.CSUtils;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;

public class CSInfoCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(commandSender instanceof HumanEntity sender)) return false;

        Block b = sender.getTargetBlock(null, 10);
        if(!ChestShopSign.isValid(b)) return false;
        String[] lines = ((Sign)b.getState()).getLines();

        sender.sendMessage(ChatColor.DARK_GREEN + CSUtils.getInstance().parseShopSign(lines));
        return true;
    }


}
