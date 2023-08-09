package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
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

public class BlockBreakListener implements Listener {

    private static final BlockFace[] FACES = new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN};

    private BlockFace[] blockFaces;

    @EventHandler
    public void on(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location blockloc = event.getBlock().getLocation();
        Rank rank = PlunaIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(event.getBlock().getLocation());
        if (rank.isLowerLevel(Rank.DEVELOPER)) {
            if (island != null) {
                if (!island.isOnIsland(player)) {
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
            } else {
                if (player.getWorld().getName().equals("islands")) {
                    event.setCancelled(true);
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
            return;
        }
        //Netherrite Generator
        if (event.getNewState().getType() == Material.BASALT) {
            event.getNewState().setType(island.getBasaltStoneRandomBlock());
            return;
        }
    }
}


