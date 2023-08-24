package de.plunamc.island.listener;

import de.plunaapi.chatinput.ChatInput;
import de.plunaapi.chatinput.PlayerChatInputsEvent;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.island.Island;
import de.plunamc.island.market.*;
import de.plunamc.island.utils.Formatter;
import de.plunamc.island.utils.Itemmanager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class InventoryClickListener implements Listener {

    private HashMap<Player, Material> materialHashMap = new HashMap<>();

    private ArrayList<Player> sell = new ArrayList<>();

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        ItemStack clickeditem = event.getCurrentItem();

        //Warp Inventar
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE054")) {
            String name = event.getCurrentItem().getItemMeta().getDisplayName();
            Bukkit.dispatchCommand(player, "warp teleport "+name);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }
        //Volker Verkauf
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE048")) {
            Inventory inventory = event.getView().getTopInventory();

            if (event.getCurrentItem().getType() == Material.PAPER) {
                event.setCancelled(true);
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlistone());
            }
            if (clickeditem.getType() == Material.POPPED_CHORUS_FRUIT && clickeditem.getItemMeta().getCustomModelData() == 4) {
                event.setCancelled(true);
                for (ItemStack sellItem : event.getClickedInventory()) {
                    for (VolkerVerkauf volkerVerkauf : VolkerVerkauf.values()) {
                        if (sellItem == null) continue;
                        if (sellItem.getType().equals(volkerVerkauf.getMaterial())) {
                            PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).addMoney(volkerVerkauf.getSellprice() * sellItem.getAmount());
                            inventory.remove(sellItem);
                        }
                    }
                }
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast Items verkauft.");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
            }

            //Slot 52

            if(event.getClickedInventory().equals(inventory)){
                switch (event.getAction()){
                    case PLACE_ALL, PLACE_ONE, PLACE_SOME, PICKUP_ALL, PICKUP_HALF, PICKUP_ONE, PICKUP_SOME, MOVE_TO_OTHER_INVENTORY ->{
                        List<String> lore = new ArrayList<>();
                        lore.add(" ");
                        int all = 0;
                        for (ItemStack itemStack : inventory.getContents()) {
                            if(itemStack == null) continue;
                            if(VolkerVerkauf.isMaterialInList(itemStack.getType())){
                                lore.add(itemStack.getItemMeta().getDisplayName() + " §8- §b"+itemStack.getAmount() * VolkerVerkauf.getMaterialPrice(itemStack.getType()) + " §f\uE041");
                                all += itemStack.getAmount() * VolkerVerkauf.getMaterialPrice(itemStack.getType());
                            }
                        }
                        lore.add(" ");
                        lore.add("§7Gesamtpreis: §d"+all+"§f\uE041");

                        ItemMeta sellItem = inventory.getItem(52).getItemMeta();
                        sellItem.setLore(lore);
                        inventory.getItem(52).setItemMeta(sellItem);
                    }
                }
            }

        }
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE049")) {
            event.setCancelled(true);
            if (!clickeditem.getItemMeta().hasCustomModelData()) return;
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 3) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlisttwo());
            }
        }
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE050")) {
            event.setCancelled(true);
            if (!clickeditem.getItemMeta().hasCustomModelData()) return;
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 3) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlistthree());
            }
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 2) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlistone());
            }
        }
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE051")) {
            event.setCancelled(true);
            if (!clickeditem.getItemMeta().hasCustomModelData()) return;
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 3) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlistfour());
            }
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 2) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlisttwo());
            }
        }
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE052")) {
            event.setCancelled(true);
            if (!clickeditem.getItemMeta().hasCustomModelData()) return;
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 3) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlistfive());
            }
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 2) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlistthree());
            }
        }
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE053")) {
            event.setCancelled(true);
            if (!clickeditem.getItemMeta().hasCustomModelData()) return;
            if (event.getCurrentItem().getType() == Material.POPPED_CHORUS_FRUIT && event.getCurrentItem().getItemMeta().getCustomModelData() == 2) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getVolkerverlistfour());
            }
        }
        //Benni Baumeister
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE044")) {
            if (event.isLeftClick() && !event.isShiftClick()) {
                BenniBaumeister[] benniBaumeister = BenniBaumeister.values();
                Optional<BenniBaumeister> itemStack = Arrays.stream(benniBaumeister).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice()) {
                        if (isInventoryFull(player) == true) return;
                        player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice());
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);

                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.isLeftClick() && event.isShiftClick()) {
                BenniBaumeister[] benniBaumeister = BenniBaumeister.values();
                Optional<BenniBaumeister> itemStack = Arrays.stream(benniBaumeister).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice() * 64) {
                        if (isInventoryFull(player) == true) return;
                        int slotLimit = 64;
                        for (int i = 0; i < slotLimit; i++) {
                            player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        }
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * 64);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.getClick().equals(ClickType.DROP)) {
                materialHashMap.put(player, clickeditem.getType());
                ChatInput chatInput = new ChatInput(player, "bennibuy", "§7Gebe die Anzahl an die du kaufen möchtest! §8(§71-64§8)");
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du kannst mit /cancel den Kauf abbrechen.");
                chatInput.apply();
                player.closeInventory();
            }
            event.setCancelled(true);
        }
        //Greta Gärtner
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE045")) {
            if (event.isLeftClick() && !event.isShiftClick()) {
                GretaGartner[] GretaGartner = de.plunamc.island.market.GretaGartner.values();
                Optional<GretaGartner> itemStack = Arrays.stream(GretaGartner).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice()) {
                        if (isInventoryFull(player) == true) return;
                        player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice());
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);

                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.isLeftClick() && event.isShiftClick()) {
                GretaGartner[] GretaGartner = de.plunamc.island.market.GretaGartner.values();
                Optional<GretaGartner> itemStack = Arrays.stream(GretaGartner).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice() * 64) {
                        if (isInventoryFull(player) == true) return;
                        int slotLimit = 64;
                        for (int i = 0; i < slotLimit; i++) {
                            player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        }
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * 64);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.getClick().equals(ClickType.DROP)) {
                materialHashMap.put(player, clickeditem.getType());
                ChatInput chatInput = new ChatInput(player, "gretagartner", "§7Gebe die Anzahl an die du kaufen möchtest! §8(§71-64§8)");
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du kannst mit /cancel den Kauf abbrechen.");
                chatInput.apply();
                player.closeInventory();
            }
            event.setCancelled(true);
        }
        //ZyrusZüchter
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE046")) {
            if (event.isLeftClick() && !event.isShiftClick()) {
                ZyrusZuchter[] ZyrusZuchter = de.plunamc.island.market.ZyrusZuchter.values();
                Optional<ZyrusZuchter> itemStack = Arrays.stream(ZyrusZuchter).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice()) {
                        if (isInventoryFull(player) == true) return;
                        player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice());
                        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);

                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.isLeftClick() && event.isShiftClick()) {
                ZyrusZuchter[] ZyrusZuchter = de.plunamc.island.market.ZyrusZuchter.values();
                Optional<ZyrusZuchter> itemStack = Arrays.stream(ZyrusZuchter).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice() * 64) {
                        if (isInventoryFull(player) == true) return;
                        int slotLimit = 64;
                        for (int i = 0; i < slotLimit; i++) {
                            player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        }
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * 64);
                        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.getClick().equals(ClickType.DROP)) {
                materialHashMap.put(player, clickeditem.getType());
                ChatInput chatInput = new ChatInput(player, "zyruszuchter", "§7Gebe die Anzahl an die du kaufen möchtest! §8(§71-64§8)");
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du kannst mit /cancel den Kauf abbrechen.");
                chatInput.apply();
                player.closeInventory();
            }
            event.setCancelled(true);
        }
        //HildeHolle
        if (event.getView().getTitle().equals(PlunaIsland.getInstance().getInventoryTitle() + "\uE047")) {
            if (event.isLeftClick() && !event.isShiftClick()) {
                HildaHolle[] HildaHolle = de.plunamc.island.market.HildaHolle.values();
                Optional<HildaHolle> itemStack = Arrays.stream(HildaHolle).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice()) {
                        if (isInventoryFull(player) == true) return;
                        player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice());
                        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);

                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.isLeftClick() && event.isShiftClick()) {
                HildaHolle[] HildaHolle = de.plunamc.island.market.HildaHolle.values();
                Optional<HildaHolle> itemStack = Arrays.stream(HildaHolle).filter(itemstacks -> itemstacks.getMaterial().equals(clickeditem.getType())).findAny();
                if (itemStack.isPresent()) {
                    if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= itemStack.get().getPrice() * 64) {
                        if (isInventoryFull(player) == true) return;
                        int slotLimit = 64;
                        for (int i = 0; i < slotLimit; i++) {
                            player.getInventory().addItem(new ItemStack(clickeditem.getType()));
                        }
                        player.closeInventory();
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                        PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * 64);
                        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                    }
                }
            }
            if (event.getClick().equals(ClickType.DROP)) {
                materialHashMap.put(player, clickeditem.getType());
                ChatInput chatInput = new ChatInput(player, "hildaholle", "§7Gebe die Anzahl an die du kaufen möchtest! §8(§71-64§8)");
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du kannst mit /cancel den Kauf abbrechen.");
                chatInput.apply();
                player.closeInventory();
            }
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onChatInput(PlayerChatInputsEvent event) {
        ChatInput chatInput = event.getChatInput();
        Player player = event.getPlayer();
        if (chatInput.getNamespace().startsWith("bennibuy")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(event.getInput());
            } catch (NumberFormatException ignored) {
            }
            if (!event.getInput().equalsIgnoreCase("cancel")) {
                if (amount > 0 && amount <= 64) {
                    if (materialHashMap.containsKey(player)) {
                        BenniBaumeister[] benniBaumeister = BenniBaumeister.values();
                        Optional<BenniBaumeister> itemStack = Arrays.stream(benniBaumeister).filter(itemstacks -> itemstacks.getMaterial().equals(materialHashMap.get(player))).findAny();
                        if (isInventoryFull(player) == true) {
                            chatInput.disapply();
                            materialHashMap.remove(player);
                        }
                        if (itemStack.isPresent()) {
                            int price = itemStack.get().getPrice() * amount;
                            if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= price) {
                                for (int i = 0; i < amount; i++) {
                                    player.getInventory().addItem(new ItemStack(materialHashMap.get(player)));
                                }
                                player.closeInventory();
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                                PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * amount);
                                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                                PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                            } else {
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                            }
                        }
                    }
                } else {
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Bitte gebe eine Zahl zwischen 1-64 an.");
                }
            } else {
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den Kauf abgebrochen.");
            }
            chatInput.disapply();
            materialHashMap.remove(player);
        }

        if (chatInput.getNamespace().startsWith("gretagartner")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(event.getInput());
            } catch (NumberFormatException ignored) {
            }
            if (!event.getInput().equalsIgnoreCase("cancel")) {
                if (amount > 0 && amount <= 64) {
                    if (materialHashMap.containsKey(player)) {
                        GretaGartner[] gretaGartner = GretaGartner.values();
                        Optional<GretaGartner> itemStack = Arrays.stream(gretaGartner).filter(itemstacks -> itemstacks.getMaterial().equals(materialHashMap.get(player))).findAny();
                        if (isInventoryFull(player) == true) {
                            chatInput.disapply();
                            materialHashMap.remove(player);
                        }
                        if (itemStack.isPresent()) {
                            int price = itemStack.get().getPrice() * amount;
                            if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= price) {
                                for (int i = 0; i < amount; i++) {
                                    player.getInventory().addItem(new ItemStack(materialHashMap.get(player)));
                                }
                                player.closeInventory();
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                                PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * amount);
                                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                                PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                            } else {
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                            }
                        }
                    }
                } else {
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Bitte gebe eine Zahl zwischen 1-64 an.");
                }
            } else {
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den Kauf abgebrochen.");
            }
            chatInput.disapply();
            materialHashMap.remove(player);
        }

        if (chatInput.getNamespace().startsWith("zyruszuchter")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(event.getInput());
            } catch (NumberFormatException ignored) {
            }
            if (!event.getInput().equalsIgnoreCase("cancel")) {
                if (amount > 0 && amount <= 64) {
                    if (materialHashMap.containsKey(player)) {
                        ZyrusZuchter[] zyrusZuchter = ZyrusZuchter.values();
                        Optional<ZyrusZuchter> itemStack = Arrays.stream(zyrusZuchter).filter(itemstacks -> itemstacks.getMaterial().equals(materialHashMap.get(player))).findAny();
                        if (isInventoryFull(player) == true) {
                            chatInput.disapply();
                            materialHashMap.remove(player);
                        }
                        if (itemStack.isPresent()) {
                            int price = itemStack.get().getPrice() * amount;
                            if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= price) {
                                for (int i = 0; i < amount; i++) {
                                    player.getInventory().addItem(new ItemStack(materialHashMap.get(player)));
                                }
                                player.closeInventory();
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                                PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * amount);
                                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                                PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                            } else {
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                            }
                        }
                    }
                } else {
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Bitte gebe eine Zahl zwischen 1-64 an.");
                }
            } else {
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den Kauf abgebrochen.");
            }
            chatInput.disapply();
            materialHashMap.remove(player);
        }


        if (chatInput.getNamespace().startsWith("hildaholle")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(event.getInput());
            } catch (NumberFormatException ignored) {
            }
            if (!event.getInput().equalsIgnoreCase("cancel")) {
                if (amount > 0 && amount <= 64) {
                    if (materialHashMap.containsKey(player)) {
                        HildaHolle[] hildaHolle = HildaHolle.values();
                        Optional<HildaHolle> itemStack = Arrays.stream(hildaHolle).filter(itemstacks -> itemstacks.getMaterial().equals(materialHashMap.get(player))).findAny();
                        if (isInventoryFull(player) == true) {
                            chatInput.disapply();
                            materialHashMap.remove(player);
                        }
                        if (itemStack.isPresent()) {
                            int price = itemStack.get().getPrice() * amount;
                            if (PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney() >= price) {
                                for (int i = 0; i < amount; i++) {
                                    player.getInventory().addItem(new ItemStack(materialHashMap.get(player)));
                                }
                                player.closeInventory();
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast ein Item gekauft");
                                PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(itemStack.get().getPrice() * amount);
                                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
                                PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                            } else {
                                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast nicht genügend Bits.");
                            }
                        }
                    }
                } else {
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Bitte gebe eine Zahl zwischen 1-64 an.");
                }
            } else {
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den Kauf abgebrochen.");
            }
            chatInput.disapply();
            materialHashMap.remove(player);
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (materialHashMap.containsKey(player)) {
            event.setCancelled(true);
        }
    }

    public boolean isInventoryFull(Player player) {
        Inventory inventory = player.getInventory();
        if (inventory.firstEmpty() == -1) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            player.closeInventory();
            player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dein Inventar ist voll.");
            return true;
        }
        return false;

    }
}
