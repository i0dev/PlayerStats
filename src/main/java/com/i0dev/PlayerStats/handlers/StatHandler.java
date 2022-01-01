package com.i0dev.PlayerStats.handlers;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.hooks.i0devMapPointsHook;
import com.i0dev.PlayerStats.hooks.JosephKothHook;
import com.i0dev.PlayerStats.hooks.JosephTokensHook;
import com.i0dev.PlayerStats.managers.StatManager;
import com.i0dev.PlayerStats.objects.StatTye;
import com.i0dev.PlayerStats.objects.StatsInventoryHolder;
import com.i0dev.PlayerStats.templates.AbstractListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class StatHandler extends AbstractListener {
    public StatHandler(Heart heart) {
        super(heart);
    }

    StatManager statManager;
    BukkitTask bukkitTaskExternal;


    @Override
    public void initialize() {
        bukkitTaskExternal = Bukkit.getScheduler().runTaskTimerAsynchronously(heart, updateExternalStatistics, (10 * 20L), 10 * (60 * 20L));
        statManager = heart.getManager(StatManager.class);
    }


    @Override
    public void deinitialize() {
        statManager = null;
        if (bukkitTaskExternal != null) bukkitTaskExternal.cancel();
        bukkitTaskExternal = null;
    }

    // Time Played
    Runnable updateExternalStatistics = () -> {
        // Play time
        Bukkit.getOnlinePlayers().forEach(player -> statManager.setStat(player.getUniqueId(), StatTye.TIME_CONNECTED, player.getStatistic(Statistic.PLAY_ONE_TICK) / 20));

        // Koth
        if (Heart.usingJosephKoTH) {
            for (UUID uuid : JosephKothHook.getAllUUIDs()) {
                statManager.setStat(uuid, StatTye.KOTHS_CAPTURED, JosephKothHook.getKothWins(uuid));
            }
        }
        // Cane
        if (Heart.usingJosephTokens) {
            for (UUID uuid : JosephTokensHook.getAllUUIDs()) {
                statManager.setStat(uuid, StatTye.CANE_BROKEN, JosephTokensHook.getCaneBroken(uuid));
            }
        }
        System.out.println("hit");
        // Map Points
        try {
            if (Heart.usingEmberMapPoints) {
                for (UUID uuid : i0devMapPointsHook.getAllUUIDs()) {
                    statManager.setStat(uuid, StatTye.MAP_POINTS, i0devMapPointsHook.getMapPoints(uuid));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        statManager.setStat(e.getPlayer().getUniqueId(), StatTye.TIME_CONNECTED, e.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK) / 20);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerQuitEvent e) {
        statManager.setStat(e.getPlayer().getUniqueId(), StatTye.TIME_CONNECTED, e.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK) / 20);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerKickEvent e) {
        statManager.setStat(e.getPlayer().getUniqueId(), StatTye.TIME_CONNECTED, e.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK) / 20);
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onCaneBreak(BlockBreakEvent e) {
        if (Heart.usingJosephTokens) return;
        Block block = e.getBlock();
        if (block == null || block.getType() == null) return;
        statManager.increaseStat(e.getPlayer().getUniqueId(), StatTye.BLOCKS_BROKEN, 1);
        if (Material.SUGAR_CANE.equals(block.getType()) || Material.SUGAR_CANE_BLOCK.equals(block.getType())) {
            statManager.increaseStat(e.getPlayer().getUniqueId(), StatTye.CANE_BROKEN, 1);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCanePlace(BlockPlaceEvent e) {
        Block block = e.getBlock();
        if (block == null || block.getType() == null) return;
        statManager.increaseStat(e.getPlayer().getUniqueId(), StatTye.BLOCKS_PLACED, 1);
        if (Material.SUGAR_CANE.equals(block.getType()) || Material.SUGAR_CANE_BLOCK.equals(block.getType())) {
            statManager.increaseStat(e.getPlayer().getUniqueId(), StatTye.CANE_PLACED, 1);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerKill(EntityDeathEvent e) {
        Entity died = e.getEntity();
        Entity killer = e.getEntity().getKiller();
        if (!(died instanceof Player)) return;
        if (killer == null) return;
        statManager.increaseStat(killer.getUniqueId(), StatTye.PLAYERS_KILLED, 1);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMobKill(EntityDeathEvent e) {
        Entity died = e.getEntity();
        Entity killer = e.getEntity().getKiller();
        if (died instanceof Player) return;
        if (killer == null) return;
        statManager.increaseStat(killer.getUniqueId(), StatTye.MOBS_KILLED, 1);
        if (died instanceof Bat) {
            statManager.increaseStat(killer.getUniqueId(), StatTye.BATS_KILLED, 1);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(EntityDeathEvent e) {
        Entity died = e.getEntity();
        if (!(died instanceof Player)) return;
        statManager.increaseStat(died.getUniqueId(), StatTye.TIMES_DIED, 1);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFishCatch(PlayerFishEvent e) {
        statManager.increaseStat(e.getPlayer().getUniqueId(), StatTye.FISH_CAUGHT, 1);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() == null || e.getInventory().getHolder() == null) return;
        if (e.getInventory().getHolder() instanceof StatsInventoryHolder) {
            e.setCancelled(true);
        }
    }


}
