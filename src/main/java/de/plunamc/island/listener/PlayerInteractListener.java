package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import de.plunamc.island.spawner.CustomBlock;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Rank rank = PlunaIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
        if (PlunaIsland.getInstance().getBuild().contains(player)) return;
        if (island != null) {
                if (!island.isOnIsland(player)) {
                    event.setCancelled(true);
                    return;
            }
            if(island.isOnIsland(player)){
                if (player.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    CustomBlock customBlock = CustomBlock.getBlockByCustomModelData(Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getCustomModelData());
                    Block blocklock = event.getClickedBlock().getRelative(event.getBlockFace());
                    if (blocklock.isLiquid() || (blocklock.getType() == Material.AIR)) {
                        if (player.getCooldown(Material.PRISMARINE_SHARD) > 0) {
                            return;
                        }
                        if (player.getGameMode() == GameMode.SURVIVAL) {
                            int Amount = player.getInventory().getItemInMainHand().getAmount();
                            player.getInventory().getItemInMainHand().setAmount(Amount - 1);
                        }
                        blocklock.setType(Material.NOTE_BLOCK);
                        NoteBlock noteBlock = (NoteBlock) blocklock.getBlockData();
                        noteBlock.setInstrument(customBlock.getInstrument());
                        noteBlock.setNote(customBlock.getNote());
                        blocklock.setBlockData(noteBlock);
                        player.setCooldown(Material.PRISMARINE_SHARD, 5);
                        PlunaIsland.getInstance().getSpawnerManager().createSpawner(customBlock, blocklock.getLocation());
                    }
                }
            }
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.NOTE_BLOCK) {
            event.setCancelled(true);
            return;
        }
        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        if(player.getWorld().getName().equals("spawn")){
            if(PlunaIsland.getInstance().getBuild().contains(player)){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }
    }
}
