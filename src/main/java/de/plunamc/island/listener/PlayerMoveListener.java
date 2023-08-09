package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMoveIsland(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(player.hasMetadata("island")){
            Island island = (Island) player.getMetadata("island").get(0).value();
            Island moveIsland = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
            Island getIslandOwner = PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId());
            if(island == null && moveIsland != null){
                PlunaIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                player.setMetadata("island", new FixedMetadataValue(PlunaIsland.getInstance(), moveIsland));
                return;
            }
            if(moveIsland == null){
                player.removeMetadata("island", PlunaIsland.getInstance());
                PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player, getIslandOwner);
                return;
            }
            if(moveIsland != island){
                PlunaIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                player.setMetadata("island", new FixedMetadataValue(PlunaIsland.getInstance(), moveIsland));
            }
        }else{
            Island moveIsland = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
            if(moveIsland != null){
                PlunaIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                player.setMetadata("island", new FixedMetadataValue(PlunaIsland.getInstance(), moveIsland));
            }
        }
    }
}
