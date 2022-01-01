package com.i0dev.PlayerStats.hooks;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MCoreFactionsHook {

    public static boolean isWilderness(Location location) {
        return BoardColl.get().getFactionAt(PS.valueOf(location)).isNone();
    }

    public static boolean isSystemFaction(Location location) {
        if (isWilderness(location)) return false;
        return BoardColl.get().getFactionAt(PS.valueOf(location)).isSystemFaction();
    }

    public static boolean isOwn(Location location, Player player) {
        return BoardColl.get().getFactionAt(PS.valueOf(location)).getId().equals(MPlayer.get(player).getFaction().getId());
    }

    public static List<String> getAllFactions() {
        List<String> factions = new ArrayList<>();

        FactionColl.get().getAll().forEach(faction -> factions.add(faction.getName()));

        return factions;
    }

    public static Faction getFactionByName(String name) {
        return FactionColl.get().getByName(name);
    }

    public static boolean isInFaction(UUID uuid, Object faction) {
        MPlayer mPlayer = MPlayer.get(uuid);
        if (mPlayer == null) return false;
        return ((Faction) faction).getMPlayers().contains(mPlayer);
    }

    public static String getRelationColor(UUID uuid, Object faction) {
        MPlayer mPlayer = MPlayer.get(uuid);
        if (mPlayer == null) return "";
        return "&" + mPlayer.getRelationTo((Faction) faction).getColor().getChar();
    }

}
