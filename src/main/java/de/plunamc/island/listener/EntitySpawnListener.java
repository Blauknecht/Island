package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void on(EntitySpawnEvent event){
        if(event.getLocation().getWorld().getName().equals("islands")){
            if(event.getLocation().distance(PlunaIsland.getInstance().getIslandManager().getSpawn()) < 200){
                event.setCancelled(true);
            }
        }
    }
}
