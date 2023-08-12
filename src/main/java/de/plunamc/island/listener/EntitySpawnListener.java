package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void on(EntitySpawnEvent event){
        if(event.getLocation().getWorld().getName().equals("islands")){
            if(event.getEntity().getWorld().getName().equals("spawn")){
                if(event.getEntityType() == EntityType.DROPPED_ITEM)return;
                if(event.getEntityType() == EntityType.PLAYER)return;
                event.setCancelled(true);
            }
        }
    }
}
