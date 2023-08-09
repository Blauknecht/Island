package de.plunamc.island.market;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

public enum VolkerVerkauf {

    SPRUCE_SAPLING(Material.SPRUCE_SAPLING, 200 ),
    OAK_SAPLING(Material.OAK_SAPLING,  200);

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
