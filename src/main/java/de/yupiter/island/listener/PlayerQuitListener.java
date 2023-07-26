package de.yupiter.island.listener;

import de.yupiter.island.YupiterIsland;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent event){
        event.setQuitMessage(YupiterIsland.getInstance().getPrefix()+"§c« §7" + event.getPlayer().getName());
    }
}
