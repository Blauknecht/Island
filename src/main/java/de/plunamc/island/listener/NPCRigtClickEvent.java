package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.manager.PlayerData;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCRigtClickEvent implements Listener {

    @EventHandler
    public void onNPCClick(NPCRightClickEvent event) {
        Player player = event.getClicker();
        //Zyrus Züchter
        if (event.getNPC().getId() == 0) {
            player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getZyruszuchter());
        }
        //Greta Gärtner
        if (event.getNPC().getId() == 1) {
            player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getGretageartner());
        }
        //Hilda Hölle
        if (event.getNPC().getId() == 2) {
            player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getHildaholle());
        }
        //Benni Bauarbeiter
        if (event.getNPC().getId() == 3) {
            player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getBennibaumeister());
        }
        //Volker Verkauf
        if (event.getNPC().getId() == 4) {
            player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverkauf());
        }
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
    }
}
