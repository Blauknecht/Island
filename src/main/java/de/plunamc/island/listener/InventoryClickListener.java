package de.plunamc.island.listener;

import de.plunamc.island.PlunaIsland;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        ItemStack clickeditem = event.getCurrentItem();
        //Benni Baumeister
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE044")) {
            event.setCancelled(true);
        }
        //Greta Gärtner
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE045")) {
            event.setCancelled(true);
        }
        //ZyrusZüchter
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE046")) {
            event.setCancelled(true);
        }
        //HildeHolle
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE047")) {
            event.setCancelled(true);
        }
        //VolkerVerkauf
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE048")) {
            event.setCancelled(true);
        }
    }
}
