package de.yupiter.island.listener;

import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
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
            Island moveIsland = YupiterIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
            if(island == null && moveIsland != null){
                YupiterIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                player.setMetadata("island", new FixedMetadataValue(YupiterIsland.getInstance(), moveIsland));
                return;
            }
            if(moveIsland == null){
                player.removeMetadata("island", YupiterIsland.getInstance());
                YupiterIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                return;
            }
            if(moveIsland != island){
                YupiterIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                player.setMetadata("island", new FixedMetadataValue(YupiterIsland.getInstance(), moveIsland));
            }
        }else{
            Island moveIsland = YupiterIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
            if(moveIsland != null){
                YupiterIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                player.setMetadata("island", new FixedMetadataValue(YupiterIsland.getInstance(), moveIsland));
            }
        }
    }
}
