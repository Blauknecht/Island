package de.plunamc.island.commands;

import de.plunamc.island.PlunaIsland;
import de.plunamc.packets.data.Rank;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (PlunaIsland.getInstance().getRankAPI().getPlayerRank(player).isHigherEqualsLevel(Rank.DEVELOPER)) {
                if (args.length == 0) {
                    if (PlunaIsland.getInstance().getBuild().contains(player)) {
                        PlunaIsland.getInstance().getBuild().remove(player);
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nun nicht mehr im Buildmodus.");
                    } else {
                        PlunaIsland.getInstance().getBuild().add(player);
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist nun im Buildmodus.");
                    }
                }
            }
        } else {
            sender.sendMessage("console");
        }
        return false;
    }
}
