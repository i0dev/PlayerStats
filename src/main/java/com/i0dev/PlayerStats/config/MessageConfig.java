package com.i0dev.PlayerStats.config;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.templates.AbstractConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageConfig extends AbstractConfiguration {


    String helpPageHeader = "&7&m_______&r&8[&r &c&lPlayerStats &8]&m_______";
    String reloadUsage = " &c* &7/PlayerStats reload";
    String helpUsage = " &c* &7/PlayerStats help";
    String statsUsage = " &c* &7/Stats [player]";
    String leaderboardUsage = " &c* &7/Leaderboard <stat>";

    String leaderboardHeader = "&8________&r&8[&r &7{stat} &c&lLeaderboard &8]________";
    String leaderboardFormat = "{rank} &4{player} &7- &c{stat}";
    String yourPosition = "&7Your position is &f{rank}&7 with a value of &c{stat}&7.";

    String openedStats = "&7Now showing statistics for &c{player}&7.";
    String forceSaved = "&aForce saved data successfully!";
    String cantFindStatType = " &cCan't find that stat type.";
    String reloadedConfig = "&7You have&a reloaded&7 the configuration.";
    String noPermission = "&cYou don not have permission to run that command.";
    String cantFindPlayer = "&cThe player: &f{player}&c cannot be found!";
    String invalidNumber = "&cThe number &f{num} &cis invalid! Try again.";
    String cantRunAsConsole = "&cYou cannot run this command from console.";

    public MessageConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }
}
