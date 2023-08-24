package de.plunamc.island.spawner;

import lombok.Getter;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;

public enum CustomBlock {

    WEIZEN_GEN(3, Instrument.DIDGERIDOO, new Note(0),Material.WHEAT, 10, 20*10),
    KAROTTEN_GEN(4, Instrument.DIDGERIDOO, new Note(1),Material.CARROT, 10, 20*10),
    KARTOFFEL_GEN(5, Instrument.DIDGERIDOO, new Note(2),Material.POTATO, 10, 20*10),
    KUERBIS_GEN(6, Instrument.DIDGERIDOO, new Note(3),Material.PUMPKIN, 1, 20*10),
    MELONEN_GEN(7, Instrument.DIDGERIDOO, new Note(4),Material.MELON_SLICE, 10, 20*10),
    ZUCKERROHR_GEN(8, Instrument.DIDGERIDOO, new Note(5),Material.SUGAR_CANE, 5, 20*10),
    BAMBUS_GEN(9, Instrument.DIDGERIDOO, new Note(6),Material.BAMBOO, 3, 20*10),
    MOOS_GEN(10, Instrument.DIDGERIDOO, new Note(7), Material.MOSS_BLOCK, 1, 20*10),
    HONIG_GEN(11, Instrument.DIDGERIDOO, new Note(8), Material.HONEYCOMB, 1, 20*10),
    SEETANG_GEN(12, Instrument.DIDGERIDOO, new Note(9), Material.KELP, 5, 20*10),
    EIS_GEN(13, Instrument.DIDGERIDOO, new Note(10), Material.IRON_INGOT, 1, 20*10);

    @Getter
    private int customModelData;
    @Getter
    private Material spawnMaterial;
    @Getter
    private int spawnAmount;
    @Getter
    private int spawnRate;
    @Getter
    private Instrument instrument;
    @Getter
    private Note note;

    CustomBlock(int customModelData, Instrument instrument, Note note, Material spawnMaterial, int spawnAmount, int spawnRate){
        this.customModelData = customModelData;
        this.instrument = instrument;
        this.note = note;
        this.spawnAmount = spawnAmount;
        this.spawnMaterial = spawnMaterial;
        this.spawnRate = spawnRate;
    }

    public static CustomBlock getBlockByCustomModelData(int customModelData){
        for (CustomBlock customBlock : values()) {
            if(customBlock.getCustomModelData() == customModelData){
                return customBlock;
            }
        }
        return null;
    }
}
