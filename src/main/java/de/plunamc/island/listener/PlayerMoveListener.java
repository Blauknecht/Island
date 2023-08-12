package de.plunamc.island.listener;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.island.Island;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMoveIsland(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("spawn")) {
            Location playerLocation = event.getPlayer().getLocation();
            Block blockBelow = playerLocation.getBlock().getRelative(0, -1, 0);
            if (blockBelow.getType() == Material.END_PORTAL) {
                CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class).getPlayerExecutor(player.getUniqueId()).connect("skypvp-1");
            }
            if (player.getLocation().getY() < 80) {
                player.teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Hey! Was machst du da?");
            }
            if(player.hasMetadata("island")){
                player.removeMetadata("island", PlunaIsland.getInstance());
            }
            if(!player.hasMetadata("spawn")){
                player.setMetadata("spawn", new FixedMetadataValue(PlunaIsland.getInstance(), "spawn"));
                PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
            }
        } else if (player.getWorld().getName().equals("islands")) {
            if(player.hasMetadata("spawn")){
                player.removeMetadata("spawn", PlunaIsland.getInstance());
            }
            if (player.hasMetadata("island")) {
                Island island = (Island) player.getMetadata("island").get(0).value();
                Island moveIsland = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
                if (moveIsland != island) {
                    PlunaIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                    player.setMetadata("island", new FixedMetadataValue(PlunaIsland.getInstance(), moveIsland));
                }
            } else {
                Island moveIsland = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
                if (moveIsland != null) {
                    PlunaIsland.getInstance().getPlayerManager().setInselScoreboard(player, moveIsland);
                    player.setMetadata("island", new FixedMetadataValue(PlunaIsland.getInstance(), moveIsland));
                }
            }
        }

    }
}