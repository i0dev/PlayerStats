package com.i0dev.PlayerStats.commands;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.config.GeneralConfig;
import com.i0dev.PlayerStats.config.MessageConfig;
import com.i0dev.PlayerStats.managers.StatManager;
import com.i0dev.PlayerStats.templates.AbstractCommand;
import com.i0dev.PlayerStats.utility.MsgUtil;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CmdPlayerStats extends AbstractCommand {

    MessageConfig msg;
    GeneralConfig config;

    public CmdPlayerStats(Heart heart, String command) {
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
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            MsgUtil.msg(sender, msg.getHelpPageHeader());
            MsgUtil.msg(sender, msg.getHelpUsage());
            MsgUtil.msg(sender, msg.getReloadUsage());
            MsgUtil.msg(sender, msg.getStatsUsage());
            MsgUtil.msg(sender, msg.getLeaderboardUsage());
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("playerstats.reload.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            getHeart().reload();
            MsgUtil.msg(sender, msg.getReloadedConfig());
            return;
        }

        if (args[0].equalsIgnoreCase("save")) {
            if (!sender.hasPermission("playerstats.save.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            heart.getManager(StatManager.class).save();
            MsgUtil.msg(sender, msg.getForceSaved());
            return;
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompleteHelper(args[0], Arrays.asList("reload", "help", "save"));
        return blank;
    }
}
