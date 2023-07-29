package de.yupiter.island.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Serialers {

    public static String toString(Location location){
        return location.getWorld().getName()
                + ";" + location.getX()
                + ";" + location.getY()
                + ";" + location.getZ()
                + ";" + location.getYaw()
                + ";" + location.getPitch();
    }

    public static Location locFromString(String str){
        if(str == null || str.isEmpty()){
            return null;
        }

        String[] strar = str.split(";");
        Location newLoc = new Location(Bukkit.getWorld(strar[0]), Double.valueOf(strar[1]),
                Double.valueOf(strar[2]),
                Double.valueOf(strar[3]),
                Float.valueOf(strar[4]),
                Float.valueOf(strar[5]));
        return newLoc.clone();
    }

    public static String ListToString(List<OfflinePlayer> list){
        StringBuilder builder = new StringBuilder();
        list.forEach(o -> {
            builder.append(o.getUniqueId().toString()).append("@");
        });
        return builder.toString();
    }
    public static List<OfflinePlayer> StringtoList(String string){
        if(string == null || string.isEmpty()){
            return null;
        }
        List<OfflinePlayer> list = new ArrayList<>();
        for (String s : string.split("@")) {
            list.add(Bukkit.getOfflinePlayer(UUID.fromString(s)));
        }
        return list;
    }
}
