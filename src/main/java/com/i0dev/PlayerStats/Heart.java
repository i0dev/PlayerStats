package com.i0dev.PlayerStats;

import com.i0dev.PlayerStats.commands.CmdLeaderboard;
import com.i0dev.PlayerStats.commands.CmdPlayerStats;
import com.i0dev.PlayerStats.commands.CmdStats;
import com.i0dev.PlayerStats.config.GeneralConfig;
import com.i0dev.PlayerStats.config.MessageConfig;
import com.i0dev.PlayerStats.config.StorageConfig;
import com.i0dev.PlayerStats.handlers.StatHandler;
import com.i0dev.PlayerStats.managers.StatManager;
import com.i0dev.PlayerStats.templates.AbstractCommand;
import com.i0dev.PlayerStats.templates.AbstractConfiguration;
import com.i0dev.PlayerStats.templates.AbstractListener;
import com.i0dev.PlayerStats.templates.AbstractManager;
import com.i0dev.PlayerStats.utility.ConfigUtil;
import com.i0dev.PlayerStats.utility.MsgUtil;
import com.i0dev.PlayerStats.utility.SQLUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Heart extends JavaPlugin {

    List<AbstractManager> managers = new ArrayList<>();
    List<AbstractConfiguration> configs = new ArrayList<>();

    public static boolean usingPapi;
    public static boolean usingMCoreFactions;

    public static boolean usingJosephKoTH;
    public static boolean usingJosephTokens;
    public static boolean usingEmberMapPoints;
    public static boolean usingLuckPerms;

    @Override
    public void onEnable() {

        usingPapi = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
        Plugin factions = getServer().getPluginManager().getPlugin("Factions");
        usingMCoreFactions = factions != null && factions.getDescription().getVersion().startsWith("2.");
        Plugin josephKoTH = getServer().getPluginManager().getPlugin("koth");
        usingJosephKoTH = josephKoTH != null && josephKoTH.getDescription().getVersion().startsWith("1.0-SNAPSHOT");
        Plugin josephTokens = getServer().getPluginManager().getPlugin("tokens");
        usingJosephTokens = josephTokens != null && josephTokens.getDescription().getVersion().startsWith("1.0-SNAPSHOT");
        usingEmberMapPoints = getServer().getPluginManager().isPluginEnabled("MapPoints");
        usingLuckPerms = getServer().getPluginManager().isPluginEnabled("LuckPerms");

        managers.addAll(Arrays.asList(
                new CmdPlayerStats(this, "PlayerStats"),
                new CmdStats(this, "Stats"),
                new CmdLeaderboard(this, "Leaderboard"),
                new StatManager(this),
                new StatHandler(this)

        ));

        configs.addAll(Arrays.asList(
                new GeneralConfig(this, getDataFolder() + "/General.json"),
                new StorageConfig(this, getDataFolder() + "/Storage.json"),
                new MessageConfig(this, getDataFolder() + "/Messages.json")
        ));

        reload();
        registerManagers();
        // SQLUtil.establishConnection(this, false);

        System.out.println("\u001B[32m" + this.getDescription().getName() + " by: " + this.getDescription().getAuthors().get(0) + " has been enabled.");
    }

    public void reload() {
        // old ~ new
        ArrayList<MsgUtil.Pair<AbstractConfiguration, AbstractConfiguration>> toReplace = new ArrayList<>();
        configs.forEach(abstractConfiguration -> toReplace.add(new MsgUtil.Pair<>(abstractConfiguration, ConfigUtil.load(abstractConfiguration, this))));
        toReplace.forEach(pairs -> {
            configs.remove(pairs.getKey());
            configs.add(pairs.getValue());
        });
    }


    @SneakyThrows
    @Override
    public void onDisable() {
        ConfigUtil.save(storage());
        configs.clear();
        managers.forEach(AbstractManager::deinitialize);
        HandlerList.unregisterAll(this);
        managers.clear();
        Connection connection = SQLUtil.getConnection();
        if (connection != null) connection.close();
        Bukkit.getScheduler().cancelTasks(this);
        System.out.println("\u001B[31m" + this.getDescription().getName() + " by: " + this.getDescription().getAuthors().get(0) + " has been disabled.");
    }

    public <T> T getManager(Class<T> clazz) {
        return (T) managers.stream().filter(manager -> manager.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public <T> T getConfig(Class<T> clazz) {
        return (T) configs.stream().filter(config -> config.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public void registerManagers() {
        managers.forEach(abstractManager -> {
            if (abstractManager.isLoaded()) abstractManager.deinitialize();
            if (abstractManager instanceof AbstractListener)
                getServer().getPluginManager().registerEvents((AbstractListener) abstractManager, this);
            else if (abstractManager instanceof AbstractCommand) {
                getCommand(((AbstractCommand) abstractManager).getCommand()).setExecutor(((AbstractCommand) abstractManager));
                getCommand(((AbstractCommand) abstractManager).getCommand()).setTabCompleter(((AbstractCommand) abstractManager));
            }
            abstractManager.initialize();
            abstractManager.setLoaded(true);
        });
    }

    public GeneralConfig cnf() {
        return getConfig(GeneralConfig.class);
    }

    public MessageConfig msg() {
        return getConfig(MessageConfig.class);
    }

    public StorageConfig storage() {
        return getConfig(StorageConfig.class);
    }

}
