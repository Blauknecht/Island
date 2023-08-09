package de.plunamc.island.market;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

public enum BenniBaumeister {

    DRIPSTONE_BLOCK(Material.DRIPSTONE_BLOCK, 2400),
    CLAY_BALL(Material.CLAY_BALL, 200),
    BUCKET_LAVA(Material.LAVA_BUCKET, 400),
    BUCKET_WATER(Material.WATER_BUCKET, 200),
    HORN_CORAL_BLOCK(Material.HORN_CORAL_BLOCK, 400),
    FIRE_CORAL_BLOCK(Material.FIRE_CORAL_BLOCK, 400),
    BRAIN_CORAL_BLOCK(Material.BRAIN_CORAL_BLOCK, 400),
    TUBE_CORAL_BLOCK(Material.TUBE_CORAL_BLOCK, 400),
    BUBBLE_CORAL_BLOCK(Material.BUBBLE_CORAL_BLOCK, 400),
    ENDSTONE(Material.END_STONE, 1000),
    MYZEL(Material.MYCELIUM, 1600),
    SNOW_BLOCK(Material.SNOW_BLOCK, 400),
    ICE(Material.ICE, 600),
    MOOS_BLOCK(Material.MOSS_BLOCK, 600),
    SPONGE(Material.SPONGE, 4000),
    BUDDING_AMETHYST(Material.BUDDING_AMETHYST, 3000),
    GRAVEL(Material.GRAVEL, 500),
    RED_SAND(Material.RED_SAND, 700),
    SAND(Material.SAND, 300),
    PODZOL(Material.PODZOL, 1600),
    DIRT(Material.DIRT, 300),
    GRASS_BLOCK(Material.GRASS_BLOCK, 1000),
    DRIPSTONE(Material.POINTED_DRIPSTONE, 500),
    TUFF(Material.TUFF, 300),
    CALCITE(Material.CALCITE, 300),
    DEEPSLATE(Material.DEEPSLATE,  200);

    @Getter
    private Material material;
    @Getter
    private int price;

    BenniBaumeister(Material material, Integer price ) {
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
