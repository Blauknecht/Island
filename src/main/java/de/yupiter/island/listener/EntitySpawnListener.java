package de.yupiter.island.listener;

import de.yupiter.island.YupiterIsland;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void on(EntitySpawnEvent event){
        if(event.getLocation().getWorld().getName().equals("islands")){
            if(event.getLocation().distance(YupiterIsland.getInstance().getIslandManager().getSpawn()) < 200){
                event.setCancelled(true);
            }
        }
    }
}
