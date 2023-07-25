package de.yupiter.island.listener;

import de.kevloe.packets.types.Rank;
import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Rank rank = YupiterIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = YupiterIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
        if(island != null){
            if(!rank.isHigherEqualsLevel(Rank.DEVELOPER)){
                if(!island.isOnIsland(player)){
                    event.setCancelled(true);
                }
            }
        }
    }
}
