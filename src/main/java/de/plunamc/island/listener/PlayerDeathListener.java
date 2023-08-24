package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.getEntity().spigot().respawn();
        if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(event.getEntity()).getMoney() > 3000) {
            //   PlunaIsland.getInstance().getPlayerManager().getPlayerData(event.getEntity()).removeMoney(3000);
            // TODO
        }
        event.setKeepInventory(true);
    }

    @EventHandler
    public void onRespawnEvent(PlayerRespawnEvent event) {
        Bukkit.getScheduler().runTaskLater(PlunaIsland.getInstance(), new Runnable() {
            @Override
            public void run() {
                event.getPlayer().sendTitle("§cDu bist gestorben!", "", 10, 50, 20);
                event.getPlayer().teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
            }
        }, 20 * 2);
    }
}
