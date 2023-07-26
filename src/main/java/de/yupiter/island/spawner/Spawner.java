package de.yupiter.island.spawner;

import de.yupiter.island.YupiterIsland;
import de.yupiter.island.utils.Serialers;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class Spawner {

    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private Location spawnercoords;
    @Getter
    private int spawnerlevel;

    public Spawner(int id, String name,Location spawnercoords, int spawnerlevel){
        this.id = id;
        this.name = name;
        this.spawnercoords = spawnercoords;
        this.spawnerlevel = spawnerlevel;

    }
    public void createSpawner(int id){
        
    }
    public void save(boolean insert){
        Connection connection = YupiterIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                if(insert){
                    try {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO islandSpawner(`IslandID`, `SpawnerName`, `SpawnerCoords`, `SpawnerLevel`) VALUES (?,?,?,?);");
                        statement.setObject(1, id);
                        statement.setObject(2, name);
                        statement.setObject(3, Serialers.toString(spawnercoords));
                        statement.setObject(4, spawnerlevel);
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Spawner "+name+" with the id "+id+" is saved!");
                    }catch (SQLException e){
                        throw new RuntimeException();
                    }
                }else{
                    try {
                        PreparedStatement statement = connection.prepareStatement("UPDATE islandSpawner SET `IslandID` = ?, `SpawnerName` = ?, `SpawnerCoords` = ?, `SpawnerLevel` = ? WHERE IslandID?;");
                        statement.setObject(1, id);
                        statement.setObject(2, name);
                        statement.setObject(3, Serialers.toString(spawnercoords));
                        statement.setObject(4, spawnerlevel);
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Spawner "+name+" with the id "+id+" is updated!");
                    }catch (SQLException e){
                        throw new RuntimeException();
                    }
                }
            }
        });
    }

}
