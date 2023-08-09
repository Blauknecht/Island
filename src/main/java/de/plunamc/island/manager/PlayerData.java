package de.plunamc.island.manager;

import de.plunaapi.PlunaAPI;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.market.*;
import de.plunamc.island.utils.Itemmanager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;

public class PlayerData {

    @Getter
    private Player player;
    @Getter
    private Inventory bennibaumeister;
    @Getter
    private Inventory gretageartner;
    @Getter
    private Inventory hildaholle;
    @Getter
    private Inventory volkerverkauf;
    @Getter
    private Inventory zyruszuchter;
    @Setter
    @Getter
    private int money;


    public PlayerData(Player player) {
        this.player = player;

        this.bennibaumeister = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE044");
        this.gretageartner = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() +"\uE045");
        this.hildaholle = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE047");
        this.volkerverkauf = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE048");
        this.zyruszuchter = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE046");

        this.loadBenniBaumeister();
        this.loadGretaGartner();
        this.loadVolkerVerkauf();
        this.loadZyrusZuchter();
        this.loadHildaHolle();
    }

    //00 01 02 03 04 05 06 07 08
    //09 10 11 12 13 14 15 16 17
    //18 19 20 21 22 23 24 25 26
    //27 28 29 30 31 32 33 34 35
    //36 37 38 39 40 41 42 43 44
    //45 46 47 48 49 50 51 52 53

    public void loadGretaGartner() {
        int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
        int i = 0;
        for(GretaGartner gretaGartner : GretaGartner.values()){
            if (i >= slots.length) {
                break;
            }
            this.gretageartner.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(gretaGartner.getMaterial(), "§b", Arrays.asList("", "§f\uE041 §d"+gretaGartner.getPrice())));
            i++;
        }
    }
    public void loadHildaHolle() {
        int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
        int i = 0;
        for(HildaHolle hildaholle : HildaHolle.values()){
            if (i >= slots.length) {
                break;
            }
            this.hildaholle.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(hildaholle.getMaterial(), "§b", Arrays.asList("", "§f\uE041 §d"+hildaholle.getPrice())));
            i++;
        }
    }
    public void loadVolkerVerkauf() {
        int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
        int i = 0;
        for(VolkerVerkauf volkerVerkauf : VolkerVerkauf.values()){
            if (i >= slots.length) {
                break;
            }
            this.volkerverkauf.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(volkerVerkauf.getMaterial(), "§b", Arrays.asList("", "§f\uE041 §d"+volkerVerkauf.getSellPrice())));
            i++;
        }
    }
    public void loadZyrusZuchter() {
        int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
        int i = 0;
        for(ZyrusZuchter zyruszuchter : ZyrusZuchter.values()){
            if (i >= slots.length) {
                break;
            }
            this.zyruszuchter.setItem(slots[i],Itemmanager.createMarketItemWithLoreColoredName(zyruszuchter.getMaterial(), "§b", Arrays.asList("", "§f\uE041 §d"+zyruszuchter.getPrice())));
            i++;
        }
    }
    public void loadBenniBaumeister() {
        int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
        int i = 0;
        for(BenniBaumeister bennibaumeister : BenniBaumeister.values()){
            if (i >= slots.length) {
                break;
            }
            this.bennibaumeister.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(bennibaumeister.getMaterial(), "§b", Arrays.asList("", "§f\uE041 §d"+bennibaumeister.getPrice())));
            i++;
        }
    }

}
