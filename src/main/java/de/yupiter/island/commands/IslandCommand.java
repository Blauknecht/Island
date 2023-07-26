package de.yupiter.island.commands;

import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class IslandCommand implements CommandExecutor, TabCompleter {

    private YupiterIsland plugin;

    public IslandCommand(YupiterIsland plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            Island island = plugin.getIslandManager().getIsland(player.getUniqueId());
            if(args.length == 0){
                if(island != null){
                    player.teleport(island.getSpawn());
                    player.sendMessage(YupiterIsland.getInstance().getPrefix() + "Du wurdest zu deiner Insel teleportiert.");
                }else{
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is create §8- §7Erstelle dir eine Insel.");
                }
                return true;
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("delete")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("settings")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("leave")){
                    if(island == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast keine Insel.");
                        return false;
                    }
                    if(island.getOwner().getUniqueId().equals(player.getUniqueId())){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Der Owner kann die Insel nicht verlassen.");
                        return false;
                    }
                    island.leave(player.getUniqueId());
                    return true;
                }
                if(args[0].equalsIgnoreCase("info")){
                    Island locationIsland = plugin.getIslandManager().getIslandAtLocation(player.getLocation());
                    if(locationIsland == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast keine Insel.");
                        return false;
                    }
                    player.sendMessage("§8§m-------------- §bInsel Informationen §8§m--------------");
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Insel-Inhaber: §b"+locationIsland.getOwner().getName());
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Insel wurde erstellt von: §b"+locationIsland.getCreator().getName());
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Insel-Level: §b"+locationIsland.getLevel());
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Insel-Größe: §b"+locationIsland.getIslandSize().getSize()+ "Blöcke §7(§b"+locationIsland.getIslandSize().getName()+"§7)");
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+ "Besuchbar: §a✔");
                    if(!locationIsland.getTrustedPlayers().isEmpty()){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Vertraute Spieler: ");
                        locationIsland.getTrustedPlayers().forEach(uuid -> {
                            OfflinePlayer trustedPlayer = Bukkit.getOfflinePlayer(uuid);
                            player.sendMessage(YupiterIsland.getInstance().getPrefix()+" ➥ §b"+trustedPlayer.getName());
                        });
                    }else{
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Vertraute Spieler: §b-");
                    }
                    if(!locationIsland.getBanPlayers().isEmpty()){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Gebannte Spieler: ");
                        locationIsland.getTrustedPlayers().forEach(uuid -> {
                            OfflinePlayer trustedPlayer = Bukkit.getOfflinePlayer(uuid);
                            player.sendMessage(YupiterIsland.getInstance().getPrefix()+" ➥ §c"+trustedPlayer.getName());
                        });
                    }else{
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Gebannte Spieler: §b-");
                    }
                    player.sendMessage("§8>>§8§m-----------------------------------------------§r§8<<");
                    return true;
                }
                if(args[0].equalsIgnoreCase("setspawn")){
                    if(island == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast keine Insel.");
                        return false;
                    }
                    Island locationIsland = plugin.getIslandManager().getIslandAtLocation(player.getLocation());
                    if(locationIsland == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du bist auf keiner Insel.");
                        return false;
                    }
                    if(locationIsland != island){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du kannst kein Spawn auf einer anderen Insel setzen.");
                        return false;
                    }
                    if(!island.getOwner().getUniqueId().equals(player.getUniqueId())){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du bist nicht der Insel Owner.");
                        return false;
                    }
                    island.setSpawn(player.getLocation());
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast den neuen Inselspawn gesetzt.");
                    return true;
                }
                if(args[0].equalsIgnoreCase("level")){
                    Island locationIsland = plugin.getIslandManager().getIslandAtLocation(player.getLocation());
                    if(locationIsland == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du bist auf keiner Insel.");
                        return false;
                    }
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Insellevel: "+locationIsland.getLevel());
                    return true;
                }
                if(args[0].equalsIgnoreCase("setwarp")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("create")){
                    if(island == null){
                        plugin.getIslandManager().createIsland(player);
                    }else{
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast schon eine Insel.");
                    }
                    return true;
                }
                if(args[0].equalsIgnoreCase("help")){
                    sendHelpSite(player, 1);
                    return true;
                }

            }else if(args.length == 2){
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1].toString());
                if(args[0].equalsIgnoreCase("invite")){
                    if(island == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast keine Insel.");
                        return false;
                    }
                    Island targetPlayerIsland = plugin.getIslandManager().getIsland(target.getUniqueId());
                    if(targetPlayerIsland != null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Der Spieler hat schon eine Insel.");
                        return false;
                    }
                    if(!island.getOwner().getUniqueId().equals(player.getUniqueId())){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du bist nicht der Insel Owner.");
                        return false;
                    }
                    if(island.getTrustedPlayers().contains(target.getUniqueId())){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Dieser Spieler ist schon auf deiner Insel.");
                        return false;
                    }
                    island.invitePlayer(target.getUniqueId());
                    return true;
                }
                if(args[0].equalsIgnoreCase("setowner")){
                    if(island == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast keine Insel.");
                        return false;
                    }
                    if(!island.getOwner().getUniqueId().equals(player.getUniqueId())){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du bist nicht der Insel Owner.");
                        return false;
                    }
                    island.setOwner(target.getUniqueId());
                    return true;
                }
                if(args[0].equalsIgnoreCase("kick")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("ban")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("unban")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("visit")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("warp")){
                    return true;
                }
                if(args[0].equalsIgnoreCase("accept")){
                    Island invitedIsland = plugin.getIslandManager().getInvitedIsland(player, args[1]);
                    if(invitedIsland == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du wurdest nicht zu dieser Insel eingeladen.");
                        return false;
                    }
                    invitedIsland.addPlayer(player.getUniqueId());
                    player.teleport(invitedIsland.getSpawn());
                    return true;
                }
                if(args[0].equalsIgnoreCase("deny")){
                    Island invitedIsland = plugin.getIslandManager().getInvitedIsland(player, args[1]);
                    if(invitedIsland == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du wurdest nicht zu dieser Insel eingeladen.");
                        return false;
                    }
                    invitedIsland.getInvitePlayers().remove(player.getUniqueId());
                    player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast die Einladung §cabgelehnt§7.");
                    if(invitedIsland.getOwner().getPlayer() != null){
                        invitedIsland.getOwner().getPlayer().sendMessage(YupiterIsland.getInstance().getPrefix()+"Der Spieler §b"+player.getName()+" §7hat die Einladung §cabgelehnt§7.");
                    }
                    return true;
                }
                if(args[0].equalsIgnoreCase("remove")){
                    if(island == null){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du hast keine Insel.");
                        return false;
                    }
                    if(!island.getOwner().getUniqueId().equals(player.getUniqueId())){
                        player.sendMessage(YupiterIsland.getInstance().getPrefix()+"Du bist nicht der Insel Owner.");
                        return false;
                    }
                    island.removePlayer(target.getUniqueId());
                    return true;
                }
                if(args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("2")){
                    sendHelpSite(player, 2);
                    return true;
                }
                if(args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("1")){
                    sendHelpSite(player, 1);
                    return true;
                }

            }else{
                sendHelpSite(player, 1);
            }
        }
        return false;
    }
    public void sendHelpSite(Player p, int page){
        if(page == 1){
            p.sendMessage("§8>>§8§m--------------------§b Seite 1. §8§m--------------------§r§8<<");
            p.sendMessage("");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is §8- §7Teleportiere dich zu deiner Insel.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is create §8- §7Erstelle dir eine Insel.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is delete §8- §7Lösche deine Insel.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is settings §8- §7Öffnet die Insel-Benutzeroberfläche.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is leave §8- §7Entziehe dir selber die Rechte auf der Insel.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is info §8- §7Gibt dir eine Information deiner Insel wieder.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is setspawn §8- §7Setze dein Inselspawn");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is level §8- §7Zeigt dir an welches Level (d)eine Insel");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is setwarp §8- §7Setze ein Warp für deine Insel. (§bmax 1.§7)");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is warp §8- §7Öffnet ein Liste der erstellten Warps.");
            p.sendMessage("");
            TextComponent message = new TextComponent(YupiterIsland.getInstance().getPrefix()+"➥ Seite 2.");
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§o§7Klicke hier für Seite 2.").create()));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is help 2"));
            p.spigot().sendMessage(message);
            p.sendMessage("");
            p.sendMessage("§8>>§8§m-----------------------------------------------§r§8<<");
        }else if(page == 2){
            p.sendMessage("§8>>§8§m--------------------§b Seite 2. §8§m--------------------§r§8<<");
            p.sendMessage("");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is invite <Spieler> §8- §7Lade einen Spieler zu deiner Insel ein. (§bmax. 1 Spieler§7)");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is setowner <Spieler> §8- §7Übergebe deine Insel einem anderen Spieler.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is kick <Spieler> §8- §7Werfe Spieler von deiner Insel.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is ban <Spieler> §8- §7Banne ein Spieler von deiner Insel.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is unban <Spieler> §8- §7Entbanne ein Spieler von deiner Insel.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is visit <Spieler> §8- §7Besuche eine Insel eines anderen Spielers.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is warp <Spieler> §8- §7Warpe dich du zu einer Insel eines anderen Spielers.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is accept <Spieler> §8- §7Nehme eine Einladung an, um bei einer anderen Insel mitzuhelfen.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is deny <Spieler> §8- §7Lehnt die Einladung ab, um bei einer anderen Insel mitzuhelfen.");
            p.sendMessage(YupiterIsland.getInstance().getPrefix()+"/is remove <Spieler> §8- §7Entfernt die Rechte eines Spieler für deine Insel.");
            p.sendMessage("");
            TextComponent message = new TextComponent(YupiterIsland.getInstance().getPrefix()+"➥ Seite 1.");
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
        if(args.length == 1){
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
            match.add("spawn");
            String name = args[0].isEmpty() ? "" : args[0];
            match.forEach(matchs -> {
                if (matchs.contains(name)) {
                    tabs.add(matchs);
                }
            });
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("setowner") || args[0].equalsIgnoreCase("visit") || args[0].equalsIgnoreCase("warp") ){
                String name = args[1].isEmpty() ? "" : args[1];
                Bukkit.getOnlinePlayers().forEach(players -> {
                    if (players.getName().contains(name)) {
                        tabs.add(players.getName());
                    }
                });
            }
            if(args[0].equalsIgnoreCase("help")){
                tabs.add("1");
                tabs.add("2");
            }
            if(args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("ban")){
                //tabs für die auf der Insel sind
            }
            if(args[0].equalsIgnoreCase("remove")){
                //tabs für die die getrusted sind
            }
            if(args[0].equalsIgnoreCase("unban")){
                //tabs für die die von der insel gebannt sind
            }
            if(args[0].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("acccept")){
                //tabs für die ein inselinvite an steht
            }
        }
        return tabs;
    }
}
