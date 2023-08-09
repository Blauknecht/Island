package de.plunamc.island.market;

import de.plunamc.island.island.IslandSize;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum GretaGartner {

    SPRUCE_SAPLING(Material.SPRUCE_SAPLING, 200),
    BIRCH_SAPLING(Material.BIRCH_SAPLING, 200),
    JUNGLE_SAPLING(Material.JUNGLE_SAPLING, 200),
    ACACIA_SAPLING(Material.ACACIA_SAPLING, 200),
    CHERRY_SAPLING(Material.CHERRY_SAPLING, 200),
    DARK_OAK_SAPLING(Material.DARK_OAK_SAPLING, 200),
    MANGROVE_PROPAGULE(Material.MANGROVE_PROPAGULE, 200),
    OAK_SAPLING(Material.OAK_SAPLING, 200),
    COBWEB(Material.COBWEB, 2000),
    DEAD_BUSH(Material.DEAD_BUSH, 500),
    SEA_PICKLE(Material.SEA_PICKLE, 500),
    SPORE_BLOSSOM(Material.SPORE_BLOSSOM, 500),
    SUGAR_CANE(Material.SUGAR_CANE, 300),
    KELP(Material.KELP, 500),
    PINK_PETALS(Material.PINK_PETALS, 700),
    BIG_DRIPLEAF(Material.BIG_DRIPLEAF, 400),
    SMALL_DRIPLEAF(Material.SMALL_DRIPLEAF, 400),
    BAMBOO(Material.BAMBOO, 1000),
    CHORUS_FLOWER(Material.CHORUS_FLOWER, 2000),
    CACTUS(Material.CACTUS, 300),
    BROWN_MUSHROOM_BLOCK(Material.BROWN_MUSHROOM_BLOCK, 3000),
    RED_MUSHROOM_BLOCK(Material.RED_MUSHROOM_BLOCK, 3000),
    VINE(Material.VINE, 300),
    GLOW_LICHEN(Material.GLOW_LICHEN, 400),
    LILY_PAD(Material.LILY_PAD, 100),
    SUNFLOWER(Material.SUNFLOWER, 500),
    LILAC(Material.LILAC, 500),
    ROSE_BUSH(Material.ROSE_BUSH, 500),
    PEONY(Material.PEONY, 500),
    TALL_GRASS(Material.TALL_GRASS, 500),
    LARGE_FERN(Material.LARGE_FERN, 500),
    TUBE_CORAL(Material.TUBE_CORAL, 300),
    BRAIN_CORAL(Material.BRAIN_CORAL, 300),
    BUBBLE_CORAL(Material.BUBBLE_CORAL, 300),
    FIRE_CORAL(Material.FIRE_CORAL, 300),
    HORN_CORAL(Material.HORN_CORAL, 300),
    WHEAT_SEEDS(Material.WHEAT_SEEDS, 100),
    COCOA_BEANS(Material.COCOA_BEANS, 500),
    PUMPKIN_SEEDS(Material.PUMPKIN_SEEDS, 300),
    MELON_SEEDS(Material.MELON_SEEDS, 300),
    CARROT(Material.CARROT, 200),
    POTATO(Material.POTATO, 200),
    BEETROOT_SEEDS(Material.BEETROOT_SEEDS, 500),
    SWEET_BERRIES(Material.SWEET_BERRIES, 600),
    GLOW_BERRIES(Material.GLOW_BERRIES, 600);

    @Getter
    private Material material;
    @Getter
    private int price;

    GretaGartner(Material material, Integer price ) {
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
