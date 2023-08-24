package de.plunamc.island.commands;

import de.plunaapi.scoreboard.PlayerScore;
import de.plunamc.island.PlunaIsland;
import de.plunamc.packets.data.Rank;
import de.plunamc.island.island.Island;
import de.plunamc.island.island.IslandSize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BorderCommand implements CommandExecutor, TabCompleter {

    private PlunaIsland plugin;

    public BorderCommand(PlunaIsland plugin) {
        this.plugin = plugin;
    }

    private boolean admin = false;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            Island island = plugin.getIslandManager().getIsland(player.getUniqueId());
            String s1 = args[0];
            if (PlunaIsland.getInstance().getRankAPI().getPlayerRank(player).isHigherEqualsLevel(Rank.ADMIN)) {
                if (args.length == 1) {
                    if (island != null) {
                        if (checkUsingCharAtMethod(s1)) {
                            if (s1.equalsIgnoreCase("0")) {
                                island.setIslandSize(IslandSize.EINS);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("1")) {
                                island.setIslandSize(IslandSize.ZWEI);
                                island.updateBorderAdmin();
                                island.setStreams(300);
                            }
                            if (s1.equalsIgnoreCase("2")) {
                                island.setIslandSize(IslandSize.DREI);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("3")) {
                                island.setIslandSize(IslandSize.VIER);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("4")) {
                                island.setIslandSize(IslandSize.FÜNF);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("5")) {
                                island.setIslandSize(IslandSize.SECHS);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("6")) {
                                island.setIslandSize(IslandSize.SIEBEN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("7")) {
                                island.setIslandSize(IslandSize.ACHT);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("8")) {
                                island.setIslandSize(IslandSize.NEUN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("9")) {
                                island.setIslandSize(IslandSize.ZEHN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("10")) {
                                island.setIslandSize(IslandSize.ELF);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("11")) {
                                island.setIslandSize(IslandSize.ZWÖLF);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("12")) {
                                island.setIslandSize(IslandSize.DREIZEHN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("13")) {
                                island.setIslandSize(IslandSize.VIERZEHN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("14")) {
                                island.setIslandSize(IslandSize.FÜNFZEHN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("15")) {
                                island.setIslandSize(IslandSize.SECHSZEHN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("16")) {
                                island.setIslandSize(IslandSize.SIEBZEHN);
                                island.updateBorderAdmin();

                            }
                            if (s1.equalsIgnoreCase("17")) {
                                island.setIslandSize(IslandSize.ACHTZEHN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("18")) {
                                island.setIslandSize(IslandSize.NEUNZEHN);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("19")) {
                                island.setIslandSize(IslandSize.ZWANZIG);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("20")) {
                                island.setIslandSize(IslandSize.EINUNDZWANZIG);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("21")) {
                                island.setIslandSize(IslandSize.ZWEIUNDZWANZIG);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("22")) {
                                island.setIslandSize(IslandSize.DREIUNDZWANZIG);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("23")) {
                                island.setIslandSize(IslandSize.VIERUNDZWANZIG);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("24")) {
                                island.setIslandSize(IslandSize.FÜNFUNDZWANZIG);
                                island.updateBorderAdmin();
                            }
                            if (s1.equalsIgnoreCase("25")) {
                                island.setIslandSize(IslandSize.SECHSUNDZWANZIG);
                                island.updateBorderAdmin();
                            }
                            island.setLevel(Integer.valueOf(args[0] +1));
                            PlayerScore playerScore = PlayerScore.getScores().get(player);
                            playerScore.setScore("§7➥ §f"+ island.getLevel().toString()+"§7/§b"+island.getExp().toString()+"ᴇxᴘ", 3);
                            player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insel wurde auf §b" + island.getIslandSize().getName() + " §7(§b" + island.getIslandSize().getSize() + "§7) gesetzt.");

                        }
                    }
                }
            } else if (PlunaIsland.getInstance().getRankAPI().getPlayerRank(player).isHigherEqualsLevel(Rank.DEVELOPER)) {
                if (args.length == 1) {
                    if (island != null) {
                        if (checkUsingCharAtMethod(s1)) {
                            if (s1.equalsIgnoreCase("0")) {
                                island.setIslandSize(IslandSize.EINS);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("1")) {
                                island.setIslandSize(IslandSize.ZWEI);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("2")) {
                                island.setIslandSize(IslandSize.DREI);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("3")) {
                                island.setIslandSize(IslandSize.VIER);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("4")) {
                                island.setIslandSize(IslandSize.FÜNF);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("5")) {
                                island.setIslandSize(IslandSize.SECHS);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("6")) {
                                island.setIslandSize(IslandSize.SIEBEN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("7")) {
                                island.setIslandSize(IslandSize.ACHT);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("8")) {
                                island.setIslandSize(IslandSize.NEUN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("9")) {
                                island.setIslandSize(IslandSize.ZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("10")) {
                                island.setIslandSize(IslandSize.ELF);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("11")) {
                                island.setIslandSize(IslandSize.ZWÖLF);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("12")) {
                                island.setIslandSize(IslandSize.DREIZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("13")) {
                                island.setIslandSize(IslandSize.VIERZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("14")) {
                                island.setIslandSize(IslandSize.FÜNFZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("15")) {
                                island.setIslandSize(IslandSize.SECHSZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("16")) {
                                island.setIslandSize(IslandSize.SIEBZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("17")) {
                                island.setIslandSize(IslandSize.ACHTZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("18")) {
                                island.setIslandSize(IslandSize.NEUNZEHN);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("19")) {
                                island.setIslandSize(IslandSize.ZWANZIG);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("20")) {
                                island.setIslandSize(IslandSize.EINUNDZWANZIG);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("21")) {
                                island.setIslandSize(IslandSize.ZWEIUNDZWANZIG);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("22")) {
                                island.setIslandSize(IslandSize.DREIUNDZWANZIG);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("23")) {
                                island.setIslandSize(IslandSize.VIERUNDZWANZIG);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("24")) {
                                island.setIslandSize(IslandSize.FÜNFUNDZWANZIG);
                                island.updateBorder();
                            }
                            if (s1.equalsIgnoreCase("25")) {
                                island.setIslandSize(IslandSize.SECHSUNDZWANZIG);
                                island.updateBorder();
                            }
                            island.setLevel(Integer.parseInt(args[0]) +1);
                            PlayerScore playerScore = PlayerScore.getScores().get(player);
                            playerScore.setScore("§7➥ §f"+ island.getLevel().toString(), 3);
                            player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Insel wurde auf §b" + island.getIslandSize().getName() + " §7(§b" + island.getIslandSize().getSize() + "§7) gesetzt.");

                        }
                    }
                }

            }
        } else {
            sender.sendMessage("console");
        }
        return false;
    }

    static boolean checkUsingCharAtMethod(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        char c = str.charAt(0);
        return c >= '0' && c <= '9';
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if (args.length == 1) {
            List<String> match = new ArrayList<>();
            match.add("0");
            match.add("1");
            match.add("2");
            match.add("3");
            match.add("4");
            match.add("5");
            match.add("6");
            match.add("7");
            match.add("8");
            match.add("9");
            match.add("10");
            match.add("11");
            match.add("12");
            match.add("13");
            match.add("14");
            match.add("15");
            match.add("16");
            match.add("17");
            match.add("18");
            match.add("19");
            match.add("20");
            match.add("21");
            match.add("22");
            match.add("23");
            match.add("24");
            match.add("25");
            String name = args[0].isEmpty() ? "" : args[0];
            match.forEach(matchs -> {
                if (matchs.contains(name)) {
                    tabs.add(matchs);
                }
            });
        }
        return tabs;
    }
}

