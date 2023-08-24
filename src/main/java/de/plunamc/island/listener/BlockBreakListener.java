package de.plunamc.island.listener;

import de.plunaapi.scoreboard.PlayerScore;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.files.BlockFile;
import de.plunamc.island.island.Island;
import de.plunamc.island.spawner.Spawner;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlockBreakListener implements Listener {

    private static final BlockFace[] FACES = new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN};

    public static List<Location> cobblegen = new ArrayList<>();
    public static List<Location> nethergen = new ArrayList<>();
    private BlockFace[] blockFaces;

    @EventHandler
    public void on(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location blockloc = event.getBlock().getLocation();
        Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(blockloc);
        if (player.getWorld().getName().equals("spawn")) {
            if (PlunaIsland.getInstance().getBuild().contains(player)) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
            }
        }
        if (island != null) {
            if (!island.isOnIsland(player)) {
                if (PlunaIsland.getInstance().getBuild().contains(player)) return;
                event.setCancelled(true);
            }
            ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
            if (tool.getType().toString().contains("PICKAXE")) {
                if (tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                    if (event.getBlock().getType() == Material.SPAWNER) {
                        event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SPAWNER));
                    }
                }
            }
            if (island.isOnIsland(player)) {
                if (event.getBlock().getType() == Material.NOTE_BLOCK) {
                    PlunaIsland.getInstance().getSpawnerManager().getSpawners().forEach(spawner -> {
                        if (spawner.getSpawnercoords().equals(event.getBlock().getLocation())) {
                            spawner.delete();
                        }
                    });
                }
            }
            if (island.getExpblocks() > 0) {
                for (Map.Entry<Material, Integer> entry : BlockFile.blockExpMap.entrySet()) {
                    Material material = entry.getKey();
                    int points = entry.getValue();
                    if (event.getBlock().getType() == material) {
                        if (!cobblegen.contains(event.getBlock().getLocation()) && !nethergen.contains(event.getBlock().getLocation())) {
                            island.removeExpBlock(1);
                            PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId()).removeExp(points);
                            island.updateBossbarProgress(player);
                            PlayerScore playerScore = PlayerScore.getScores().get(player);
                            playerScore.setScore("§7➥ §f" + island.getLevel().toString(), 3);
                        }
                        cobblegen.remove(event.getBlock().getLocation());
                        nethergen.remove(event.getBlock().getLocation());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBreakCobbleStonByGen(BlockFormEvent event) {
        Block block = event.getBlock();
        Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(block.getLocation());
        if (!block.isLiquid()) return;
        if (island == null) return;

        //Cobblestone Generator
        if (event.getNewState().getType() == Material.COBBLESTONE) {
            event.getNewState().setType(island.getCobbleStoneRandomBlock());
            cobblegen.add(event.getNewState().getBlock().getLocation());
            return;
        }
        //Netherrite Generator
        if (event.getNewState().getType() == Material.BASALT) {
            event.getNewState().setType(island.getBasaltStoneRandomBlock());
            nethergen.add(event.getNewState().getBlock().getLocation());
            return;
        }
    }
}


