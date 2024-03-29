package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
            Player damager = (Player) event.getDamager();
            Player player = (Player) event.getEntity();
            if(damager.getWorld().getName().equals("islands") && player.getWorld().getName().equals("islands")){
                event.setCancelled(true);
            }
        }
        if(event.getDamager() instanceof Player){
            Player damager = (Player) event.getDamager();
            if(damager.getWorld().getName().equals("islands")){
                Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(damager.getLocation());
                if(island != null){
                    if(!island.isOnIsland(damager)){
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
