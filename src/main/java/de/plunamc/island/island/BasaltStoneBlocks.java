package de.plunamc.island.island;

import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum BasaltStoneBlocks {

    SECHSUNDZWANZIG(IslandSize.SECHSUNDZWANZIG, 24.5F,49.97F,24.50F,0.2F,0.73F
            ,0.05F, 0.05F ),
    FÜNFUNDZWANZIG(IslandSize.FÜNFUNDZWANZIG, 24.5F,49.97F,24.50F,0.2F,0.73F
            ,0.05F, 0.05F ),
    VIERUNDZWANZIG(IslandSize.VIERUNDZWANZIG, 24.5F,49.97F,24.50F,0.2F,0.73F
            ,0.05F, 0.05F ),
    DREIUNDZWANZIG(IslandSize.DREIUNDZWANZIG, 24.5F,49.97F,24.50F,0.2F,0.73F
            ,0.05F, 0.05F ),
    ZWEIUNDZWANZIG(IslandSize.ZWEIUNDZWANZIG, 24.5F,49.97F,24.50F,0.2F,0.73F
            ,0.05F, 0.05F ),
    EINUNDZWANZIG(IslandSize.EINUNDZWANZIG, 24.5F,49.98F,24.50F,0.2F,0.73F
            ,0.05F, 0.04F ),
    ZWANZIG(IslandSize.ZWANZIG, 24.5F,49.98F,24.50F,0.2F,0.73F
            ,0.05F, 0.04F ),
    NEUNZEHN(IslandSize.NEUNZEHN, 24.5F,49.98F,24.50F,0.2F,0.73F
            ,0.05F, 0.04F ),
    ACHTZEHN(IslandSize.ACHTZEHN, 24.5F,49.98F,24.50F,0.2F,0.73F
            ,0.05F, 0.04F ),
    SIEBZEHN(IslandSize.SIEBZEHN, 24.5F,49.98F,24.50F,0.2F,0.73F
            ,0.05F, 0.04F ),
    SECHSZEHN(IslandSize.SECHSZEHN, 24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.03F ),
    FÜNFZEHN(IslandSize.FÜNFZEHN, 24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.03F ),
    VIERZEHN(IslandSize.VIERZEHN, 24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.03F ),
    DREIZEHN(IslandSize.DREIZEHN,24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.03F ),
    ZWÖLF(IslandSize.ZWÖLF, 24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.03F ),
    ELF(IslandSize.ELF, 24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.02F ),
    ZEHN(IslandSize.ZEHN,  24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.02F ),
    NEUN(IslandSize.NEUN, 24.5F,50.00F,24.50F,0.2F,0.73F
            ,0.05F, 0.02F ),
    ACHT(IslandSize.ACHT,  24.5F,50.01F,24.50F,0.2F,0.73F
            ,0.05F, 0.01F ),
    SIEBEN(IslandSize.SIEBEN,24.5F,50.01F,24.50F,0.2F,0.73F
            ,0.05F, 0.01F ),
    SECHS(IslandSize.SECHS,24.5F,50.01F,24.50F,0.2F,0.73F
            ,0.05F, 0.01F ),
    FÜNF(IslandSize.FÜNF,  24.5F,50.02F,24.50F,0.2F,0.73F
            ,0.05F, 0F ),
    VIER(IslandSize.VIER,  24.5F,50.02F,24.50F,0.2F,0.73F
            ,0.05F, 0F ),
    DREI(IslandSize.DREI,  24.5F,50.02F,24.50F,0.02F,0.73F
            ,0.05F, 0F ),
    ZWEI(IslandSize.ZWEI, 24.5F,50.02F,24.50F,0.0F,0.73F
            ,0.05F, 0F ),
    EINS(IslandSize.EINS, 24.5F,50.02F,24.50F,0.2F,0.73F
            ,0.05F, 0F );

    @Getter
    private IslandSize size;
    @Getter
    private float basaltStoneSpawnRate;
    @Getter
    private float netherrackSpawnRate;
    @Getter
    private float blackstoneSpawnRate;
    @Getter
    private float nethergoldSpawnRate;
    @Getter
    private float netherquarzSpawnRate;
    @Getter
    private float goldBlackStoneSpawnRate;
    @Getter
    private float acientdebriSpawnRate;

    @Getter
    private List<Material> materials;

    BasaltStoneBlocks(IslandSize islandSize, float basaltStoneSpawnRate, float netherrackSpawnRate, float blackstoneSpawnRate, float nethergoldSpawnRate,
                      float netherquarzSpawnRate, float goldBlackStoneSpawnRate, float acientdebriSpawnRate) {
        this.size = islandSize;
        this.basaltStoneSpawnRate = basaltStoneSpawnRate;
        this.netherrackSpawnRate = netherrackSpawnRate;
        this.blackstoneSpawnRate = blackstoneSpawnRate;
        this.nethergoldSpawnRate = nethergoldSpawnRate;
        this.netherquarzSpawnRate = netherquarzSpawnRate;
        this.goldBlackStoneSpawnRate = goldBlackStoneSpawnRate;
        this.acientdebriSpawnRate = acientdebriSpawnRate;

        List<Material> materialList = new ArrayList<>();
        for (int i = 0; i < this.basaltStoneSpawnRate*10000; i++){
            materialList.add(Material.BASALT);
        }
        for (int i = 0; i < this.netherrackSpawnRate*10000; i++){
            materialList.add(Material.NETHERRACK);
        }
        for (int i = 0; i < this.blackstoneSpawnRate*10000; i++){
            materialList.add(Material.BLACKSTONE);
        }
        for (int i = 0; i < this.nethergoldSpawnRate*10000; i++){
            materialList.add(Material.NETHER_GOLD_ORE);
        }
        for (int i = 0; i < this.netherquarzSpawnRate*10000; i++){
            materialList.add(Material.NETHER_QUARTZ_ORE);
        }
        for (int i = 0; i < this.goldBlackStoneSpawnRate*10000; i++){
            materialList.add(Material.GILDED_BLACKSTONE);
        }
        for (int i = 0; i < this.acientdebriSpawnRate*10000; i++){
            materialList.add(Material.ANCIENT_DEBRIS);
        }
        this.materials = materialList;
    }

    public static BasaltStoneBlocks getBlockRate(IslandSize size){
        for (BasaltStoneBlocks value : values()) {
            if(value.getSize() == size){
                return value;
            }
        }
        return EINS;
    }
}
