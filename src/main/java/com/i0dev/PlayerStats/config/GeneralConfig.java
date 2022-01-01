package com.i0dev.PlayerStats.config;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.objects.ConfigItem;
import com.i0dev.PlayerStats.objects.IndexableSkullConfigItem;
import com.i0dev.PlayerStats.templates.AbstractConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeneralConfig extends AbstractConfiguration {

    public GeneralConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }

    String statsInventoryTitle = "&8Statistics for &c{player}";

    int statsInventorySize = 45;

    IndexableSkullConfigItem stats_skull_cane_broken = new IndexableSkullConfigItem(
            "&a&lCane broken",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has broken &a{amount} &7sugarcane!"
            ),
            true,
            11,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWJlYzRhNGE3MWRjY2MwMDE2MTdjZWM4NjM4MDMyMWYyMzlmY2IwMWE3YWViNGJhYTU2Zjg0NzE4MzcxMzk4YiJ9fX0="
    );

    IndexableSkullConfigItem stats_skull_cane_placed = new IndexableSkullConfigItem(
            "&a&lCane placed",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has placed &a{amount} &7sugarcane!"
            ),
            true,
            10,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjhlNWEzYTg1ZjgyZjc3MmIxMGRkZGExZTIxNjU3NmZmMWI5YTk4MzQ0ZTA3NTNiNjhkZGUwNmY3ZjIxNTdmNCJ9fX0="

    );

    IndexableSkullConfigItem stats_skull_time_connected = new IndexableSkullConfigItem(
            "&e&lTime connected",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has played &a{amount}&7!"
            ),
            true,
            22,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjg2YjlkNThiY2QxYTU1NWY5M2U3ZDg2NTkxNTljZmQyNWI4ZGQ2ZTliY2UxZTk3MzgyMjgyNDI5MTg2MiJ9fX0="
    );

    IndexableSkullConfigItem stats_skull_koths_captured = new IndexableSkullConfigItem(
            "&c&lKoTHs captured",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has captured &a{amount} &7KoTHs!"
            ),
            true,
            4,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM0YTU5MmE3OTM5N2E4ZGYzOTk3YzQzMDkxNjk0ZmMyZmI3NmM4ODNhNzZjY2U4OWYwMjI3ZTVjOWYxZGZlIn19fQ=="
    );
    IndexableSkullConfigItem stats_skull_players_killed = new IndexableSkullConfigItem(
            "&c&lPlayers killed",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has killed &a{amount} &7players!"
            ),
            true,
            28,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODYzZmQ3ODIyMmQ1OTg4YzZiZTFjYzlmYTk2ZTg1Mjg5MTViYjY5NzQ2NDY0ZDIzOGY5MzZlYmViYjIzYzUyIn19fQ=="
    );
    IndexableSkullConfigItem stats_skull_mobs_killed = new IndexableSkullConfigItem(
            "&c&lMobs killed",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has killed &a{amount} &7mobs!"
            ),
            true,
            40,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNmYjRlNWRiOTdmNDc5YzY2YTQyYmJkOGE3ZDc4MWRhZjIwMWE4ZGRhZjc3YWZjZjRhZWY4Nzc3OWFhOGI0In19fQ=="
    );

    IndexableSkullConfigItem stats_skull_times_died = new IndexableSkullConfigItem(
            "&c&lTimes died",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has died &a{amount} &7times!"
            ),
            true,
            29,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTY2NmYzYzQzZmRjZDAxNTgzOTI3NTIxMTE0ZmI2OGRjYThmYjZjNmNjZTUxNGJlNTNkNDdiNTAwNjM3NjFjNSJ9fX0="
    );

    IndexableSkullConfigItem stats_skull_fish_caught = new IndexableSkullConfigItem(
            "&b&lFish caught",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has caught &a{amount} &7fish!"
            ),
            true,
            34,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjZDcxZmJiYmJiNjZjN2JhZjc4ODFmNDE1YzY0ZmE4NGY2NTA0OTU4YTU3Y2NkYjg1ODkyNTI2NDdlYSJ9fX0="
    );


    IndexableSkullConfigItem stats_skull_blocks_broken = new IndexableSkullConfigItem(
            "&8&lBlocks Broken",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has broken &a{amount} &7blocks!"
            ),
            true,
            16,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGMxNzU0ODUxZTM2N2U4YmViYTJhNmQ4ZjdjMmZlZGU4N2FlNzkzYWM1NDZiMGYyOTlkNjczMjE1YjI5MyJ9fX0="
    );
    IndexableSkullConfigItem stats_skull_blocks_placed = new IndexableSkullConfigItem(
            "&8&lBlocks placed",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has placed &a{amount} &7blocks!"
            ),
            true,
            15,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU2NDZkODc4NTkxYzU4YzM5ZjJkN2ExZGFmMDFlMTE0NDU0NGQ0NjU5NzdlYmE1YzNmZTc0M2I5ZmNmMTQwIn19fQ=="
    );

    IndexableSkullConfigItem stats_skull_bats_killed = new IndexableSkullConfigItem(
            "&f&lBats killed",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has killed &a{amount} &7bats!"
            ),
            true,
            33,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWU5OWRlZWY5MTlkYjY2YWMyYmQyOGQ2MzAyNzU2Y2NkNTdjN2Y4YjEyYjlkY2E4ZjQxYzNlMGEwNGFjMWNjIn19fQ=="
    );

    IndexableSkullConfigItem stats_skull_map_points = new IndexableSkullConfigItem(
            "&6&lMap Points",
            1,
            (short) 0,
            "SKULL_ITEM",
            Arrays.asList(
                    "",
                    "&c{player} &7has &a{amount} &7map points!"
            ),
            true,
            8,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmMxZTczMDIzMzUyY2JjNzdiODk2ZmU3ZWEyNDJiNDMxNDNlMDEzYmVjNWJmMzE0ZDQxZTVmMjY1NDhmYjJkMiJ9fX0="
    );


    ConfigItem stats_item_border = new ConfigItem(
            "&f",
            1,
            (short) 15,
            "STAINED_GLASS_PANE",
            new ArrayList<>(),
            true
    );

}
