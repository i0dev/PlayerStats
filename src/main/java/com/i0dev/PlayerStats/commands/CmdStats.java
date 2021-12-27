package com.i0dev.PlayerStats.commands;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.config.GeneralConfig;
import com.i0dev.PlayerStats.config.MessageConfig;
import com.i0dev.PlayerStats.managers.StatManager;
import com.i0dev.PlayerStats.objects.StatStorage;
import com.i0dev.PlayerStats.templates.AbstractCommand;
import com.i0dev.PlayerStats.utility.MsgUtil;
import com.i0dev.PlayerStats.utility.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.rmi.CORBA.Util;
import java.util.Arrays;
import java.util.List;

public class CmdStats extends AbstractCommand {

    MessageConfig msg;
    GeneralConfig config;

    public CmdStats(Heart heart, String command) {
        super(heart, command);
    }

    @Override
    public void initialize() {
        msg = getHeart().getConfig(MessageConfig.class);
        config = getHeart().getConfig(GeneralConfig.class);
    }

    @Override
    public void deinitialize() {
        msg = null;
        config = null;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            MsgUtil.msg(sender, msg.getCantRunAsConsole());
            return;
        }
        OfflinePlayer player = (Player) sender;
        if (args.length == 1)
            player = MsgUtil.getPlayer(args[0]);
        if (player == null)
            player = Bukkit.getOfflinePlayer(args[0]);
        if (player == null) {
            MsgUtil.msg(sender, msg.getCantFindPlayer(), new MsgUtil.Pair<>("{player}", args[0]));
            return;
        }

        ((Player) sender).openInventory(heart.getManager(StatManager.class).getStatsInventory(player));
        MsgUtil.msg(sender, msg.getOpenedStats(), new MsgUtil.Pair<>("{player}", player.getName()));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) return null;
        return blank;
    }
}
