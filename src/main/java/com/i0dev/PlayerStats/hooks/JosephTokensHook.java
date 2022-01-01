package com.i0dev.PlayerStats.hooks;


import gg.halcyon.tokens.entity.MPlayer;
import gg.halcyon.tokens.entity.MPlayerColl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JosephTokensHook {


    public static long getCaneBroken(UUID uuid) {
        return MPlayer.get(uuid).getCaneBroken();
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
