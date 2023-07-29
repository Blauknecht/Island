package de.yupiter.island.commands;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.bukkit.event.BukkitCloudServiceRegisterEvent;
import de.dytanic.cloudnet.ext.bridge.node.CloudNetBridgeModule;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.kevloe.packets.types.Rank;
import de.yupiter.island.YupiterIsland;
import de.yupiterapi.playerdata.RankAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class WarzoneCommand implements CommandExecutor {

    boolean wartung = false;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            RankAPI rankAPI = new RankAPI();
            if(!rankAPI.getPlayerRank(player).isHigherEqualsLevel(Rank.DEVELOPER)){
                if(args.length == 0){
                    if(wartung == false){
                        String serverName = "";
                        CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class).getPlayerExecutor(player.getUniqueId()).connect(serverName);
                    }else {
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Die Warzonewartungen sind zurzeit aktiv.");
                        return true;
                    }
                }else{
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"/warzone");
                }
            }else{
                if(args.length == 1 && args[0].equalsIgnoreCase("wartungen")){
                        if(wartung == false){
                            player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Die Warzonewartungen sind zurzeit aus.");
                        }else{
                            player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Die Warzonewartungen sind zurzeit an.");
                        }
                    return true;
                }else if(args.length == 2){
                        if(args[0].equalsIgnoreCase("wartungen") && args[1].equalsIgnoreCase("an")){
                            wartung = true;
                            player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Die Wartungen für die Warzone sind nun aktiv.");
                            Bukkit.broadcastMessage(YupiterIsland.getInstance().getPrefix()+"Die Wartungen für die Warzone sind nun aktiv.");
                        }else if(args[0].equalsIgnoreCase("wartungen") && args[1].equalsIgnoreCase("aus")){
                            wartung = false;
                            player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Die Wartungen für die Warzone sind nun aktiv.");
                            Bukkit.broadcastMessage(YupiterIsland.getInstance().getPrefix()+"Die Wartungen für die Warzone sind nun §7§nnicht mehr §7aktiv.");
                        }
                }
            }
        }else{
            sender.sendMessage("console");
        }
        return false;
    }
}
