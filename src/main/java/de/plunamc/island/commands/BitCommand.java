package de.plunamc.island.commands;

import de.plunaapi.scoreboard.PlayerScore;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.island.Island;
import de.plunamc.packets.data.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BitCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            Integer money = PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).getMoney();
            Island getIslandOwner = PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId());
            if (PlunaIsland.getInstance().getRankAPI().getPlayerRank(player).isHigherEqualsLevel(Rank.DEVELOPER)) {
                if (args.length == 0) {
                    player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Deine Bits: §b" + money);
                } else if (args.length == 3) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        if (!isNumeric(args[2])) return true;
                        if (args[0].equalsIgnoreCase("set")) {
                            PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).setMoney(Integer.parseInt(args[2]));
                            PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).saveMoney();
                            player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den Spieler §b" + target.getName() + "§7 auf §b" + args[2] + "§7 Bits gesetzt.");
                        } else if (args[0].equalsIgnoreCase("remove")) {
                            PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).removeMoney(Integer.parseInt(args[2]));
                            player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den Spieler §b" + target.getName() + " §b" + args[2] + " §7Bits entfernt.");
                        } else if (args[0].equalsIgnoreCase("add")) {
                            PlunaIsland.getInstance().getPlayerManager().getPlayerData(player).addMoney(Integer.parseInt(args[2]));
                            player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast den Spieler §b" + target.getName() + " §b" + args[2] + "§7 Bits gegeben.");
                        }
                        PlayerScore playerScore = PlayerScore.getScores().get(player);
                        PlunaIsland.getInstance().getPlayerManager().setSpawnScoreboard(player);
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler ist nicht online.");
                    }
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler §b" + target.getName() + " §7hat §b" + PlunaIsland.getInstance().getPlayerManager().getPlayerData(target).getMoney() + "§7 Bits.");
                    } else {
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler ist nicht online.");
                    }
                }
            } else {
                player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Deine Bits: §b" + money);
            }
        } else {
            sender.sendMessage("console");
        }
        return false;
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (PlunaIsland.getInstance().getRankAPI().getPlayerRank(player).isHigherEqualsLevel(Rank.DEVELOPER)) {
                List<String> tabs = new ArrayList<>();
                if (args.length == 1) {
                    List<String> match = new ArrayList<>();
                    match.add("add");
                    match.add("remove");
                    match.add("set");
                    String name = args[0].isEmpty() ? "" : args[0];
                    match.forEach(matchs -> {
                        if (matchs.contains(name)) {
                            tabs.add(matchs);
                        }
                    });
                }
                if (args.length == 2) {
                    String name = args[1].isEmpty() ? "" : args[1];
                    Bukkit.getOnlinePlayers().forEach(players -> {
                        if (players.getName().contains(name)) {
                            tabs.add(players.getName());
                        }
                    });
                }
                return tabs;
            }
        }
        return null;
    }
}
