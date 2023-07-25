package de.yupiter.island.listener;

import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoinListener implements Listener {

    public void on(PlayerJoinEvent event){
        Player player = event.getPlayer();
        List<Island> islands = YupiterIsland.getInstance().getIslandManager().getInvitedIslands(player);
        if(!islands.isEmpty()){
            StringBuilder builder = new StringBuilder();
            int i = 1;
            for (Island island : islands) {
                if(i == islands.size()){
                    builder.append("§b").append(island.getOwner().getName());
                }else{
                    builder.append("§b").append(island.getOwner().getName()).append("§7, ");
                }
                ++i;
            }
            player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du wurdest auf die Insel eingeladen von: \n"+builder.toString());
        }
    }
}
