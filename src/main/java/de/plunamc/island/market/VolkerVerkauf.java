package de.plunamc.island.market;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

public enum VolkerVerkauf {

    STONE(Material.STONE, 7),
    COBBLESTONE(Material.COBBLESTONE, 7),
    IRON_ORE(Material.IRON_ORE, 100),
    COPPER_ORE(Material.COPPER_ORE, 100),
    GOLD_ORE(Material.GOLD_ORE, 180),
    COAL_BLOCK(Material.COAL_BLOCK, 900),
    RAW_IRON_BLOCK(Material.RAW_IRON_BLOCK, 360),
    RAW_COPPER_BLOCK(Material.RAW_COPPER_BLOCK, 270),
    RAW_GOLD_BLOCK(Material.RAW_GOLD_BLOCK, 720),
    GOLD_BLOCK(Material.GOLD_BLOCK, 1620),
    OAK_LOG(Material.OAK_LOG, 120),
    SPRUCE_LOG(Material.SPRUCE_LOG, 120),
    BIRCH_LOG(Material.BIRCH_LOG, 120),
    JUNGLE_LOG(Material.JUNGLE_LOG, 120),
    ACACIA_LOG(Material.ACACIA_LOG, 120),
    DARK_OAK_LOG(Material.DARK_OAK_LOG, 120),
    MANGROVE_LOG(Material.MANGROVE_LOG, 120),
    CRIMSON_STEM(Material.CRIMSON_STEM, 120),
    WARPED_STEM(Material.WARPED_STEM, 120),
    LAPIS_BLOCK(Material.LAPIS_BLOCK, 1260),
    SEAGRASS(Material.SEAGRASS, 8),
    SEA_PICKLE(Material.SEA_PICKLE, 5),
    WHITE_WOOL(Material.WHITE_WOOL, 30),
    ORANGE_WOOL(Material.ORANGE_WOOL, 30),
    MAGENTA_WOOL(Material.MAGENTA_WOOL, 30),
    LIGHT_BLUE_WOOL(Material.LIGHT_BLUE_WOOL, 30),
    YELLOW_WOOL(Material.YELLOW_WOOL, 30),
    LIME_WOOL(Material.LIME_WOOL, 30),
    PINK_WOOL(Material.PINK_WOOL, 30),
    GRAY_WOOL(Material.GRAY_WOOL, 30),
    LIGHT_GRAY_WOOL(Material.LIGHT_GRAY_WOOL, 30),
    CYAN_WOOL(Material.CYAN_WOOL, 30),
    PURPLE_WOOL(Material.PURPLE_WOOL, 30),
    BLUE_WOOL(Material.BLUE_WOOL, 30),
    BROWN_WOOL(Material.BROWN_WOOL, 30),
    GREEN_WOOL(Material.GREEN_WOOL, 30),
    RED_WOOL(Material.RED_WOOL, 30),
    BLACK_WOOL(Material.BLACK_WOOL, 30),
    BROWN_MUSHROOM(Material.BROWN_MUSHROOM, 40),
    RED_MUSHROOM(Material.RED_MUSHROOM, 40),
    WEEPING_VINES(Material.WEEPING_VINES, 6),
    TWISTING_VINES(Material.TWISTING_VINES, 6),
    SUGAR_CANE(Material.SUGAR_CANE, 6),
    KELP(Material.KELP, 3),
    BAMBOO(Material.BAMBOO, 2),
    CACTUS(Material.CACTUS, 4),
    PUMPKIN(Material.PUMPKIN, 8),
    EMERALD_BLOCK(Material.EMERALD_BLOCK, 1800),
    TURTLE_EGG(Material.TURTLE_EGG, 80),
    REDSTONE(Material.REDSTONE, 160),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK, 1440),
    SCUTE(Material.SCUTE, 60),
    ARROW(Material.ARROW, 16),
    COAL(Material.COAL, 100),
    DIAMOND(Material.DIAMOND, 240),
    EMERALD(Material.EMERALD, 200),
    LAPIS_LAZULI(Material.LAPIS_LAZULI, 140),
    AMETHYST_SHARD(Material.AMETHYST_SHARD, 6),
    RAW_IRON(Material.RAW_IRON, 40),
    RAW_COPPER(Material.RAW_COPPER, 30),
    RAW_GOLD(Material.RAW_GOLD, 80),
    GOLD_INGOT(Material.GOLD_INGOT, 180),
    STRING(Material.STRING, 12),
    FEATHER(Material.FEATHER, 16),
    GUNPOWDER(Material.GUNPOWDER, 10),
    WHEAT_SEEDS(Material.WHEAT_SEEDS, 2),
    WHEAT(Material.WHEAT, 24),
    PORKCHOP(Material.PORKCHOP, 40),
    COOKED_PORKCHOP(Material.COOKED_PORKCHOP, 40),
    BUCKET(Material.BUCKET, 20),
    LEATHER(Material.LEATHER, 30),
    MILK_BUCKET(Material.MILK_BUCKET, 150),
    DRIED_KELP_BLOCK(Material.DRIED_KELP_BLOCK, 72),
    EGG(Material.EGG, 10),
    COD(Material.COD, 30),
    SALMON(Material.SALMON, 30),
    TROPICAL_FISH(Material.TROPICAL_FISH, 30),
    PUFFERFISH(Material.PUFFERFISH, 80),
    COOKED_COD(Material.COOKED_COD, 40),
    COOKED_SALMON(Material.COOKED_SALMON, 40),
    INK_SAC(Material.INK_SAC, 20),
    COCOA_BEANS(Material.COCOA_BEANS, 12),
    BONE(Material.BONE, 16),
    MELON_SLICE(Material.MELON_SLICE, 2),
    DRIED_KELP(Material.DRIED_KELP, 8),
    PUMPKIN_SEEDS(Material.PUMPKIN_SEEDS, 1),
    MELON_SEEDS(Material.MELON_SEEDS, 1),
    BEEF(Material.BEEF, 40),
    COOKED_BEEF(Material.COOKED_BEEF, 44),
    CHICKEN(Material.CHICKEN, 14),
    COOKED_CHICKEN(Material.COOKED_CHICKEN, 18),
    ROTTEN_FLESH(Material.ROTTEN_FLESH, 6),
    ENDER_PEARL(Material.ENDER_PEARL, 60),
    BLAZE_POWDER(Material.BLAZE_POWDER, 60),
    GOLD_NUGGET(Material.GOLD_NUGGET, 20),
    SPIDER_EYE(Material.SPIDER_EYE, 20),
    CARROT(Material.CARROT, 28),
    POTATO(Material.POTATO, 28),
    BAKED_POTATO(Material.BAKED_POTATO, 40),
    POISONOUS_POTATO(Material.POISONOUS_POTATO, 4),
    PRISMARINE_SHARD(Material.PRISMARINE_SHARD, 10),
    PRISMARINE_CRYSTALS(Material.PRISMARINE_CRYSTALS, 80),
    RABBIT(Material.RABBIT, 40),
    COOKED_RABBIT(Material.COOKED_RABBIT, 44),
    RABBIT_HIDE(Material.RABBIT_HIDE, 300),
    MUTTON(Material.MUTTON, 40),
    COOKED_MUTTON(Material.COOKED_MUTTON,44 ),
    CHORUS_FRUIT(Material.CHORUS_FRUIT, 4),
    BEETROOT(Material.BEETROOT, 24),
    BEETROOT_SEEDS(Material.BEETROOT_SEEDS, 1),
    SWEET_BERRIES(Material.SWEET_BERRIES, 14),
    GLOW_BERRIES(Material.GLOW_BERRIES, 42),
    HONEY_BOTTLE(Material.HONEY_BOTTLE, 160);

    @Getter
    private Material material;
    @Getter
    private int sellprice;

    VolkerVerkauf(Material material, Integer sellprice ) {
        this.material = material;
        this.sellprice = sellprice;
    }
    public Material getMaterial() {
        return material;
    }
    public int getSellPrice() {
        return sellprice;
    }
}
