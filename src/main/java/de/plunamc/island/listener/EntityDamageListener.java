package de.plunamc.island.listener;


import de.plunamc.island.PlunaIsland;
import de.plunamc.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamge(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
            if(player.getWorld().getName().equals("spawn")){
                event.setCancelled(true);
            }

        }
    }

}
