package de.plunamc.island.manager;

import de.plunaapi.PlunaAPI;
import de.plunaapi.scoreboard.PlayerScore;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.utils.Formatter;
import de.plunamc.island.utils.Serialers;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
    public void setPlayerData(PlayerData data){
        this.playerDataMap.put(data.getPlayer(), data);
    }


    public void setSpawnScoreboard(Player player, Island island){
        Rank rank = PlunaIsland.getInstance().getRankAPI().getPlayerRank(player);
        PlayerScore playerScore = PlayerScore.getScores().get(player);
        playerScore.createSidebar("§bᴘʟᴜɴᴀᴍᴄ.ᴅᴇ");
        playerScore.setScore("§9", 8);
        playerScore.setScore("§bʀᴀɴɢ", 7);
        playerScore.setScore("§7➥ "+rank.getUnicode(), 6);
        playerScore.setScore("§6", 5);
        playerScore.setScore("§fʙɪᴛꜱ", 4);
        playerScore.setScore("§7➥ §f"+ island.getStreams(), 3);
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
        playerScore.setScore("§bʟᴇᴠᴇʟ§7/§bᴇxᴘ", 4);
        playerScore.setScore("§7➥ §f"+ island.getLevel().toString()+"§7/§f"+island.getExp().toString()+" ᴇxᴘ", 3);
        playerScore.setScore("§9", 2);
        playerScore.setScore("§bᴅɪꜱᴄᴏʀᴅ", 1);
        playerScore.setScore("§7➥ §fᴅᴄ.ᴘʟᴜɴᴀᴍᴄ.ᴅᴇ", 0);
    }

    private void loadMoney() {
        CompletableFuture.runAsync(() -> {
            int moneys = 0;
            try {
                PreparedStatement statement = PlunaIsland.getInstance().getMysql().getConnection().prepareStatement("select * from islandsMoney");
                statement.execute();
                statement.closeOnCompletion();
                ResultSet set = statement.getResultSet();
                while (set.next()) {

                    OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(set.getString("Owner")));
                    int money = set.getInt("Money");

                   // PlayerData playerData = new PlayerData(owner.getName()).getMoney();


                }
                Bukkit.getLogger().info("Loaded " + moneys + " islands from database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
