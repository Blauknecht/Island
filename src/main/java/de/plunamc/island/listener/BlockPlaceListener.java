package de.plunamc.island.listener;

import de.plunaapi.scoreboard.PlayerScore;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.files.BlockFile;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Map;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void on(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Rank rank = PlunaIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(event.getBlock().getLocation());
        if(player.getWorld().getName().equals("spawn")){
            if(PlunaIsland.getInstance().getBuild().contains(player)){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }
        if(island != null){
            if(!island.isOnIsland(player)){
                if(PlunaIsland.getInstance().getBuild().contains(player))return;
                event.setCancelled(true);
            }
            for(Map.Entry<Material, Integer> entry : BlockFile.blockExpMap.entrySet()){
                Material material = entry.getKey();
                int points = entry.getValue();
                if(event.getBlock().getType() == material){
                    island.addExpBlock(1);
                    PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId()).addExp(points);
                    island.updateBossbarProgress(player);
                    PlayerScore playerScore = PlayerScore.getScores().get(player);
                    playerScore.setScore("§7➥ §f" + island.getLevel().toString(), 3);
                }
            }
        }else{
            if(player.getWorld().getName().equals("islands")){
                if (PlunaIsland.getInstance().getBuild().contains(player)) return;
                event.setCancelled(true);
            }
        }
    }
}
