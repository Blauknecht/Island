package de.plunamc.island.commands;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.manager.IslandManager;
import de.plunamc.island.warps.Warps;
import de.plunamc.packets.data.Rank;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.openInventory(PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getWarpInventory());
                player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("change")) {
                    if (PlunaIsland.getInstance().getWarpManager().getWarps(player.getUniqueId()) != null) {
                        Warps warps = PlunaIsland.getInstance().getWarpManager().getWarps(player.getUniqueId());
                        String warpName = warps.getWarpName();
                        PlunaIsland.getInstance().getWarpManager().createWarp(player.getLocation(), warpName, player);
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "§7Du hast die Location für den Warp §b" + warpName + " §7umgeändert.");
                        return true;
                    }
                }else{
                    sendPlayerHelpSite(player);
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (PlunaIsland.getInstance().getWarpManager().getWarps(player.getUniqueId()) == null) {
                        PlunaIsland.getInstance().getWarpManager().createWarp(player.getLocation(), args[1], player);
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "§7Du hast dein Warp §b" + args[1] + " §7erfolgreich gesetzt.");
                        return true;
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "§7Du hast schon ein Warp.");
                        return true;
                    }

                }
                if (args[0].equalsIgnoreCase("delete")) {
                    if (PlunaIsland.getInstance().getWarpManager().getWarps(player.getUniqueId()) != null) {
                        Warps warps = PlunaIsland.getInstance().getWarpManager().getWarps(player.getUniqueId());
                        warps.delete();
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("teleport")) {
                    if (PlunaIsland.getInstance().getWarpManager().getWarpsByName(args[1]) != null) {
                        player.teleport(PlunaIsland.getInstance().getWarpManager().getWarpsByName(args[1]).getWarpLocation());
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du wurdest zu dem Warp §b" + args[1] + " §7teleportiert.");
                        return true;
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Dieser Warp existiert nicht.");
                        return true;
                    }
                }
            }else{
                sendPlayerHelpSite(player);
            }

        } else {
            sender.sendMessage("console");
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if (args.length == 1) {
            List<String> match = new ArrayList<>();
            match.add("create");
            match.add("remove");
            match.add("change");
            match.add("teleport");
            String name = args[0].isEmpty() ? "" : args[0];
            match.forEach(matchs -> {
                if (matchs.contains(name)) {
                    tabs.add(matchs);
                }
            });
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("teleport")) {
                String name = args[1].isEmpty() ? "" : args[1];
                PlunaIsland.getInstance().getWarpManager().getWarps().forEach(warps -> {
                    if (warps.getWarpName().contains(name)) {
                        tabs.add(warps.getWarpName());
                    }
                });
            }
        }
        return tabs;
    }
    public void sendPlayerHelpSite(Player p){
        p.sendMessage("§8>>§8§m--------------------§b Seite 1. §8§m--------------------§r§8<<");
        p.sendMessage("");
        p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/warp §8- §7Listet die Warps auf.");
        p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/warp teleport §8- §7Teleportiert dich zu einem Warp.");
        p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/warp create §8- §7Erstelle deinen Warp.");
        p.sendMessage(PlunaIsland.getInstance().getPrefix() + "/warp delete §8- §7Lösche deinen Warp.");
        p.sendMessage("");
        p.sendMessage("§8>>§8§m-----------------------------------------------§r§8<<");
    }
}
