package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void on(BlockPlaceEvent event){
        Player player = event.getPlayer();
        Rank rank = PlunaIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(event.getBlock().getLocation());
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
