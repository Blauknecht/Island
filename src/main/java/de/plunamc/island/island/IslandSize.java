package de.plunamc.island.island;

import lombok.Getter;

import java.util.Arrays;

public enum IslandSize {

    SECHSUNDZWANZIG(25, 380000,135, "XXVI"),
    FÜNFUNDZWANZIG(24, 300000,131, "XXV"),
    VIERUNDZWANZIG(23, 24000,127, "XXIV"),
    DREIUNDZWANZIG(22, 20000,123, "XXIII"),
    ZWEIUNDZWANZIG(21, 165000,119, "XXII"),
    EINUNDZWANZIG(20, 14000,115, "XXI"),
    ZWANZIG(19, 12000,111, "XX"),
    NEUNZEHN(18, 10300,107, "XIX"),
    ACHTZEHN(17, 89500,103, "XVIII"),
    SIEBZEHN(16, 77000,99, "XVII"),
    SECHSZEHN(15, 65500,95, "XVI"),
    FÜNFZEHN(14, 55000,91, "XV"),
    VIERZEHN(13, 45500,87, "XIV"),
    DREIZEHN(12, 37500,83, "XIII"),
    ZWÖLF(11, 30000,79, "XII"),
    ELF(10, 24000,75, "XI"),
    ZEHN(9, 18500,71, "X"),
    NEUN(8, 13500,67, "IX"),
    ACHT(7, 9700,63, "VIII"),
    SIEBEN(6, 6600,59, "VII"),
    SECHS(5, 4200,55, "VI"),
    FÜNF(4,2400, 51, "V"),
    VIER(3,1200, 47, "IV"),
    DREI(2,450, 43, "III"),
    ZWEI(1,75, 39, "II"),
    EINS(0,0, 35, "I");

    @Getter
    private int minLevel;
    @Getter
    private int minEXP;
    @Getter
    private int size;
    @Getter
    private String name;

    IslandSize(int minLevel,int minEXP, int size, String name){
        this.minEXP = minEXP;
        this.minLevel = minLevel;
        this.size = size;
        this.name = name;
    }

    public static IslandSize getSizeByLevel(int level){
        return Arrays.stream(values()).filter(islandSize -> level >= islandSize.minLevel).findAny().orElse(null);
    }
    public static IslandSize getSizeByEXP(int exp){
        return Arrays.stream(values()).filter(islandSize -> exp >= islandSize.minEXP).findAny().orElse(null);
    }
}
