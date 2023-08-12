package de.plunamc.island;

import de.plunaapi.PlunaAPI;
import de.plunaapi.playerdata.RankAPI;
import de.plunamc.island.commands.*;
import de.plunamc.island.files.BlockFile;
import de.plunamc.island.listener.*;
import de.plunamc.island.manager.IslandManager;
import de.plunamc.island.manager.PlayerData;
import de.plunamc.island.manager.PlayerManager;
import de.plunamc.island.manager.SchematicManager;
import de.plunamc.island.utils.MySQL;
import de.plunamc.island.utils.ScoreboardUpdater;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class PlunaIsland extends JavaPlugin {

    @Getter
    private static PlunaIsland instance;
    @Getter
    private String InventoryTitle = "§f\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012\uE012";

    @Getter
    private String prefix = "§f\uE001 §7";

    @Getter
    private MySQL mysql;

    @Getter
    private ArrayList<Player> build;

    @Getter
    private SchematicManager schematicManager;

    @Getter
    private IslandManager islandManager;

    @Getter
    private PlayerManager playerManager;

    @Getter
    private RankAPI rankAPI;

    @Override
    public void onEnable() {
        instance = this;

        this.rankAPI = new RankAPI();
        this.build = new ArrayList<>();

        getCommand("is").setExecutor(new IslandCommand(this));
        getCommand("bits").setExecutor(new BitCommand());
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("warzone").setExecutor(new WarzoneCommand());
        getCommand("updateborder").setExecutor(new BorderCommand(this));

        this.mysql = new MySQL("212.227.225.63", "root", "nwTrgv7yTaQ7Vgf2!", "skyblock");
        this.mysql.connect();
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islands` ( `IslandID` INT NOT NULL , `Owner` VARCHAR(100) NOT NULL ,`Creator` VARCHAR(100) NOT NULL , `Created` BIGINT NOT NULL ,`Streams` BIGINT NOT NULL , `Server` VARCHAR(100) NOT NULL , `IslandCenter` VARCHAR(100) NOT NULL , `IslandSpawn` VARCHAR(100) NOT NULL , `TrustedPlayers` LONGTEXT NULL DEFAULT NULL , `IslandLevel` INT NOT NULL , `IslandExp` INT NOT NULL ,`IslandMobDropLevel` INT NOT NULL , `IslandErzDropLevel` INT NOT NULL , `IslandFarmingDropLevel` INT NOT NULL , `IslandXpDropLevel` INT NOT NULL , `IslandBans` LONGTEXT NULL DEFAULT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islandsMoney` (`Player` VARCHAR(100) NOT NULL , `Money` BIGINT NOT NULL , PRIMARY KEY (`Player`)) ENGINE = InnoDB;");
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islandsWarps` (`IslandID` INT NOT NULL , `WarpName` VARCHAR(100) NOT NULL , `WarpCords` VARCHAR(100) NOT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islandSpawner` (`IslandID` INT NOT NULL , `SpawnerName` VARCHAR(100) NOT NULL , `SpawnerCoords` VARCHAR(100) NOT NULL , `SpawnerLevel` INT NOT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islandsDeletes` (`IslandID` INT NOT NULL , `Owner` VARCHAR(100) NOT NULL , `TrustedPlayers` LONGTEXT NOT NULL , `Timestamp` BIGINT NOT NULL , `Location` LONGTEXT NOT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");

        this.schematicManager = new SchematicManager();
        this.islandManager = new IslandManager();
        this.playerManager = new PlayerManager();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new NPCRigtClickEvent(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new FoodLevelChangeListener(), this);
        pluginManager.registerEvents(new EntitySpawnListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);

        new BlockFile();

        PlunaAPI.instance.getClient().getPacketGetters().add(new ScoreboardUpdater());
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§f" + getPlayerManager().getPlayerData(all).getMoney() + " §f\uE041"));
                }
            }
        }, 5, 5);
        for (World worlds : Bukkit.getWorlds()) {
            if (worlds.getName().equals("spawn")) {
                worlds.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
                worlds.setGameRule(GameRule.DO_FIRE_TICK, false);
                worlds.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
                worlds.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                worlds.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                worlds.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                worlds.setClearWeatherDuration(Integer.MAX_VALUE);
                worlds.getEntities().forEach(Entity::remove);
            }
            worlds.setGameRule(GameRule.KEEP_INVENTORY, true);
            worlds.setTime(6000L);
        }
    }

    @Override
    public void onDisable() {
        this.mysql.close();
        super.onDisable();
    }


}