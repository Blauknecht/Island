package de.plunamc.island.market;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

public enum ZyrusZuchter {

    EGG(Material.EGG, 1000),
    COW_SPAWN_EGG(Material.COW_SPAWN_EGG,  2000),
    PIG_SPAWN_EGG(Material.PIG_SPAWN_EGG, 2000),
    SHEEP_SPAWN_EGG(Material.SHEEP_SPAWN_EGG, 2000),
    BEEHIVE(Material.BEEHIVE, 10000),
    SPAWNER(Material.SPAWNER, 60000),
    AXOLOTL_SPAWN_EGG(Material.AXOLOTL_SPAWN_EGG, 2000),
    CAMEL_SPAWN_EGG(Material.CAMEL_SPAWN_EGG, 2000),
    CHICKEN_SPAWN_EGG(Material.CHICKEN_SPAWN_EGG, 2000),
    CREEPER_SPAWN_EGG(Material.CREEPER_SPAWN_EGG, 3000),
    DROWNED_SPAWN_EGG(Material.DROWNED_SPAWN_EGG, 3000),
    ENDERMAN_SPAWN_EGG(Material.ENDERMAN_SPAWN_EGG, 3000),
    FROG_SPAWN_EGG(Material.FROG_SPAWN_EGG, 2000),
    GLOW_SQUID_SPAWN_EGG(Material.GLOW_SQUID_SPAWN_EGG, 2000),
    IRON_GOLEM_SPAWN_EGG(Material.IRON_GOLEM_SPAWN_EGG, 30000),
    MOOSHROOM_SPAWN_EGG(Material.MOOSHROOM_SPAWN_EGG, 5000),
    RABBIT_SPAWN_EGG(Material.RABBIT_SPAWN_EGG, 2000),
    SKELETON_SPAWN_EGG(Material.SKELETON_SPAWN_EGG, 3000),
    SLIME_SPAWN_EGG(Material.SLIME_SPAWN_EGG, 3000),
    SPIDER_SPAWN_EGG(Material.SPIDER_SPAWN_EGG, 3000),
    SQUID_SPAWN_EGG(Material.SQUID_SPAWN_EGG, 2000),
    TURTLE_SPAWN_EGG(Material.TURTLE_SPAWN_EGG,2000 ),
    VILLAGER_SPAWN_EGG(Material.VILLAGER_SPAWN_EGG, 30000),
    WITCH_SPAWN_EGG(Material.WITCH_SPAWN_EGG, 3500),
    ZOMBIE_SPAWN_EGG(Material.ZOMBIE_SPAWN_EGG, 3000);

    @Getter
    private Material material;
    @Getter
    private int price;


    ZyrusZuchter(Material material, Integer price ) {
        this.material = material;
        this.price = price;
    }
    public Material getMaterial() {
        return material;
    }
    public int getPrice() {
        return price;
    }
}
