package de.yupiter.island.listener;

import de.kevloe.packets.types.Rank;
import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void on(BlockBreakEvent event){
        Player player = event.getPlayer();
        Rank rank = YupiterIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = YupiterIsland.getInstance().getIslandManager().getIslandAtLocation(event.getBlock().getLocation());
        if(rank.isLowerLevel(Rank.DEVELOPER)){
            if(island != null){
                if(!island.isOnIsland(player)){
                    event.setCancelled(true);
                }
            }else{
                if(player.getWorld().getName().equals("islands")){
                    event.setCancelled(true);
                }
            }
        }
    }
}
