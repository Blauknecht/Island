package de.plunamc.island.warps;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.spawner.CustomBlock;
import de.plunamc.island.utils.Itemmanager;
import de.plunamc.island.utils.Serialers;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class Warps {

    @Getter
    private int id;
    @Getter
    private Location warpLocation;
    @Getter
    private String warpName;
    @Getter
    private OfflinePlayer owner;

    public Warps(int id, Location warpLocation, String warpName, OfflinePlayer owner) {
        this.id = id;
        this.warpLocation = warpLocation;
        this.warpName = warpName;
        this.owner = owner;
    }

    public void delete() {
        Connection connection = PlunaIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM islandsWarps WHERE `WarpID` = ?;");
                    statement.setObject(1, id);
                    statement.execute();
                    statement.closeOnCompletion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void save(boolean insert) {
        Connection connection = PlunaIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                if (insert) {
                    try {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO islandsWarps(`Owner`, `WarpName` , `WarpCoords`) VALUES (?,?,?);");
                        statement.setObject(1, owner.getUniqueId().toString());
                        statement.setObject(2, warpName);
                        statement.setObject(3, Serialers.toString(warpLocation));
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Spawner with the id " + id + " is saved!");
                    } catch (SQLException e) {
                        throw new RuntimeException();
                    }
                } else {
                    try {
                        PreparedStatement statement = connection.prepareStatement("UPDATE islandsWarps SET `Owner` = ?, `WarpName` = ?, `WarpCoords` = ? WHERE WarpID = ?;");
                        statement.setObject(1, owner.getUniqueId().toString());
                        statement.setObject(2, warpName);
                        statement.setObject(3, Serialers.toString(warpLocation));
                        statement.setObject(4, id);
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Spawner  with the id " + id + " is updated!");
                    } catch (SQLException e) {
                        throw new RuntimeException();
                    }
                }
            }
        });
    }
}
