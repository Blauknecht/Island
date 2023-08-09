package de.plunamc.island.island;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.plunamc.island.PlunaIsland;
import de.plunamc.island.utils.Serialers;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Island {

    @Getter
    private int id;
    @Getter
    private Location centerLocation;
    @Getter
    private Location spawn;
    @Getter
    private OfflinePlayer owner;
    @Getter
    private OfflinePlayer creator;
    @Getter
    @Setter
    private Integer level;
    @Getter
    @Setter
    private Integer exp;
    @Getter
    @Setter
    private Integer mobDropLevel;
    @Getter
    @Setter
    private Integer erzDropLevel;
    @Getter
    @Setter
    private Integer xpDropLevel;
    @Getter
    @Setter
    private Integer farmingDropLevel;

    @Getter
    private List<OfflinePlayer> banPlayers;

    @Getter
    private List<OfflinePlayer> trustedPlayers;

    @Getter
    private List<UUID> invitePlayers;

    @Getter
    @Setter
    private IslandSize islandSize;

    @Getter
    private WorldBorder border;

    @Getter
    @Setter
    private int streams;

    @Getter
    @Setter
    private boolean checkDelete;

    private int task;

    public Island(int id, Location spawn, Location centerLocation, OfflinePlayer owner, OfflinePlayer creator, int streams, List<OfflinePlayer> trustedPlayers, List<OfflinePlayer> banPlayers, int level, int exp) {
        this.id = id;
        this.spawn = spawn;
        this.centerLocation = centerLocation;
        this.owner = owner;
        this.creator = creator;
        this.streams = streams;

        this.level = level;
        this.exp = exp;
        this.mobDropLevel = 1;
        this.erzDropLevel = 1;
        this.xpDropLevel = 1;
        this.farmingDropLevel = 1;
        this.checkDelete = false;

        this.islandSize = IslandSize.getSizeByLevel(level);

        this.banPlayers = banPlayers;
        this.trustedPlayers = trustedPlayers;
        this.invitePlayers = new ArrayList<>();

        this.border = Bukkit.createWorldBorder();
        this.border.setCenter(this.centerLocation);
        this.border.setSize(this.islandSize.getSize());
        this.border.setDamageAmount(0);
        this.border.setWarningDistance(3);
    }

    public void updateBorder() {
        task = Bukkit.getScheduler().runTaskTimer(PlunaIsland.getInstance(), new Runnable() {
            double i = border.getSize();

            @Override
            public void run() {
                if (i >= (islandSize.getSize() - 0.1)) {
                    Bukkit.getScheduler().cancelTask(task);
                } else {
                    i += 0.05;
                    border.setSize(i);
                }
            }
        }, 3, 3).getTaskId();
    }

    public void updateBorderAdmin() {
        border.setSize(islandSize.getSize());
    }


    public void invitePlayer(UUID invitePlayerUUID) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(invitePlayerUUID);
        if (!this.invitePlayers.contains(invitePlayerUUID)) {
            this.invitePlayers.add(invitePlayerUUID);
            if (player.getPlayer() != null) {
                player.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Du wurdest auf die Insel von §b" + owner.getName() + " §7eingeladen.");
            }
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast §b" + player.getName() + " §7zu deiner Insel eingeladen.");
            }
        } else {
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "§cDu hast diesen Spieler schon eingeladen.");
            }
        }
    }

    public void addPlayer(UUID addPlayerUUID) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(addPlayerUUID);
        if (!this.trustedPlayers.contains(addPlayerUUID)) {
            this.trustedPlayers.add(player);
            if (this.getInvitePlayers().contains(addPlayerUUID)) {
                this.getInvitePlayers().remove(addPlayerUUID);
            }
            this.save(false);
            if (player.getPlayer() != null) {
                player.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Du bist der Insel von §b" + owner.getName() + " §abeigetreten§7.");
            }
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler §b" + player.getName() + " §7ist deiner Insel §abeigetreten§7.");
            }
        } else {
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "§cDieser Spieler ist schon auf deiner Insel.");
            }
        }
    }

    public void removePlayer(UUID removePlayerUUID) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(removePlayerUUID);
        if (this.trustedPlayers.contains(removePlayerUUID)) {
            this.trustedPlayers.remove(removePlayerUUID);
            this.save(false);
            if (player.getPlayer() != null) {
                player.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Du wurdest von der Insel von §b" + owner.getName() + " §7entfernt.");
            }
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler §b" + player.getName() + " §7wurde von deiner Insel entfernt.");
            }
        } else {
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "§cDieser Spieler ist nicht auf deiner Insel.");
            }
        }
    }

    public void leave(UUID playerUUID) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        if (this.trustedPlayers.contains(playerUUID)) {
            this.trustedPlayers.remove(playerUUID);
            this.save(false);
            if (player.getPlayer() != null) {
                player.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast die Insel von §b" + owner.getName() + " §7verlassen.");
            }
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Der Spieler §b" + player.getName() + " §7hat deine Insel §cverlassen.");
            }
        }
    }

    public void setSpawn(Location location) {
        this.spawn = location;
        this.save(false);
    }

    public void setOwner(UUID newOwnerUUID) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(newOwnerUUID);
        if (!owner.getUniqueId().equals(newOwnerUUID)) {
            if (player.getPlayer() != null) {
                player.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Du wurdest der neue Owner von der Insel von §b" + owner.getName());
            }
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "Du hast §b" + player.getName() + " §7als neuen Owner gesetzt!.");
            }
            setOwner(newOwnerUUID);
            this.save(false);
        } else {
            if (owner.getPlayer() != null) {
                owner.getPlayer().sendMessage(PlunaIsland.getInstance().getPrefix() + "§cDu kannst dich selber nicht als Owner setzen.");
            }
        }
    }

    public boolean isOnIsland(Player player) {
        return (this.getOwner().getUniqueId().equals(player.getUniqueId())) || (this.trustedPlayers.contains(player.getUniqueId()));
    }

    public String getTrustedPlayerString() {
        if (this.trustedPlayers.isEmpty()) {
            return "Keine";
        }
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (OfflinePlayer trustedPlayer : this.getTrustedPlayers()) {
            if (this.trustedPlayers.size() == i) {
                builder.append("§b").append(trustedPlayer.getName());
            } else {
                builder.append("§b").append(trustedPlayer.getName()).append(", ");
            }
            ++i;
        }
        return builder.toString();
    }
    public Material getCobbleStoneRandomBlock() {
        Random random = new Random();
        CobbleStoneBlocks stoneBlocks = CobbleStoneBlocks.getBlockRate(this.islandSize);

        Material material = stoneBlocks.getMaterials().get(random.nextInt(stoneBlocks.getMaterials().size()));
        return material;
    }

    public Material getBasaltStoneRandomBlock() {
        Random random = new Random();
        BasaltStoneBlocks basaltStoneBlocks = BasaltStoneBlocks.getBlockRate(this.islandSize);

        Material material = basaltStoneBlocks.getMaterials().get(random.nextInt(basaltStoneBlocks.getMaterials().size()));
        return material;
    }
    public void save(boolean insert){
        Connection connection = PlunaIsland.getInstance().getMysql().getConnection();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                if(insert){
                    try {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO islands(`IslandID`, `Owner`, `Creator`, `Created`, `Streams`, `Server`, `IslandCenter`, `IslandSpawn`, `TrustedPlayers`, `IslandLevel`, `IslandExp`,`IslandMobDropLevel`, `IslandErzDropLevel`, `IslandFarmingDropLevel`, `IslandXpDropLevel`, `IslandBans`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                        statement.setObject(1, id);
                        statement.setObject(2, owner.getUniqueId().toString());
                        statement.setObject(3, creator.getUniqueId().toString());
                        statement.setObject(4, System.currentTimeMillis());
                        statement.setObject(5, streams);
                        statement.setObject(6, CloudNetDriver.getInstance().getComponentName());
                        statement.setObject(7, Serialers.toString(centerLocation));
                        statement.setObject(8, Serialers.toString(spawn));
                        if(!trustedPlayers.isEmpty()){
                            statement.setObject(9, Serialers.ListToString(trustedPlayers));
                        }else{
                            statement.setObject(9, null);
                        }
                        statement.setObject(10, level);
                        statement.setObject(11, exp);
                        statement.setObject(12, mobDropLevel);
                        statement.setObject(13, erzDropLevel);
                        statement.setObject(14, farmingDropLevel);
                        statement.setObject(15, xpDropLevel);
                        if(!banPlayers.isEmpty()){
                            statement.setObject(16, Serialers.ListToString(banPlayers));
                        }else{
                            statement.setObject(16, null);
                        }
                        statement.execute();
                        statement.closeOnCompletion();
                        Bukkit.getLogger().info("Island with the id "+id+" is saved!");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    try {
                        PreparedStatement statement = connection.prepareStatement("UPDATE islands SET `Owner` = ?, `Streams` = ?, `IslandSpawn` = ?, `TrustedPlayers` = ?, `IslandLevel` = ?,`IslandExp` = ?, `IslandMobDropLevel` = ?, `IslandErzDropLevel` = ?, `IslandFarmingDropLevel` = ?, `IslandXPDropLevel` = ?, `IslandBans` = ? WHERE Creator = ?;");
                        statement.setObject(1, owner.getUniqueId().toString());
                        statement.setObject(2, streams);
                        statement.setObject(3, Serialers.toString(spawn));
                        if(!trustedPlayers.isEmpty()){
                            statement.setObject(4, Serialers.ListToString(trustedPlayers));
                        }else{
                            statement.setObject(4, null);
                        }
                        statement.setObject(5, level);
                        statement.setObject(6, level);
                        statement.setObject(7, mobDropLevel);
                        statement.setObject(8, erzDropLevel);
                        statement.setObject(9, farmingDropLevel);
                        statement.setObject(10, xpDropLevel);
                        if(!banPlayers.isEmpty()){
                            statement.setObject(11, Serialers.ListToString(banPlayers));
                        }else{
                            statement.setObject(11, null);
                        }
                        statement.setObject(12, creator.getUniqueId().toString());
                        statement.execute();
                        statement.closeOnCompletion();
                        System.out.println("Island with the id "+id+" is updated!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
