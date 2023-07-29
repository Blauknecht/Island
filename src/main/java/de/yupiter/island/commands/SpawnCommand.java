package de.yupiter.island.commands;

import de.yupiter.island.YupiterIsland;
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
                player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du wurdest zum Spawn teleportiert.");
                player.teleport(YupiterIsland.getInstance().getIslandManager().getSpawn());
                player.setWorldBorder(null);
            }else{
                player.sendMessage(YupiterIsland.getInstance().getPrefix()+"/spawn");
            }
        }else{
            sender.sendMessage("Console");
        }
        return false;
    }
}
