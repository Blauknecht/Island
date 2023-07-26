package de.yupiter.island.spawner;

import lombok.Getter;
import org.bukkit.Instrument;
import org.bukkit.Note;

public enum CustomBlock {

    RUBYORE(2, Instrument.BANJO, new Note(0)),
    WEIZEN_GEN(3, Instrument.DIDGERIDOO, new Note(0)),
    KAROTTEN_GEN(4, Instrument.DIDGERIDOO, new Note(1)),
    KARTOFFEL_GEN(5, Instrument.DIDGERIDOO, new Note(2)),
    KUERBIS_GEN(6, Instrument.DIDGERIDOO, new Note(3)),
    MELONEN_GEN(7, Instrument.DIDGERIDOO, new Note(4)),
    ZUCKERROHR_GEN(8, Instrument.DIDGERIDOO, new Note(5)),
    BAMBUS_GEN(9, Instrument.DIDGERIDOO, new Note(6)),
    MOOS_GEN(10, Instrument.DIDGERIDOO, new Note(7)),
    HONIG_GEN(11, Instrument.DIDGERIDOO, new Note(8)),
    SEETANG_GEN(12, Instrument.DIDGERIDOO, new Note(9)),
    EIS_GEN(13, Instrument.DIDGERIDOO, new Note(10));

    @Getter
    private int customModelData;
    @Getter
    private Instrument instrument;
    @Getter
    private Note note;

    CustomBlock(int customModelData, Instrument instrument, Note note){
        this.customModelData = customModelData;
        this.instrument = instrument;
        this.note = note;
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
