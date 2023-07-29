package de.yupiter.island;

import de.yupiter.island.commands.IslandCommand;
import de.yupiter.island.commands.SpawnCommand;
import de.yupiter.island.commands.WarzoneCommand;
import de.yupiter.island.listener.*;
import de.yupiter.island.manager.IslandManager;
import de.yupiter.island.manager.PlayerManager;
import de.yupiter.island.manager.SchematicManager;
import de.yupiter.island.utils.MySQL;
import de.yupiterapi.playerdata.RankAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class YupiterIsland extends JavaPlugin {

    @Getter
    private static YupiterIsland instance;

    @Getter
    private String prefix = "§f\uE001 §7";

    @Getter
    private MySQL mysql;

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

        getCommand("is").setExecutor(new IslandCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("warzone").setExecutor(new WarzoneCommand());

        this.mysql = new MySQL("localhost", "root", "UcErTjhJTW82EPSJ", "skyblock");
        this.mysql.connect();
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islands` ( `IslandID` INT NOT NULL , `Owner` VARCHAR(100) NOT NULL ,`Creator` VARCHAR(100) NOT NULL , `Created` BIGINT NOT NULL ,`Streams` BIGINT NOT NULL , `Server` VARCHAR(100) NOT NULL , `IslandCenter` VARCHAR(100) NOT NULL , `IslandSpawn` VARCHAR(100) NOT NULL , `TrustedPlayers` LONGTEXT NULL DEFAULT NULL , `IslandLevel` INT NOT NULL , `IslandMobDropLevel` INT NOT NULL , `IslandErzDropLevel` INT NOT NULL , `IslandFarmingDropLevel` INT NOT NULL , `IslandXpDropLevel` INT NOT NULL , `IslandBans` LONGTEXT NULL DEFAULT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islandsWarps` (`IslandID` INT NOT NULL , `WarpName` VARCHAR(100) NOT NULL , `WarpCords` VARCHAR(100) NOT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islandSpawner` (`IslandID` INT NOT NULL , `SpawnerName` VARCHAR(100) NOT NULL , `SpawnerCoords` VARCHAR(100) NOT NULL , `SpawnerLevel` INT NOT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");
        this.mysql.update("CREATE TABLE IF NOT EXISTS `islandsDeletes` (`IslandID` INT NOT NULL , `Owner` VARCHAR(100) NOT NULL , `TrustedPlayers` LONGTEXT NOT NULL , `Timestamp` BIGINT NOT NULL , `Location` LONGTEXT NOT NULL , PRIMARY KEY (`IslandID`)) ENGINE = InnoDB;");

        this.schematicManager = new SchematicManager();
        this.islandManager = new IslandManager();
        this.playerManager = new PlayerManager();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
        pluginManager.registerEvents(new PlayerPickupItemListener(), this);
        pluginManager.registerEvents(new PlayerDropItemListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
    }

    @Override
    public void onDisable() {
        this.mysql.close();
        super.onDisable();
    }


}