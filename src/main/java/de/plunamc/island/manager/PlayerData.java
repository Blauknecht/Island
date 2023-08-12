package de.plunamc.island.manager;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.market.*;
import de.plunamc.island.utils.Formatter;
import de.plunamc.island.utils.Itemmanager;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

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
    private Inventory volkerverlistone;
    @Getter
    private Inventory volkerverlisttwo;
    @Getter
    private Inventory volkerverlistthree;
    @Getter
    private Inventory volkerverlistfour;
    @Getter
    private Inventory volkerverlistfive;
    @Getter
    private Inventory zyruszuchter;
    @Setter
    @Getter
    private int money = 0;


    public PlayerData(Player player) {
        this.player = player;

        this.loadMoney();

        this.bennibaumeister = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE044");
        this.gretageartner = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE045");
        this.hildaholle = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE047");
        this.zyruszuchter = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE046");
        this.volkerverkauf = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE048");
        this.volkerverlistone = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE049");
        this.volkerverlisttwo = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE050");
        this.volkerverlistthree = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE051");
        this.volkerverlistfour = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE052");
        this.volkerverlistfive = Bukkit.createInventory(null, 9 * 6, PlunaIsland.getInstance().getInventoryTitle() + "\uE053");


        this.loadBenniBaumeister();
        this.loadGretaGartner();
        this.loadZyrusZuchter();
        this.loadHildaHolle();
        this.loadVolkerVerkauf();
        this.loadVolkerVerkauflistone();
        this.loadVolkerVerkauflisttwo();
        this.loadVolkerVerkauflisthree();
        this.loadVolkerVerkauflisfour();
        this.loadVolkerVerkauflisfive();
    }

    //00 01 02 03 04 05 06 07 08
    //09 10 11 12 13 14 15 16 17
    //18 19 20 21 22 23 24 25 26
    //27 28 29 30 31 32 33 34 35
    //36 37 38 39 40 41 42 43 44
    //45 46 47 48 49 50 51 52 53

    public void addMoney(int money) {
        this.money += money;
        this.saveMoney(false);
    }

    public void removeMoney(int money) {
        this.money -= money;
        this.saveMoney(false);
    }

    public void loadGretaGartner() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;
        for (GretaGartner gretaGartner : GretaGartner.values()) {
            if (i >= slots.length) {
                break;
            }
            this.gretageartner.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(gretaGartner.getMaterial(), "§b", Arrays.asList("", Formatter.smallCapsFormatter("§7Kaufpreis: ") + "§d" + gretaGartner.getPrice() + "§f\uE041", "§f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen ") + "1x", "§e" + Formatter.smallCapsFormatter("Shift") + " §7+ §f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen") + " 64x §7[§d" + gretaGartner.getPrice() * 64 + " §f\uE041§7]", "§e" + Formatter.smallCapsFormatter("Drop") + "§7: §b" + Formatter.smallCapsFormatter("Kaufen") + "§7(" + Formatter.smallCapsFormatter("eigene Menge") + ")")));
            i++;
        }
    }

    public void loadHildaHolle() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;
        for (HildaHolle hildaholle : HildaHolle.values()) {
            if (i >= slots.length) {
                break;
            }
            this.hildaholle.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(hildaholle.getMaterial(), "§b", Arrays.asList("", Formatter.smallCapsFormatter("§7Kaufpreis: ") + "§d" + hildaholle.getPrice() + "§f\uE041", "§f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen ") + "1x", "§e" + Formatter.smallCapsFormatter("Shift") + " §7+ §f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen") + " 64x §7[§d" + hildaholle.getPrice() * 64 + " §f\uE041§7]", "§e" + Formatter.smallCapsFormatter("Drop") + "§7: §b" + Formatter.smallCapsFormatter("Kaufen") + "§7(" + Formatter.smallCapsFormatter("eigene Menge") + ")")));
            i++;
        }
    }

    public void loadVolkerVerkauf() {
        this.volkerverkauf.setItem(52, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§a"+Formatter.smallCapsFormatter("Verkaufen"), 4));
        this.volkerverkauf.setItem(53, Itemmanager.createItem(Material.PAPER, 1, "§b"+Formatter.smallCapsFormatter("Verkaufspreise")));
    }

    public void loadVolkerVerkauflistone(){
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;
        for (VolkerVerkauf volkerVerkauf : VolkerVerkauf.values()) {
            if (i >= slots.length) {
                break;
            }
            this.volkerverlistone.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(volkerVerkauf.getMaterial(),
                    "§b", Arrays.asList("","§7"+Formatter.smallCapsFormatter("Verkaufspreis: ")+"§d"+ volkerVerkauf.getSellprice()+" §f\uE041")));
            i++;
        }
        this.volkerverlistone.setItem(53, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Naechste Seite"), 3));
    }
    public void loadVolkerVerkauflisttwo() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;

        for (VolkerVerkauf volkerVerkauf : Arrays.stream(VolkerVerkauf.values()).skip(26).toList()) {
            if (i >= slots.length) {
                break;
            }
            this.volkerverlisttwo.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(volkerVerkauf.getMaterial(),
                    "", Arrays.asList("","§7"+Formatter.smallCapsFormatter("Verkaufspreis: ")+"§d"+ volkerVerkauf.getSellprice()+" §f\uE041")));
            i++;
        }
        this.volkerverlisttwo.setItem(53, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Naechste Seite"), 3));
        this.volkerverlisttwo.setItem(45, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Voherige Seite"), 2));
    }

    public void loadVolkerVerkauflisthree() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;

        for (VolkerVerkauf volkerVerkauf : Arrays.stream(VolkerVerkauf.values()).skip(52).toList()) {
            if (i >= slots.length) {
                break;
            }
                this.volkerverlistthree.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(volkerVerkauf.getMaterial(), "§b",
                        Arrays.asList("","§7"+Formatter.smallCapsFormatter("Verkaufspreis: ")+"§d"+ volkerVerkauf.getSellprice()+" §f\uE041")));
                i++;
        }
        this.volkerverlistthree.setItem(53, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Naechste Seite"), 3));
        this.volkerverlistthree.setItem(45, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Voherige Seite"), 2));
    }
    public void loadVolkerVerkauflisfour() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;

        for (VolkerVerkauf volkerVerkauf : Arrays.stream(VolkerVerkauf.values()).skip(78).toList()) {
            if (i >= slots.length) {
                break;
            }
            this.volkerverlistfour.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(volkerVerkauf.getMaterial(), "§b",
                    Arrays.asList("","§7"+Formatter.smallCapsFormatter("Verkaufspreis: ")+"§d"+ volkerVerkauf.getSellprice()+" §f\uE041")));
            i++;
        }
        this.volkerverlistfour.setItem(53, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Naechste Seite"), 3));
        this.volkerverlistfour.setItem(45, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Voherige Seite"), 2));
    }
    public void loadVolkerVerkauflisfive() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;

        for (VolkerVerkauf volkerVerkauf : Arrays.stream(VolkerVerkauf.values()).skip(104).toList()) {
            if (i >= slots.length) {
                break;
            }
            this.volkerverlistfive.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(volkerVerkauf.getMaterial(), "§b",
                    Arrays.asList("","§7"+Formatter.smallCapsFormatter("Verkaufspreis: ")+"§d"+ volkerVerkauf.getSellprice()+" §f\uE041")));
            i++;
        }
        this.volkerverlistfive.setItem(45, Itemmanager.createItemWithModelData(Material.POPPED_CHORUS_FRUIT, 1, "§b"+Formatter.smallCapsFormatter("Voherige Seite"), 2));
    }

    public void loadZyrusZuchter() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;
        for (ZyrusZuchter zyruszuchter : ZyrusZuchter.values()) {
            if (i >= slots.length) {
                break;
            }
            this.zyruszuchter.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(zyruszuchter.getMaterial(), "§b", Arrays.asList("", Formatter.smallCapsFormatter("§7Kaufpreis: ") + "§d" + zyruszuchter.getPrice() + "§f\uE041", "§f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen ") + "1x", "§e" + Formatter.smallCapsFormatter("Shift") + " §7+ §f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen") + " 64x §7[§d" + zyruszuchter.getPrice() * 64 + " §f\uE041§7]", "§e" + Formatter.smallCapsFormatter("Drop") + "§7: §b" + Formatter.smallCapsFormatter("Kaufen") + "§7(" + Formatter.smallCapsFormatter("eigene Menge") + ")")));
            i++;
        }
    }

    public void loadBenniBaumeister() {
        int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42};
        int i = 0;
        for (BenniBaumeister bennibaumeister : BenniBaumeister.values()) {
            if (i >= slots.length) {
                break;
            }
            this.bennibaumeister.setItem(slots[i], Itemmanager.createMarketItemWithLoreColoredName(bennibaumeister.getMaterial(), "§b", Arrays.asList("", Formatter.smallCapsFormatter("§7Kaufpreis: ") + "§d" + bennibaumeister.getPrice() + "§f\uE041", "§f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen ") + "1x", "§e" + Formatter.smallCapsFormatter("Shift") + " §7+ §f\uE015§7: §b" + Formatter.smallCapsFormatter("Kaufen") + " 64x §7[§d" + bennibaumeister.getPrice() * 64 + " §f\uE041§7]", "§e" + Formatter.smallCapsFormatter("Drop") + "§7: §b" + Formatter.smallCapsFormatter("Kaufen") + "§7(" + Formatter.smallCapsFormatter("eigene Menge") + ")")));
            i++;
        }
    }

    public void saveMoney(boolean insert) {
        Connection connection = PlunaIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                if (insert) {
                    try {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO islandsMoney(`Player`,`Money`) VALUES (?,?);");
                        statement.setObject(1, player.getUniqueId().toString());
                        statement.setObject(2, money);
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Money is saved!");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        PreparedStatement statement = connection.prepareStatement("UPDATE islandsMoney SET `Money` = ? WHERE Player = ?;");
                        statement.setObject(1, money);
                        statement.setObject(2, player.getUniqueId().toString());
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Money is updated!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void loadMoney() {
        CompletableFuture.runAsync(() -> {
            try {
                PreparedStatement statement = PlunaIsland.getInstance().getMysql().getConnection().prepareStatement("select * from islandsMoney WHERE Player = ?");
                statement.setObject(1, this.player.getUniqueId().toString());
                statement.execute();
                statement.closeOnCompletion();
                ResultSet set = statement.getResultSet();
                if (set.next()) {
                    int money = set.getInt("Money");
                    this.setMoney(money);
                } else {
                    this.saveMoney(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
