package de.plunamc.island.manager;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.utils.Serialers;
import de.plunamc.island.warps.Warps;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WarpManager {
    @Getter
    private List<Warps> warps;
    private int lastWarpsID = 0;

    public WarpManager() {
        this.warps = new ArrayList<>();
        loadWarps();
    }

    public void createWarp(Location warpLocation, String warpName, OfflinePlayer owner) {
        Warps warp = new Warps(lastWarpsID + 1, warpLocation, warpName, owner);
        this.warps.add(warp);
        warp.save(true);
        this.lastWarpsID++;
    }

    public Warps getWarps(UUID owner){
        return warps.stream().filter(warps1 -> warps1.getOwner().getUniqueId().equals(owner)).findFirst().orElse(null);
    }

    public Warps getWarpsByName(String name){
        return warps.stream().filter(warps1 -> warps1.getWarpName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private void loadWarps() {
        int warps = 0;
        try {
            PreparedStatement statement = PlunaIsland.getInstance().getMysql().getConnection().prepareStatement("select * from islandsWarps");
            statement.execute();
            statement.closeOnCompletion();
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                warps++;

                int id = set.getInt("WarpID");

                if (id > this.lastWarpsID) {
                    this.lastWarpsID = id;
                }

                OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(set.getString("Owner")));
                String warpName = set.getString("WarpName");
                Location warpLocation = Serialers.locFromString(set.getString("WarpCoords"));
                 Warps warp = new Warps(id, warpLocation, warpName, owner);
                 this.warps.add(warp);
            }
            Bukkit.getLogger().info("Loaded " + warps + " Warps from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
