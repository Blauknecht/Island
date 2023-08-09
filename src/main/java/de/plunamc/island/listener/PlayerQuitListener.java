package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent event){
        Player player = event.getPlayer();
        PlunaIsland.getInstance().getPlayerManager().removePlayerData(player);
        event.setQuitMessage(PlunaIsland.getInstance().getPrefix()+"§c« §7" + event.getPlayer().getName());
    }
}
