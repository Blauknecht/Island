package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void on(PlayerDeathEvent event){
        event.setDeathMessage(null);
        event.getEntity().spigot().respawn();
        if(PlunaIsland.getInstance().getPlayerManager().getPlayerData(event.getEntity()).getMoney() > 0){
            PlunaIsland.getInstance().getPlayerManager().getPlayerData(event.getEntity()).removeMoney(3000);
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(PlunaIsland.getInstance(), new Runnable() {
            @Override
            public void run() {
                event.getEntity().teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
            }
        },2,2);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        event.getPlayer().spigot().respawn();
        Bukkit.getScheduler().runTaskLater(PlunaIsland.getInstance(), new Runnable() {
            @Override
            public void run() {
                event.getPlayer().teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
            }
        },5);
    }
}
