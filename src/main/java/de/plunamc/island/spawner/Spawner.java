package de.plunamc.island.spawner;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.utils.Itemmanager;
import de.plunamc.island.utils.Serialers;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class Spawner {

    @Getter
    private int id;
    @Getter
    private Location spawnercoords;
    @Getter
    private int spawnerLevel;
    @Getter
    private CustomBlock spawnerType;
    private int spawnerScheduler;

    public Spawner(int id, Location spawnercoords, int spawnerlevel, CustomBlock type) {
        this.id = id;
        this.spawnercoords = spawnercoords;
        this.spawnerLevel = spawnerlevel;
        this.spawnerType = type;
    }
    public void startSpawner(){
        int amount = this.getSpawnerType().getSpawnAmount();
        int spawnrate = this.getSpawnerType().getSpawnRate();
        Material material = this.getSpawnerType().getSpawnMaterial();
        Location loc = this.getSpawnercoords();
    spawnerScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(PlunaIsland.getInstance(), new Runnable() {
            @Override
            public void run() {
                loc.getWorld().dropItemNaturally(loc, Itemmanager.createSpawnerItem(material, amount));
            }
        }, spawnrate, spawnrate);

    }

    public void delete() {
        Connection connection = PlunaIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM islandSpawner WHERE `SpawnerID` = ?;");
                    statement.setObject(1, id);
                    statement.execute();
                    statement.closeOnCompletion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Bukkit.getScheduler().cancelTask(spawnerScheduler);
        Bukkit.getLogger().info("IslandSpawner with the id " + id + " was deleted!");
    }

    public void save(boolean insert) {
        Connection connection = PlunaIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                if (insert) {
                    try {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO islandSpawner(`SpawnerCoords`, `SpawnerLevel` , `SpawnerType`) VALUES (?,?,?);");
                        statement.setObject(1, Serialers.toString(spawnercoords));
                        statement.setObject(2, spawnerLevel);
                        statement.setObject(3, spawnerType.getCustomModelData());
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Spawner with the id " + id + " is saved!");
                    } catch (SQLException e) {
                        throw new RuntimeException();
                    }
                } else {
                    try {
                        PreparedStatement statement = connection.prepareStatement("UPDATE islandSpawner SET `SpawnerCoords` = ?, `SpawnerLevel` = ?, `SpawnerType` = ? WHERE SpawnerID = ?;");
                        statement.setObject(1, Serialers.toString(spawnercoords));
                        statement.setObject(2, spawnerLevel);
                        statement.setObject(3, spawnerType.getCustomModelData());
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
