package de.plunamc.island.market;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

public enum HildaHolle {

    CRIMSON_NILIUM(Material.CRIMSON_NYLIUM, 2400),
    WARPED_NILIUM(Material.WARPED_NYLIUM, 2400),
    NETHER_QUARZ_ORE(Material.NETHER_QUARTZ_ORE,  800),
    WEEPING_VINES(Material.WEEPING_VINES, 200),
    TWISTING_VINES(Material.TWISTING_VINES, 200),
    NETHERRACK(Material.NETHERRACK, 1000),
    SOULSAND(Material.SOUL_SAND, 2000),
    SOULSOIL(Material.SOUL_SOIL, 2000),
    NETHER_QUARZ(Material.QUARTZ, 300),
    GLOWSTONE_DUST(Material.GLOWSTONE_DUST, 700),
    GHAST_TEAR(Material.GHAST_TEAR, 100),
    NETHER_WARD(Material.NETHER_WART, 1000),
    DRAGON_BRREAH(Material.DRAGON_BREATH, 10000),
    CRYING_OBSIDIAN(Material.CRYING_OBSIDIAN, 10000),
    BLACKSTONE(Material.BLACKSTONE, 2600),
    GILDED_BLACKSTONE(Material.GILDED_BLACKSTONE,  9000);

    @Getter
    private Material material;
    @Getter
    private int price;


    HildaHolle(Material material, Integer price ) {
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
