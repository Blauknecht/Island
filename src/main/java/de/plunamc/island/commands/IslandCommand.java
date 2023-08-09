package de.plunamc.island.commands;

import de.plunamc.island.PlunaIsland;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class IslandCommand implements CommandExecutor, TabCompleter {

    private PlunaIsland plugin;

    public IslandCommand(PlunaIsland plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Island island = plugin.getIslandManager().getIsland(player.getUniqueId());
            if (args.length == 0) {
                if (island != null) {
                    player.teleport(island.getSpawn());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du wurdest zu deiner Insel teleportiert.");
                    player.setWorldBorder(island.getBorder());
                } else {
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is create §8- §7Erstelle dir eine Insel.");
                }
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("delete")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    if (!island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nicht der Insel Besitzer.");
                        return false;
                    }
                    if(!island.isCheckDelete()){
                        player.sendMessage(PlunaIsland.getInstance().getPrefix()+"Bestätige die Löschung deiner Insel mit §b/is delete§7.");
                        island.setCheckDelete(true);
                        return false;
                    }
                    island.getTrustedPlayers().forEach(offlinePlayer -> {
                        if(offlinePlayer.getPlayer() != null){
                            offlinePlayer.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix()+"Der Inselbesitzer §b"+island.getOwner().getName()+" §7hat seine Insel §cgelöscht§7.");
                            offlinePlayer.getPlayer().teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
                        }
                        island.getTrustedPlayers().remove(offlinePlayer);
                    });
                    PlunaIsland.getInstance().getIslandManager().deleteIsland(island);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=!minecraft:player, distance=135]");
                    player.teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix()+"Du hast deine Insel erfolgreich gelöscht.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("settings")) {
                    //TODO
                    return true;
                }
                if (args[0].equalsIgnoreCase("blocks")) {
                    if (PlunaIsland.getInstance().getRankAPI().getPlayerRank(player).isHigherEqualsLevel(Rank.DEVELOPER)) {
                        openInv(player);
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("leave")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    if (island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Owner kann die Insel nicht verlassen.");
                        return false;
                    }
                    island.leave(player.getUniqueId());
                    return true;
                }
                if (args[0].equalsIgnoreCase("info")) {
                    Island locationIsland = plugin.getIslandManager().getIslandAtLocation(player.getLocation());
                    if (locationIsland == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    player.sendMessage("§8§m-------------- §bInsel Informationen §8§m--------------");
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insel-Inhaber: §b" + locationIsland.getOwner().getName());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insel wurde erstellt von: §b" + locationIsland.getCreator().getName());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insel-Level: §b" + locationIsland.getLevel());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insel-EXP: §b" + locationIsland.getExp());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insel-Größe: §b" + locationIsland.getIslandSize().getSize() + " Blöcke §7(§b" + locationIsland.getIslandSize().getName() + "§7)");
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Besuchbar: §a✔");
                    if (!locationIsland.getTrustedPlayers().isEmpty()) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Vertraute Spieler: ");
                        locationIsland.getTrustedPlayers().forEach(offlinePlayer -> {
                            player.sendMessage(PlunaIsland.getInstance().getPrefix() + " ➥ §b" + offlinePlayer.getName());
                        });
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Vertraute Spieler: §b-");
                    }
                    if (!locationIsland.getBanPlayers().isEmpty()) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Gebannte Spieler: ");
                        locationIsland.getTrustedPlayers().forEach(offlinePlayer -> {
                            player.sendMessage(PlunaIsland.getInstance().getPrefix() + " ➥ §c" + offlinePlayer.getName());
                        });
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Gebannte Spieler: §b-");
                    }
                    player.sendMessage("§8>>§8§m-----------------------------------------------§r§8<<");
                    return true;
                }
                if (args[0].equalsIgnoreCase("setspawn")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    Island locationIsland = plugin.getIslandManager().getIslandAtLocation(player.getLocation());
                    if (locationIsland == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist auf keiner Insel.");
                        return false;
                    }
                    if (locationIsland != island) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du kannst kein Spawn auf einer anderen Insel setzen.");
                        return false;
                    }
                    if (!island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nicht der Insel Owner.");
                        return false;
                    }
                    island.setSpawn(player.getLocation());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den neuen Inselspawn gesetzt.");

                    return true;
                }
                if (args[0].equalsIgnoreCase("level")) {
                    Island locationIsland = plugin.getIslandManager().getIslandAtLocation(player.getLocation());
                    if (locationIsland == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist auf keiner Insel.");
                        return false;
                    }
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insellevel: " + locationIsland.getLevel());
                    return true;
                }
                if (args[0].equalsIgnoreCase("setwarp")) {
                    return true;
                }
                if (args[0].equalsIgnoreCase("create")) {
                    if (island == null) {
                        plugin.getIslandManager().createIsland(player);
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast schon eine Insel.");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("help")) {
                    sendHelpSite(player, 1);
                    return true;
                }

            } else if (args.length == 2) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1].toString());
                if (args[0].equalsIgnoreCase("invite")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    Island targetPlayerIsland = plugin.getIslandManager().getIsland(target.getUniqueId());
                    if (targetPlayerIsland != null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler hat schon eine Insel.");
                        return false;
                    }
                    if (!island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nicht der Insel Owner.");
                        return false;
                    }
                    if (island.getTrustedPlayers().contains(target.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dieser Spieler ist schon auf deiner Insel.");
                        return false;
                    }
                    island.invitePlayer(target.getUniqueId());
                    return true;
                }
                if (args[0].equalsIgnoreCase("setowner")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    if (!island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nicht der Insel Owner.");
                        return false;
                    }
                    island.setOwner(target.getUniqueId());
                    return true;
                }
                if (args[0].equalsIgnoreCase("kick")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    if(target.getPlayer() == null){
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dieser Spieler ist nicht online.");
                        return false;
                    }
                    Island targetIsland = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(target.getPlayer().getLocation());
                    if(targetIsland != null && targetIsland != island){
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dieser Spieler ist nicht auf deiner Insel.");
                        return false;
                    }
                    target.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix()+"Du wurdest von der Insel von §b"+island.getOwner().getName()+" §7gekickt.");
                    player.sendMessage(PlunaIsland.getInstance().getPrefix()+"Du hast den Spieler §b"+target.getName()+" §7von der Insel gekickt.");
                    target.getPlayer().teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
                    return true;
                }
                if (args[0].equalsIgnoreCase("ban")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    if (!island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nicht der Insel Owner.");
                        return false;
                    }
                    Island targetIsland = PlunaIsland.getInstance().getIslandManager().getIslandAtLocation(target.getPlayer().getLocation());
                    if(targetIsland != null && targetIsland != island){
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dieser Spieler ist nicht auf deiner Insel.");
                        return false;
                    }
                    target.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix()+"Du wurdest von der Insel von §b"+island.getOwner().getName()+" §7gebannt.");
                    player.sendMessage(PlunaIsland.getInstance().getPrefix()+"Du hast den Spieler §b"+target.getName()+" §7von der Insel gebannt.");
                    island.getBanPlayers().add(target);
                    island.save(false);
                    target.getPlayer().teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
                    return true;
                }
                if (args[0].equalsIgnoreCase("unban")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    if (!island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nicht der Insel Owner.");
                        return false;
                    }
                    if(!island.getBanPlayers().contains(target)){
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dieser Spieler ist nicht gebannt.");
                        return false;
                    }
                    player.sendMessage(PlunaIsland.getInstance().getPrefix()+"Du hast den Spieler §b"+target.getName()+" §7von der Insel entbannt.");
                    island.getBanPlayers().remove(target);
                    island.save(false);
                    return true;
                }
                if (args[0].equalsIgnoreCase("visit")) {
                    Island targetIsland = PlunaIsland.getInstance().getIslandManager().getIsland(target.getUniqueId());
                    if (targetIsland == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dieser Spieler hat keine Insel.");
                        return false;
                    }
                    player.sendMessage(PlunaIsland.getInstance().getPrefix()+"Du besuchst nun die Insel von §b"+target.getName());
                    player.teleport(targetIsland.getSpawn());
                    player.setWorldBorder(targetIsland.getBorder());
                    return true;
                }
                if (args[0].equalsIgnoreCase("warp")) {
                    //TODO
                    return true;
                }
                if (args[0].equalsIgnoreCase("accept")) {
                    Island invitedIsland = plugin.getIslandManager().getInvitedIsland(player, args[1]);
                    if (invitedIsland == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du wurdest nicht zu dieser Insel eingeladen.");
                        return false;
                    }
                    invitedIsland.addPlayer(player.getUniqueId());
                    player.teleport(invitedIsland.getSpawn());
                    return true;
                }
                if (args[0].equalsIgnoreCase("deny")) {
                    Island invitedIsland = plugin.getIslandManager().getInvitedIsland(player, args[1]);
                    if (invitedIsland == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du wurdest nicht zu dieser Insel eingeladen.");
                        return false;
                    }
                    invitedIsland.getInvitePlayers().remove(player.getUniqueId());
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast die Einladung §cabgelehnt§7.");
                    if (invitedIsland.getOwner().getPlayer() != null) {
                        invitedIsland.getOwner().getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler §b" + player.getName() + " §7hat die Einladung §cabgelehnt§7.");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    if (island == null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast keine Insel.");
                        return false;
                    }
                    if (!island.getOwner().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nicht der Insel Owner.");
                        return false;
                    }
                    island.removePlayer(target.getUniqueId());
                    return true;
                }
                if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("2")) {
                    sendHelpSite(player, 2);
                    return true;
                }
                if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("1")) {
                    sendHelpSite(player, 1);
                    return true;
                }

            } else {
                sendHelpSite(player, 1);
            }
        }
        return false;
    }

    public void sendHelpSite(Player p, int page) {
        if (page == 1) {
            p.sendMessage("§8>>§8§m--------------------§b Seite 1. §8§m--------------------§r§8<<");
            p.sendMessage("");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is §8- §7Teleportiere dich zu deiner Insel.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is create §8- §7Erstelle dir eine Insel.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is delete §8- §7Lösche deine Insel.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is settings §8- §7Öffnet die Insel-Benutzeroberfläche.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is leave §8- §7Entziehe dir selber die Rechte auf der Insel.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is info §8- §7Gibt dir eine Information deiner Insel wieder.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is setspawn §8- §7Setze dein Inselspawn");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is level §8- §7Zeigt dir an welches Level (d)eine Insel");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is setwarp §8- §7Setze ein Warp für deine Insel. (§bmax 1.§7)");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is warp §8- §7Öffnet ein Liste der erstellten Warps.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is spawn §8- §7Teleportiere dich zum Spawn.");
            p.sendMessage("");
            TextComponent message = new TextComponent(PlunaIsland.getInstance().getPrefix() + "➥ Seite 2.");
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§o§7Klicke hier für Seite 2.").create()));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is help 2"));
            p.spigot().sendMessage(message);
            p.sendMessage("");
            p.sendMessage("§8>>§8§m-----------------------------------------------§r§8<<");
        } else if (page == 2) {
            p.sendMessage("§8>>§8§m--------------------§b Seite 2. §8§m--------------------§r§8<<");
            p.sendMessage("");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is invite <Spieler> §8- §7Lade einen Spieler zu deiner Insel ein. (§bmax. 1 Spieler§7)");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is setowner <Spieler> §8- §7Übergebe deine Insel einem anderen Spieler.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is kick <Spieler> §8- §7Werfe Spieler von deiner Insel.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is ban <Spieler> §8- §7Banne ein Spieler von deiner Insel.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is unban <Spieler> §8- §7Entbanne ein Spieler von deiner Insel.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is visit <Spieler> §8- §7Besuche eine Insel eines anderen Spielers.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is warp <Spieler> §8- §7Warpe dich du zu einer Insel eines anderen Spielers.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is accept <Spieler> §8- §7Nehme eine Einladung an, um bei einer anderen Insel mitzuhelfen.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is deny <Spieler> §8- §7Lehnt die Einladung ab, um bei einer anderen Insel mitzuhelfen.");
            p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/is remove <Spieler> §8- §7Entfernt die Rechte eines Spieler für deine Insel.");
            p.sendMessage("");
            TextComponent message = new TextComponent(PlunaIsland.getInstance().getPrefix() + "➥ Seite 1.");
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§o§7Klicke hier für Seite 1.").create()));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is help"));
            p.spigot().sendMessage(message);
            p.sendMessage("");
            p.sendMessage("§8>>§8§m-----------------------------------------------§r§8<<");
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if (args.length == 1) {
            List<String> match = new ArrayList<>();
            match.add("delete");
            match.add("create");
            match.add("settings");
            match.add("leave");
            match.add("info");
            match.add("setspawn");
            match.add("level");
            match.add("setwarp");
            match.add("warp");
            match.add("help");
            match.add("invite");
            match.add("setowner");
            match.add("kick");
            match.add("ban");
            match.add("unban");
            match.add("visit");
            match.add("warp");
            match.add("accept");
            match.add("deny");
            match.add("remove");
            String name = args[0].isEmpty() ? "" : args[0];
            match.forEach(matchs -> {
                if (matchs.contains(name)) {
                    tabs.add(matchs);
                }
            });
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("setowner") || args[0].equalsIgnoreCase("visit") || args[0].equalsIgnoreCase("warp")) {
                String name = args[1].isEmpty() ? "" : args[1];
                Bukkit.getOnlinePlayers().forEach(players -> {
                    if (players.getName().contains(name)) {
                        tabs.add(players.getName());
                    }
                });
            }
            if (args[0].equalsIgnoreCase("help")) {
                tabs.add("1");
                tabs.add("2");
            }
            if (args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("ban")) {
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    Island island = PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId());
                    String name = args[1].isEmpty() ? "" : args[1];
                    PlunaIsland.getInstance().getIslandManager().getPlayersOnIsland(island).forEach(players ->{
                        if(players.getName().contains(name)){
                            tabs.add(players.getName());
                        }
                    });
                }
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    Island island = PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId());
                    String name = args[1].isEmpty() ? "" : args[1];
                    island.getTrustedPlayers().forEach(offlinePlayer -> {
                        if(offlinePlayer.getName().contains(name)){
                            tabs.add(offlinePlayer.getName());
                        }
                    });
                }
            }
            if (args[0].equalsIgnoreCase("unban")) {
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    Island island = PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId());
                    String name = args[1].isEmpty() ? "" : args[1];
                    island.getBanPlayers().forEach(offlinePlayer -> {
                        if(offlinePlayer.getName().contains(name)){
                            tabs.add(offlinePlayer.getName());
                        }
                    });
                }
            }
            if (args[0].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("acccept")) {
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    String name = args[1].isEmpty() ? "" : args[1];
                    PlunaIsland.getInstance().getIslandManager().getInvitedIslands(player).forEach(island -> {
                        if(island.getOwner().getName().contains(name)){
                            tabs.add(island.getOwner().getName());
                        }
                    });

                }
            }
        }
        return tabs;
    }

    public void openInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§8» §aCustomblöcke");

        //00 01 02 03 04 05 06 07 08
        //09 10 11 12 13 14 15 16 17
        //18 19 20 21 22 23 24 25 26
        //27 28 29 30 31 32 33 34 35
        //36 37 38 39 40 41 42 43 44
        //45 46 47 48 49 50 51 52 53

        //Items
        inv.setItem(10,createItem(Material.PRISMARINE_SHARD, 1, 1, "§cRubine"));
        inv.setItem(11,createItem(Material.PRISMARINE_SHARD, 1, 2, "§cRubinenblock"));
        inv.setItem(12,createItem(Material.PRISMARINE_SHARD, 1, 3, "§eWeizengenerator"));
        inv.setItem(13,createItem(Material.PRISMARINE_SHARD, 1, 4, "§6Karottengenerator"));
        inv.setItem(14,createItem(Material.PRISMARINE_SHARD, 1, 5, "§aKartoffelgenerator"));
        inv.setItem(15,createItem(Material.PRISMARINE_SHARD, 1, 6, "§6Kürbisgenerator"));
        inv.setItem(16,createItem(Material.PRISMARINE_SHARD, 1, 7, "§aMelonengenerator"));
        inv.setItem(28,createItem(Material.PRISMARINE_SHARD, 1, 8, "§6Zuckerrohrgenerator"));
        inv.setItem(29,createItem(Material.PRISMARINE_SHARD, 1, 9, "§aBambusgenerator"));
        inv.setItem(30,createItem(Material.PRISMARINE_SHARD, 1, 10, "§2Mossgenerator"));
        inv.setItem(31,createItem(Material.PRISMARINE_SHARD, 1, 11, "§eHoniggenerator"));
        inv.setItem(32,createItem(Material.PRISMARINE_SHARD, 1, 12, "§7Seetanggenerator"));
        inv.setItem(33,createItem(Material.PRISMARINE_SHARD, 1, 13, "§7Eisengenerator"));

        p.openInventory(inv);
    }
    public static ItemStack createItem(Material mat, int amount, int custommodeldata, String name){
        ItemStack it = new ItemStack(mat,amount);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setCustomModelData(custommodeldata);
        it.setItemMeta(meta);
        return it;
    }
    public static int randomInt(int min, int max){
        Random r = new Random();
        int i = r.nextInt(max-min);
        return i;
    }
}
