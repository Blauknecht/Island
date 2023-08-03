package de.yupiter.island.manager;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.yupiter.island.YupiterIsland;
import de.yupiter.island.island.Island;
import de.yupiter.island.utils.Serialers;
import de.yupiterapi.worlds.VoidGenerator;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class IslandManager {

    private List<Island> islands;

    @Getter
    private Location spawn;

    private int lastIslandID = 0;

    public IslandManager() {
        this.islands = new ArrayList<>();
        this.createWorld();
        this.loadIslands();
        spawn = new Location(Bukkit.getWorld("islands"), 0, 102, 0);
    }

    public void createIsland(Player player) {
        Island island = getIsland(player.getUniqueId());
        if (island == null) {
            int id = this.lastIslandID + 1;
            Location location = this.getCenterFromId(id);
            Location spawn = new Location(Bukkit.getWorld("islands"), location.getBlockX(), 100, location.getBlockZ() + 2);

            island = new Island(id, spawn, location, player, player, 0, new ArrayList<>(), new ArrayList<>(), 1);
            this.islands.add(island);

            player.sendMessage(YupiterIsland.getInstance().getPrefix() + "Deine Insel wird erstellt...");

            YupiterIsland.getInstance().getSchematicManager().load("island", location.getX(), location.getY(), location.getZ());

            player.teleport(spawn);
            player.sendMessage(YupiterIsland.getInstance().getPrefix() + "Du wurdest zu deiner Insel teleportiert!");
            player.setWorldBorder(island.getBorder());

            island.save(true);
        }
    }
    public void deleteIsland(Island island){
        Connection connection = YupiterIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO islandsDeletes(`IslandID`, `Owner`, `TrustedPlayers`, `Timestamp`, `Location`) VALUES (?,?,?,?,?);");
                    statement.setObject(1, island.getId());
                    statement.setObject(2, island.getOwner().getUniqueId().toString());
                    if(!island.getTrustedPlayers().isEmpty()){
                        statement.setObject(3, Serialers.ListToString(island.getTrustedPlayers()));
                    }else{
                        statement.setObject(3, null);
                    }
                    statement.setObject(4, System.currentTimeMillis());
                    statement.setObject(5, Serialers.toString(island.getCenterLocation()));
                    statement.execute();
                    statement.closeOnCompletion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM islands WHERE Creator = ?;");
                    statement.setObject(1, island.getCreator());
                    statement.execute();
                    statement.closeOnCompletion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.islands.remove(island);
        Bukkit.getLogger().info("Island with the id "+island.getId()+" was deleted!");
    }

    private Location getCenterFromId(int id) {
        int x = (id * 1000);
        int z = (int) (Math.floor(x / 384500) * 1000);

        return new Location(Bukkit.getWorld("islands"), x, 100, z);
    }

    private void createWorld() {
        if (Bukkit.getWorld("islands") == null) {
            WorldCreator worldCreator = new WorldCreator("islands");
            worldCreator.type(WorldType.FLAT);
            worldCreator.generateStructures(false);
            worldCreator.generator(new VoidGenerator());
            worldCreator.createWorld();

            YupiterIsland.getInstance().getSchematicManager().load("spawn", 0, 100, 0);
        }
    }

    public Island getIsland(UUID player){
        return this.islands.stream().filter(island -> (island.getOwner().getUniqueId().equals(player) || (island.getTrustedPlayers().contains(player)))).findAny().orElse(null);
    }
    public Island getInvitedIsland(Player player, String name){
        return this.islands.stream().filter(island -> (island.getInvitePlayers().contains(player.getUniqueId())) && (island.getOwner().getName().equals(name))).findAny().orElse(null);
    }
    public List<Island> getInvitedIslands(Player player){
        return this.islands.stream().filter(island -> island.getInvitePlayers().contains(player.getUniqueId())).collect(Collectors.toList());
    }
    public Island getIslandAtLocation(Location location){
        return this.islands.stream().filter(island -> island.getCenterLocation().distance(location) < island.getIslandSize().getSize()).findFirst().orElse(null);
    }
    public List<Player> getPlayersOnIsland(Island island){
        return Bukkit.getOnlinePlayers().stream().filter(player -> player.getLocation().distance(island.getCenterLocation()) < island.getIslandSize().getSize()).collect(Collectors.toList());
    }

    private void loadIslands() {
        CompletableFuture.runAsync(() -> {
            int islands = 0;
            try {
                PreparedStatement statement = YupiterIsland.getInstance().getMysql().getConnection().prepareStatement("select * from islands");
                statement.execute();
                statement.closeOnCompletion();
                ResultSet set = statement.getResultSet();
                while (set.next()) {
                    islands++;

                    int id = set.getInt("IslandID");

                    if(id > this.lastIslandID){
                        this.lastIslandID = id;
                    }

                    int streams = set.getInt("Streams");
                    Location spawn = Serialers.locFromString(set.getString("IslandSpawn"));
                    Location centerLocation = Serialers.locFromString(set.getString("IslandCenter"));
                    OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(set.getString("Owner")));
                    OfflinePlayer creator = Bukkit.getOfflinePlayer(UUID.fromString(set.getString("Creator")));
                    List<OfflinePlayer> trustedPlayers = Serialers.StringtoList(set.getString("TrustedPlayers"));
                    List<OfflinePlayer> bannedPlayers = Serialers.StringtoList(set.getString("IslandBans"));
                    int level = set.getInt("IslandLevel");

                    if(trustedPlayers == null){
                        trustedPlayers = new ArrayList<>();
                    }
                    if(bannedPlayers == null){
                        bannedPlayers = new ArrayList<>();
                    }

                    Island island = new Island(id, spawn, centerLocation, owner, creator, streams, trustedPlayers, bannedPlayers, level);
                    this.islands.add(island);

                    int mobDropLevel = set.getInt("IslandMobDropLevel");
                    int erzDropLevel = set.getInt("IslandErzDropLevel");
                    int xpDropLevel = set.getInt("IslandXpDropLevel");
                    int farmingDropLevel = set.getInt("IslandFarmingDropLevel");

                    island.setMobDropLevel(mobDropLevel);
                    island.setErzDropLevel(erzDropLevel);
                    island.setXpDropLevel(xpDropLevel);
                    island.setFarmingDropLevel(farmingDropLevel);
                }
                Bukkit.getLogger().info("Loaded " + islands + " islands from database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
