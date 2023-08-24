package de.plunamc.island.commands;

import de.plunamc.island.PlunaIsland;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            if(args.length == 0){
                PlunaIsland.getInstance().getIslandManager().getIsland(player.getUniqueId()).deleteBossbar(player);
                player.sendMessage(PlunaIsland.getInstance().getPrefix()+"Du wurdest zum Spawn teleportiert.");
                player.teleport(PlunaIsland.getInstance().getIslandManager().getSpawn());
                player.setWorldBorder(null);
            }else{
                player.sendMessage(PlunaIsland.getInstance().getPrefix()+"/spawn");
            }
        }else{
            sender.sendMessage("Console");
        }
        return false;
    }
}
