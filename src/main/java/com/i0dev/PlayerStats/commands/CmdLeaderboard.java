package com.i0dev.PlayerStats.commands;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.config.GeneralConfig;
import com.i0dev.PlayerStats.config.MessageConfig;
import com.i0dev.PlayerStats.managers.StatManager;
import com.i0dev.PlayerStats.objects.StatStorage;
import com.i0dev.PlayerStats.objects.StatTye;
import com.i0dev.PlayerStats.templates.AbstractCommand;
import com.i0dev.PlayerStats.utility.MsgUtil;
import com.i0dev.PlayerStats.utility.Utility;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.*;

public class CmdLeaderboard extends AbstractCommand {

    MessageConfig msg;
    GeneralConfig config;
    StatManager statManager;

    public CmdLeaderboard(Heart heart, String command) {
        super(heart, command);
    }

    @Override
    public void initialize() {
        msg = getHeart().getConfig(MessageConfig.class);
        config = getHeart().getConfig(GeneralConfig.class);
        statManager = getHeart().getManager(StatManager.class);
    }

    @Override
    public void deinitialize() {
        msg = null;
        statManager = null;
        config = null;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            MsgUtil.msg(sender, msg.getLeaderboardUsage());
            return;
        }
        if (!statManager.getStatTypes().contains(args[0].toLowerCase())) {
            MsgUtil.msg(sender, msg.getCantFindStatType());
            return;
        }

        StatTye statTye = StatTye.valueOf(args[0].toUpperCase());

        MsgUtil.msg(sender, msg.getLeaderboardHeader(), new MsgUtil.Pair<>("{stat}", format(statTye.name().toLowerCase())));

        HashMap<UUID, Long> map = new HashMap<>();

        for (StatStorage stat : heart.storage().getStats()) {
            switch (statTye) {
                case BATS_KILLED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getBats_killed());
                    break;
                case BLOCKS_BROKEN:
                    map.put(UUID.fromString(stat.getUuid()), stat.getBlocks_broken());
                    break;
                case BLOCKS_PLACED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getBlocks_placed());
                    break;
                case CANE_BROKEN:
                    map.put(UUID.fromString(stat.getUuid()), stat.getCane_broken());
                    break;
                case CANE_PLACED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getCane_placed());
                    break;
                case FISH_CAUGHT:
                    map.put(UUID.fromString(stat.getUuid()), stat.getFish_caught());
                    break;
                case KOTHS_CAPTURED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getKoths_captured());
                    break;
                case MOBS_KILLED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getMobs_killed());
                    break;
                case PLAYERS_KILLED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getPlayers_killed());
                    break;
                case TIME_CONNECTED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getTime_connected());
                    break;
                case TIMES_DIED:
                    map.put(UUID.fromString(stat.getUuid()), stat.getTimes_died());
                    break;
            }
        }

        map = sortByValue(map);

        List<UUID> keys = new ArrayList<>(map.keySet());
        List<Long> values = new ArrayList<>(map.values());

        for (int i = 0; i < 9; i++) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(keys.get(i));
            if (player == null) continue;
            long value = values.get(i);
            String valueString = formatValue(statTye, value);

            MsgUtil.msg(sender, msg.getLeaderboardFormat()
                    .replace("{rank}", colorizeRanking(i + 1))
                    .replace("{stat}", valueString)
                    .replace("{player}", player.getName() == null ? Objects.requireNonNull(Utility.getIGNFromUUID(player.getUniqueId().toString())) : player.getName())
            );
        }
        if (sender instanceof Player) {

            MsgUtil.msg(sender, "&6");
            int index = keys.indexOf(((Player) sender).getUniqueId());
            MsgUtil.msg(sender, msg.getYourPosition(), new MsgUtil.Pair<>("{rank}", Utility.ordinal(index + 1)), new MsgUtil.Pair<>("{stat}", formatValue(statTye, values.get(index))));
        }
    }

    public static HashMap<UUID, Long> sortByValue(HashMap<UUID, Long> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<UUID, Long>> list = new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        // put data from sorted list to hashmap
        HashMap<UUID, Long> temp = new LinkedHashMap<>();
        for (Map.Entry<UUID, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static String colorizeRanking(int rank) {
        if (rank == 1) return "&6" + rank + ".";
        if (rank == 2) return "&7" + rank + ".";
        if (rank == 3) return "&c" + rank + ".";
        return "&f" + rank + ".";
    }

    public String format(String msg) {
        return Utility.capitalizeFirst(msg.replace("_", " ").split(" "));
    }

    public String formatValue(StatTye statTye, long value) {
        switch (statTye) {
            case BATS_KILLED:
            case BLOCKS_BROKEN:
            case BLOCKS_PLACED:
            case CANE_BROKEN:
            case CANE_PLACED:
            case FISH_CAUGHT:
            case KOTHS_CAPTURED:
            case MOBS_KILLED:
            case PLAYERS_KILLED:
            case TIMES_DIED:
                return NumberFormat.getNumberInstance().format(value);
            case TIME_CONNECTED:
                return Utility.formatTimePeriod(value * 1000L);

        }
        return "ERROR";
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompleteHelper(args[0], statManager.getStatTypes());
        return blank;
    }
}
