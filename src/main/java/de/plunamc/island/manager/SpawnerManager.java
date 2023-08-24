package de.plunamc.island.manager;

import de.plunamc.island.PlunaIsland;
import de.plunamc.island.island.Island;
import de.plunamc.island.spawner.CustomBlock;
import de.plunamc.island.spawner.Spawner;
import de.plunamc.island.utils.Serialers;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SpawnerManager {

    @Getter
    private List<Spawner> spawners;
    private int lastSpawnerID = 0;

    public SpawnerManager(){
        this.spawners = new ArrayList<>();
        loadSpawner();
    }

    public void createSpawner(CustomBlock data, Location loc){
        Spawner spawner = new Spawner(lastSpawnerID +1, loc, 1, data);
        spawner.startSpawner();
        this.spawners.add(spawner);
        spawner.save(true);
        this.lastSpawnerID++;
    }

    private void loadSpawner() {
        int spawners = 0;
        try {
            PreparedStatement statement = PlunaIsland.getInstance().getMysql().getConnection().prepareStatement("select * from islandSpawner");
            statement.execute();
            statement.closeOnCompletion();
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                spawners++;

                int id = set.getInt("SpawnerID");

                if(id > this.lastSpawnerID){
                    this.lastSpawnerID = id;
                }

                int spawnerLevel = set.getInt("SpawnerLevel");
                Location spawnerCoords = Serialers.locFromString(set.getString("SpawnerCoords"));
                CustomBlock spawnerType = CustomBlock.getBlockByCustomModelData(set.getInt("SpawnerType"));
                Spawner spawner = new Spawner(id, spawnerCoords, spawnerLevel, spawnerType);
                this.spawners.add(spawner);
                spawner.startSpawner();
            }
            Bukkit.getLogger().info("Loaded " + spawners + " spawners from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
