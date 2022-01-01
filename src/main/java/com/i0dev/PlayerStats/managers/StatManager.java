package com.i0dev.PlayerStats.managers;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.config.GeneralConfig;
import com.i0dev.PlayerStats.objects.IndexableSkullConfigItem;
import com.i0dev.PlayerStats.objects.StatStorage;
import com.i0dev.PlayerStats.objects.StatTye;
import com.i0dev.PlayerStats.objects.StatsInventoryHolder;
import com.i0dev.PlayerStats.templates.AbstractManager;
import com.i0dev.PlayerStats.utility.ConfigUtil;
import com.i0dev.PlayerStats.utility.MsgUtil;
import com.i0dev.PlayerStats.utility.SkullUtil;
import com.i0dev.PlayerStats.utility.Utility;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class StatManager extends AbstractManager {
    public StatManager(Heart heart) {
        super(heart);
    }

    BukkitTask saveInterval;

    @Override
    public void initialize() {
        saveInterval = Bukkit.getScheduler().runTaskTimerAsynchronously(heart, this::save, 2 * (60L * 20L), 5 * (60L * 20L));
    }

    @Override
    public void deinitialize() {
        if (saveInterval != null) saveInterval.cancel();
        saveInterval = null;
    }

    public List<String> getStatTypes() {
        List<String> ret = new ArrayList<>();
        for (StatTye value : StatTye.values()) {
            ret.add(value.name().toLowerCase());
        }
        return ret;
    }

    public void save() {
        ConfigUtil.save(heart.storage());
    }

    public void setStat(UUID player, StatTye statType, long amount) {
        StatStorage obj = getStatStorage(player);
        switch (statType) {
            case BATS_KILLED:
                obj.setBats_killed(amount);
                break;
            case BLOCKS_BROKEN:
                obj.setBlocks_broken(amount);
                break;
            case BLOCKS_PLACED:
                obj.setBlocks_placed(amount);
                break;
            case CANE_BROKEN:
                obj.setCane_broken(amount);
                break;
            case CANE_PLACED:
                obj.setCane_placed(amount);
                break;
            case TIME_CONNECTED:
                obj.setTime_connected(amount);
                break;
            case FISH_CAUGHT:
                obj.setFish_caught(amount);
                break;
            case KOTHS_CAPTURED:
                obj.setKoths_captured(amount);
                save();
                break;
            case MOBS_KILLED:
                obj.setMobs_killed(amount);
                break;
            case PLAYERS_KILLED:
                obj.setPlayers_killed(amount);
                break;
            case TIMES_DIED:
                obj.setTimes_died(amount);
                break;
            case MAP_POINTS:
                obj.setMap_points(amount);
                break;
        }
    }

    public void increaseStat(UUID player, StatTye statType, int amount) {

        StatStorage obj = getStatStorage(player);
        switch (statType) {
            case BATS_KILLED:
                obj.setBats_killed(obj.getBats_killed() + amount);
                break;
            case BLOCKS_BROKEN:
                obj.setBlocks_broken(obj.getBlocks_broken() + amount);
                break;
            case BLOCKS_PLACED:
                obj.setBlocks_placed(obj.getBlocks_placed() + amount);
                break;
            case CANE_BROKEN:
                obj.setCane_broken(obj.getCane_broken() + amount);
                break;
            case CANE_PLACED:
                obj.setCane_placed(obj.getCane_placed() + amount);
                break;
            case TIME_CONNECTED:
                obj.setTime_connected(obj.getTime_connected() + amount);
                break;
            case FISH_CAUGHT:
                obj.setFish_caught(obj.getFish_caught() + amount);
                break;
            case KOTHS_CAPTURED:
                obj.setKoths_captured(obj.getKoths_captured() + amount);
                save();
                break;
            case MOBS_KILLED:
                obj.setMobs_killed(obj.getMobs_killed() + amount);
                break;
            case PLAYERS_KILLED:
                obj.setPlayers_killed(obj.getPlayers_killed() + amount);
                break;
            case TIMES_DIED:
                obj.setTimes_died(obj.getTimes_died() + amount);
                break;
            case MAP_POINTS:
                obj.setMap_points(obj.getMap_points() + amount);
                break;
        }
    }

    public StatStorage getStatStorage(UUID player) {
        StatStorage storage = null;
        if (player != null)
            storage = heart.storage().getStats().stream().filter(statStorage -> statStorage.getUuid().equals(player.toString())).findFirst().orElse(null);

        if (storage == null) {
            storage = new StatStorage();
            storage.setUuid(player.toString());
            heart.storage().getStats().add(storage);
            save();
        }
        return storage;
    }

    public Inventory getStatsInventory(OfflinePlayer player) {
        StatStorage storage = getStatStorage(player.getUniqueId());
        GeneralConfig cnf = heart.cnf();
        Inventory inv = Bukkit.createInventory(new StatsInventoryHolder(), cnf.getStatsInventorySize(), MsgUtil.full(null, heart.cnf().getStatsInventoryTitle(), new MsgUtil.Pair<>("{player}", player.getName())));

        List<IndexableSkullConfigItem> items = Arrays.asList(
                cnf.getStats_skull_cane_broken(),
                cnf.getStats_skull_cane_placed(),
                cnf.getStats_skull_time_connected(),
                cnf.getStats_skull_koths_captured(),
                cnf.getStats_skull_players_killed(),
                cnf.getStats_skull_mobs_killed(),
                cnf.getStats_skull_times_died(),
                cnf.getStats_skull_fish_caught(),
                cnf.getStats_skull_blocks_broken(),
                cnf.getStats_skull_blocks_placed(),
                cnf.getStats_skull_bats_killed(),
                cnf.getStats_skull_map_points()
        );

        NumberFormat nf = NumberFormat.getIntegerInstance();

        for (int i = 0; i < items.size(); i++) {
            IndexableSkullConfigItem item = items.get(i);

            String amount = "";
            switch (i) {
                case 0:
                    amount = nf.format(storage.getCane_broken()) + "";
                    break;
                case 1:
                    amount = nf.format(storage.getCane_placed()) + "";
                    break;
                case 2:
                    amount = Utility.formatTimePeriod(storage.getTime_connected() * 1000L);
                    break;
                case 3:
                    amount = nf.format(storage.getKoths_captured()) + "";
                    break;
                case 4:
                    amount = nf.format(storage.getPlayers_killed()) + "";
                    break;
                case 5:
                    amount = nf.format(storage.getMobs_killed()) + "";
                    break;
                case 6:
                    amount = nf.format(storage.getTimes_died()) + "";
                    break;
                case 7:
                    amount = nf.format(storage.getFish_caught()) + "";
                    break;
                case 8:
                    amount = nf.format(storage.getBlocks_broken()) + "";
                    break;
                case 9:
                    amount = nf.format(storage.getBlocks_placed()) + "";
                    break;
                case 10:
                    amount = nf.format(storage.getBats_killed()) + "";
                    break;
                case 11:
                    amount = nf.format(storage.getMap_points()) + "";
                    break;
            }

            ItemStack skull = SkullUtil.itemFromBase64(item.getSkullData());

            Utility.modifyItemStackFromConfigItem(skull, item, new MsgUtil.Pair<>("{amount}", amount), new MsgUtil.Pair<>("{player}", player.getName()));

            inv.setItem(item.getIndex(), skull);
        }

        Utility.fillInventoryWithBorder(inv, Utility.makeItemStackFromConfigItem(cnf.getStats_item_border()));

        return inv;
    }

}
