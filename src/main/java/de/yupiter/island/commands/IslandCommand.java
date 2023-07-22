package de.yupiter.island.commands;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IslandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0){
            WorldCreator worldCreator = new WorldCreator("test");
            worldCreator.type(WorldType.FLAT);
            worldCreator.generateStructures(false);
        }
        return false;
    }
}
