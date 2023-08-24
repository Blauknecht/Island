package de.plunamc.island.manager;

import de.plunaapi.scoreboard.PlayerScore;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.spawner.Spawner;
import de.plunamc.island.utils.Formatter;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class PlayerManager {


    private Map<Player, PlayerData> playerDataMap;

    public PlayerManager(){
        this.playerDataMap = new HashMap<>();
    }

    public void addPlayerData(Player player){
        if(!this.playerDataMap.containsKey(player)){
            this.playerDataMap.put(player, new PlayerData(player));
        }
    }
    public void removePlayerData(Player player){
        if(this.playerDataMap.containsKey(player)){
            this.playerDataMap.remove(player);
        }
    }
    public PlayerData getPlayerData(Player player){
        if(this.playerDataMap.containsKey(player)){
            return this.playerDataMap.get(player);
        }
        return null;
    }

    public void setSpawnScoreboard(Player player){
        Rank rank = PlunaIsland.getInstance().getRankAPI().getPlayerRank(player);
        PlayerScore playerScore = PlayerScore.getScores().get(player);
        playerScore.createSidebar("§bᴘʟᴜɴᴀᴍᴄ.ᴅᴇ");
        playerScore.setScore("§9", 8);
        playerScore.setScore("§bʀᴀɴɢ", 7);
        playerScore.setScore("§7➥ "+rank.getUnicode(), 6);
        playerScore.setScore("§6", 5);
        playerScore.setScore("§bʙɪᴛꜱ", 4);
        playerScore.setScore("§7➥ §f"+ this.getPlayerData(player).getMoney() +" §f\uE041", 3);
        playerScore.setScore("§a", 2);
        playerScore.setScore("§bᴅɪꜱᴄᴏʀᴅ", 1);
        playerScore.setScore("§7➥ §fᴅᴄ.ᴘʟᴜɴᴀᴍᴄ.ᴅᴇ", 0);

    }
    public void setInselScoreboard(Player player, Island island){
        PlayerScore playerScore = PlayerScore.getScores().get(player);
        playerScore.createSidebar("§bᴘʟᴜɴᴀᴍᴄ.ᴅᴇ");
        playerScore.setScore("§9", 8);
        playerScore.setScore("§bɪɴꜱᴇʟ", 7);
        playerScore.setScore("§7➥ §f"+ Formatter.smallCapsFormatter(island.getOwner().getName()), 6);
        playerScore.setScore("§6", 5);
        playerScore.setScore("§bʟᴇᴠᴇʟ§7", 4);
        playerScore.setScore("§7➥ §f"+ island.getLevel().toString(), 3);
        playerScore.setScore("§9", 2);
        playerScore.setScore("§bᴅɪꜱᴄᴏʀᴅ", 1);
        playerScore.setScore("§7➥ §fᴅᴄ.ᴘʟᴜɴᴀᴍᴄ.ᴅᴇ", 0);
    }
}
