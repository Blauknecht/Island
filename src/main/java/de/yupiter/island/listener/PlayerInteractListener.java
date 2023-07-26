package de.yupiter.island.listener;

import de.kevloe.packets.types.Rank;
import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Rank rank = YupiterIsland.getInstance().getRankAPI().getPlayerRank(player);
        Island island = YupiterIsland.getInstance().getIslandManager().getIslandAtLocation(player.getLocation());
        if(island != null){
            if(!rank.isHigherEqualsLevel(Rank.DEVELOPER)){
                if(!island.isOnIsland(player)){
                    event.setCancelled(true);
                    return;
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
            }
        }
    }
}
