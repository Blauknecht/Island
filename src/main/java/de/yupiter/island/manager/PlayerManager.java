package de.yupiter.island.manager;

import de.yupiter.island.island.Island;
import de.yupiterapi.YupiterAPI;
import de.yupiterapi.playerdata.RankAPI;
import de.yupiterapi.scoreboard.PlayerScore;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

public class PlayerManager {

    public void setSpawnScoreboard(Player player){
        RankAPI rankAPI = new RankAPI();
        PlayerScore playerScore = PlayerScore.getScores().get(player);
        playerScore.createSidebar("§bʏᴜᴘɪᴛᴇʀ.ɴᴇᴛ");
        playerScore.setScore("§9", 8);
        playerScore.setScore("§bʀᴀɴɢ", 7);
        playerScore.setScore("§7➥ "+rankAPI.getPlayerRank(player).getUnicode(), 6);
        playerScore.setScore("§6", 5);
        playerScore.setScore("§bꜱᴛʀᴇᴀᴍꜱ", 4);
        playerScore.setScore("§7➥ §f"+YupiterAPI.formatNumber(YupiterAPI.instance.getCoinsAPI().getCoins(player)), 3);
        playerScore.setScore("§a", 2);
        playerScore.setScore("§bᴅɪꜱᴄᴏʀᴅ", 1);
        playerScore.setScore("§7➥ §fʏᴜᴘɪᴛᴇʀ.ɴᴇᴛ", 0);

    }
    public void setInselScoreboard(Player player, Island island){
        PlayerScore playerScore = PlayerScore.getScores().get(player);
        playerScore.createSidebar("§bʏᴜᴘɪᴛᴇʀ.ɴᴇᴛ");
        playerScore.setScore("§9", 8);
        playerScore.setScore("§bɪɴꜱᴇʟ", 7);
        playerScore.setScore("§7➥ §f"+island.getOwner().getName(), 6);
        playerScore.setScore("§6", 5);
        playerScore.setScore("§bʟᴇᴠᴇʟ", 4);
        playerScore.setScore("§7➥ §f"+ island.getLevel().toString(), 3);
        playerScore.setScore("§9", 2);
        playerScore.setScore("§bᴅɪꜱᴄᴏʀᴅ", 1);
        playerScore.setScore("§7➥ §fʏᴜᴘɪᴛᴇʀ.ɴᴇᴛ", 0);
    }
}
