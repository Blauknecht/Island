package de.plunamc.island.island;

import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum CobbleStoneBlocks {

    SECHSUNDZWANZIG(IslandSize.SECHSUNDZWANZIG, 74.418F,2.826F,2.826F,2.826F,0.942F
            ,0.942F,9.42F,2.00F,1.50F,0.75F,0.3F,
            0.6F,0.5F,0.15F),
    FÜNFUNDZWANZIG(IslandSize.FÜNFUNDZWANZIG, 74.418F,2.826F,2.826F,2.826F,0.942F
            ,0.942F,9.42F,2.00F,1.50F,0.75F,0.3F,
            0.6F,0.5F,0.15F),
    VIERUNDZWANZIG(IslandSize.VIERUNDZWANZIG, 74.418F,2.826F,2.826F,2.826F,0.942F
            ,0.942F,9.42F,2.00F,1.50F,0.75F,0.3F,
            0.6F,0.5F,0.15F),
    DREIUNDZWANZIG(IslandSize.DREIUNDZWANZIG, 74.418F,2.826F,2.826F,2.826F,0.942F
            ,0.942F,9.42F,2.00F,1.50F,0.75F,0.3F,
            0.6F,0.5F,0.15F),
    ZWEIUNDZWANZIG(IslandSize.ZWEIUNDZWANZIG, 74.418F,2.826F,2.826F,2.826F,0.942F
            ,0.942F,9.42F,2.00F,1.50F,0.75F,0.3F,
            0.6F,0.5F,0.15F),
    EINUNDZWANZIG(IslandSize.EINUNDZWANZIG, 74.418F,2.826F,2.826F,2.826F,0.942F
            ,0.942F,9.42F,2.00F,1.50F,0.75F,0.3F,
            0.6F,0.5F,0.15F),
    ZWANZIG(IslandSize.ZWANZIG, 74.418F,2.826F,2.826F,2.826F,0.942F
            ,0.942F,9.42F,2.00F,1.50F,0.75F,0.3F,
            0.6F,0.5F,0.15F),
    NEUNZEHN(IslandSize.NEUNZEHN, 75.6425F,2.8725F,2.8725F,2.8725F,0.9575F
            ,0.9575F,9.575F,1.5F,1.00F,0.5F,0.3F,
            0.45F,0.4F,0.1F),
    ACHTZEHN(IslandSize.ACHTZEHN, 75.6425F,2.8725F,2.8725F,2.8725F,0.9575F
            ,0.9575F,9.575F,1.5F,1.00F,0.5F,0.3F,
            0.45F,0.4F,0.1F),
    SIEBZEHN(IslandSize.SIEBZEHN, 75.6425F,2.8725F,2.8725F,2.8725F,0.9575F
            ,0.9575F,9.575F,1.5F,1.00F,0.5F,0.3F,
            0.45F,0.4F,0.1F),
    SECHSZEHN(IslandSize.SECHSZEHN, 75.6425F,2.8725F,2.8725F,2.8725F,0.9575F
            ,0.9575F,9.575F,1.5F,1.00F,0.5F,0.3F,
            0.45F,0.4F,0.1F),
    FÜNFZEHN(IslandSize.FÜNFZEHN, 75.6425F,2.8725F,2.8725F,2.8725F,0.9575F
            ,0.9575F,9.575F,1.5F,1.00F,0.5F,0.3F,
            0.45F,0.4F,0.1F),
    VIERZEHN(IslandSize.VIERZEHN, 76.33375F,2.89875F,2.89875F,2.89875F,0.96625F
            ,0.96625F,9.6625F,1.25F,0.75F,0.5F,0.2F,
            0.3F,0.3F,0.0075F),
    DREIZEHN(IslandSize.DREIZEHN, 76.33375F,2.89875F,2.89875F,2.89875F,0.96625F
            ,0.96625F,9.6625F,1.25F,0.75F,0.5F,0.2F,
            0.3F,0.3F,0.0075F),
    ZWÖLF(IslandSize.ZWÖLF, 76.33375F,2.89875F,2.89875F,2.89875F,0.96625F
            ,0.96625F,9.6625F,1.25F,0.75F,0.5F,0.2F,
            0.3F,0.3F,0.0075F),
    ELF(IslandSize.ELF, 76.33375F,2.89875F,2.89875F,2.89875F,0.96625F
            ,0.96625F,9.6625F,1.25F,0.75F,0.5F,0.2F,
            0.3F,0.3F,0.0075F),
    ZEHN(IslandSize.ZEHN,  77.2225F,2.9325F,2.9325F,2.9325F,0.9775F
            ,0.9775F,9.775F,1.00F,0.50F,0.25F,0.10F,
            0.15F,0.02F,0.05F),
    NEUN(IslandSize.NEUN, 77.2225F,2.9325F,2.9325F,2.9325F,0.9775F
            ,0.9775F,9.775F,1.00F,0.50F,0.25F,0.10F,
            0.15F,0.02F,0.05F),
    ACHT(IslandSize.ACHT,  77.2225F,2.9325F,2.9325F,2.9325F,0.9775F
            ,0.9775F,9.775F,1.00F,0.50F,0.25F,0.10F,
            0.15F,0.02F,0.05F),
    SIEBEN(IslandSize.SIEBEN,  77.2225F,2.9325F,2.9325F,2.9325F,0.9775F
            ,0.9775F,9.775F,1.00F,0.50F,0.25F,0.10F,
            0.15F,0.02F,0.05F),
    SECHS(IslandSize.SECHS, 77.2225F,2.9325F,2.9325F,2.9325F,0.9775F
            ,0.9775F,9.775F,1.00F,0.50F,0.25F,0.10F,
            0.15F,0.02F,0.05F),
    FÜNF(IslandSize.FÜNF, 77.262F,2.934F,2.934F,2.934F,0.978F
            ,0.978F,9.78F,1.00F,0.50F,0.25F,0.1F,
            0.15F,0.2F,0.00F),
    VIER(IslandSize.VIER, 77.42F,2.94F,2.94F,2.94F,0.98F
            ,0.98F,9.8F,1.00F,0.50F,0.25F,0.1F,
            0.15F,0.00F,0.00F),
    DREI(IslandSize.DREI, 77.5385F,2.9445F,2.9445F,2.9445F,0.9815F
            ,0.9815F,9.815F,1.00F,0.5F,0.25F,0.1F,
            0.00F,0.00F,0.00F),
    ZWEI(IslandSize.ZWEI, 78.21F,2.97F,2.97F,2.97F,0.99F
            ,0.99F,9.9F,1.00F,0.00F,0.00F,0.00F,
            0.00F,0.00F,0.00F),
    EINS(IslandSize.EINS, 79.0F,3.00F,3.00F,3.00F,1.00F
            ,1.00F,10.00F,0.00F,0.00F,0.00F,0.00F,
            0.00F,0.00F,0.00F);

    @Getter
    private IslandSize size;
    @Getter
    private float cobbleStoneSpawnRate;
    @Getter
    private float dioritSpawnRate;
    @Getter
    private float granitSpawnRate;
    @Getter
    private float andesitSpawnRate;
    @Getter
    private float tuffSpawnRate;
    @Getter
    private float calciteSpawnRate;
    @Getter
    private float deepslateSpawnRate;
    @Getter
    private float coalSpawnRate;
    @Getter
    private float ironSpawnRate;
    @Getter
    private float goldSpawnRate;
    @Getter
    private float lapisSpawnRate;
    @Getter
    private float redstoneSpawnRate;
    @Getter
    private float emeraldSpawnRate;
    @Getter
    private float diamondSpawnRate;

    @Getter
    private List<Material> materials;

    CobbleStoneBlocks(IslandSize islandSize, float cobbleStoneSpawnRate, float dioritSpawnRate,
                      float granitSpawnRate, float andesitSpawnRate, float tuffSpawnRate,
                      float calciteSpawnRate, float deepslateSpawnRate, float coalSpawnRate,
                      float ironSpawnRate, float goldSpawnRate, float lapisSpawnRate,
                      float redstoneSpawnRate, float emeraldSpawnRate, float diamondSpawnRate) {
        this.size = islandSize;
        this.cobbleStoneSpawnRate = cobbleStoneSpawnRate;
        this.dioritSpawnRate = dioritSpawnRate;
        this.granitSpawnRate = granitSpawnRate;
        this.andesitSpawnRate = andesitSpawnRate;
        this.tuffSpawnRate = tuffSpawnRate;
        this.calciteSpawnRate = calciteSpawnRate;
        this.deepslateSpawnRate = deepslateSpawnRate;
        this.coalSpawnRate = coalSpawnRate;
        this.ironSpawnRate = ironSpawnRate;
        this.goldSpawnRate = goldSpawnRate;
        this.lapisSpawnRate = lapisSpawnRate;
        this.redstoneSpawnRate = redstoneSpawnRate;
        this.emeraldSpawnRate = emeraldSpawnRate;
        this.diamondSpawnRate = diamondSpawnRate;

        List<Material> materialList = new ArrayList<>();
        for (int i = 0; i < this.cobbleStoneSpawnRate*10000; i++){
            materialList.add(Material.COBBLESTONE);
        }
        for (int i = 0; i < this.dioritSpawnRate*10000; i++){
            materialList.add(Material.DIORITE);
        }
        for (int i = 0; i < this.granitSpawnRate*10000; i++){
            materialList.add(Material.GRANITE);
        }
        for (int i = 0; i < this.andesitSpawnRate*10000; i++){
            materialList.add(Material.ANDESITE);
        }
        for (int i = 0; i < this.tuffSpawnRate*10000; i++){
            materialList.add(Material.TUFF);
        }
        for (int i = 0; i < this.calciteSpawnRate*10000; i++){
            materialList.add(Material.CALCITE);
        }
        for (int i = 0; i < this.deepslateSpawnRate*10000; i++){
            materialList.add(Material.DEEPSLATE);
        }
        for (int i = 0; i < this.coalSpawnRate*10000; i++){
            materialList.add(Material.COAL_ORE);
        }
        for (int i = 0; i < this.ironSpawnRate*10000; i++){
            materialList.add(Material.IRON_ORE);
        }
        for (int i = 0; i < this.goldSpawnRate*10000; i++){
            materialList.add(Material.GOLD_ORE);
        }
        for (int i = 0; i < this.lapisSpawnRate*10000; i++){
            materialList.add(Material.LAPIS_ORE);
        }
        for (int i = 0; i < this.redstoneSpawnRate*10000; i++){
            materialList.add(Material.REDSTONE_ORE);
        }
        for (int i = 0; i < this.emeraldSpawnRate*10000; i++){
            materialList.add(Material.EMERALD_ORE);
        }
        for (int i = 0; i < this.diamondSpawnRate*10000; i++){
            materialList.add(Material.DIAMOND_ORE);
        }
        this.materials = materialList;
    }

    public static CobbleStoneBlocks getBlockRate(IslandSize size){
        for (CobbleStoneBlocks value : values()) {
            if(value.getSize() == size){
                return value;
            }
        }
        return EINS;
    }
}
