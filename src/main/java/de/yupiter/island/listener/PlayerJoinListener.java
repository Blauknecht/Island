package de.yupiter.island.listener;

import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(YupiterIsland.getInstance().getPrefix()+"§a» §7" + player.getName());
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
        player.setMetadata("island", new FixedMetadataValue(YupiterIsland.getInstance(), null));

            Bukkit.getScheduler().runTaskLater(YupiterIsland.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if(!player.hasPlayedBefore()) {
                        player.teleport(YupiterIsland.getInstance().getIslandManager().getSpawn());
                    }
                    Island island = YupiterIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
                    if(island != null){
                        player.setWorldBorder(island.getBorder());
                    }
                }
            }, 15L);
    }
}
