package com.i0dev.PlayerStats.hooks;

import com.i0dev.MapPoints.Heart;
import com.i0dev.MapPoints.config.StorageConfig;
import com.i0dev.MapPoints.managers.PointsManager;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class i0devMapPointsHook {

    static Heart heart;

    public static long getMapPoints(UUID uuid) {
        return heart.getManager(PointsManager.class).getAmount(uuid);
    }

    public static void setup() {
        heart = ((Heart) Bukkit.getPluginManager().getPlugin("MapPoints"));
    }

    public static List<UUID> getAllUUIDs() {
        setup();
        List<UUID> ret = new ArrayList<>();
        for (String id : heart.getConfig(StorageConfig.class).getStorage().keySet()) {
            UUID uuid = UUID.fromString(id);
            ret.add(uuid);
        }
        return ret;
    }


}
