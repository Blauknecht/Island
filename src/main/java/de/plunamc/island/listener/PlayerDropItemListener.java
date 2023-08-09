package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void on(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        Rank rank = PlunaIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
        if(!rank.isHigherEqualsLevel(Rank.DEVELOPER)){
            if(island != null && !island.isOnIsland(player)){
                event.setCancelled(true);
            }
        }
    }
}
