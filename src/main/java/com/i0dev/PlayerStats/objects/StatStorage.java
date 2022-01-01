package com.i0dev.PlayerStats.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatStorage {

    String uuid;

    long cane_broken = 0,
            cane_placed = 0,
            time_connected = 0,
            koths_captured = 0,
            players_killed = 0,
            mobs_killed = 0,
            times_died = 0,
            fish_caught = 0,
            blocks_broken = 0,
            blocks_placed = 0,
            bats_killed = 0,
            map_points = 0;
}
