package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event){
        Player player = (Player) event.getEntity();
        if (player.getLocation().getWorld().getName().equals("spawn")) {
            event.setCancelled(true);
        }
    }
}
