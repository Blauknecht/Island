package de.yupiter.island.listener;

import de.kevloe.packets.types.Rank;
import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import de.yupiterapi.YupiterAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {

    @EventHandler
    public void on(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        Rank rank = YupiterIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = YupiterIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
        if(!rank.isHigherEqualsLevel(Rank.DEVELOPER)){
            if(island != null && !island.isOnIsland(player)){
                event.setCancelled(true);
            }
        }
    }
}