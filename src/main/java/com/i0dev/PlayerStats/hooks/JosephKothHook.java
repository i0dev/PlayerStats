package com.i0dev.PlayerStats.hooks;

import com.i0dev.PlayerStats.objects.StatTye;
import org.jdgames.koth.entity.MPlayer;
import org.jdgames.koth.entity.MPlayerColl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JosephKothHook {


    public static int getKothWins(UUID uuid) {
        return MPlayer.get(uuid).getKothWins();
    }

    public static List<UUID> getAllUUIDs() {
        List<UUID> ret = new ArrayList<>();
        for (MPlayer mPlayer : MPlayerColl.get().getAll()) {
            if (mPlayer == null || mPlayer.getUuid() == null) continue;
            ret.add(mPlayer.getUuid());
        }
        return ret;
    }


}
