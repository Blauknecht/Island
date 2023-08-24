package de.plunamc.island.island;

import lombok.Getter;

import java.util.Arrays;

public enum IslandSize {

    SECHSUNDZWANZIG(26, 380000, 135, "XXVI"),
    FÜNFUNDZWANZIG(25, 300000, 131, "XXV"),
    VIERUNDZWANZIG(24, 24000, 127, "XXIV"),
    DREIUNDZWANZIG(23, 20000, 123, "XXIII"),
    ZWEIUNDZWANZIG(22, 165000, 119, "XXII"),
    EINUNDZWANZIG(21, 14000, 115, "XXI"),
    ZWANZIG(20, 12000, 111, "XX"),
    NEUNZEHN(19, 10300, 107, "XIX"),
    ACHTZEHN(18, 89500, 103, "XVIII"),
    SIEBZEHN(17, 77000, 99, "XVII"),
    SECHSZEHN(16, 65500, 95, "XVI"),
    FÜNFZEHN(15, 55000, 91, "XV"),
    VIERZEHN(14, 45500, 87, "XIV"),
    DREIZEHN(13, 37500, 83, "XIII"),
    ZWÖLF(12, 30000, 79, "XII"),
    ELF(11, 24000, 75, "XI"),
    ZEHN(10, 18500, 71, "X"),
    NEUN(9, 13500, 67, "IX"),
    ACHT(8, 9700, 63, "VIII"),
    SIEBEN(7, 6600, 59, "VII"),
    SECHS(6, 4200, 55, "VI"),
    FÜNF(5, 2400, 51, "V"),
    VIER(4, 1200, 47, "IV"),
    DREI(3, 500, 43, "III"),
    ZWEI(2, 100, 39, "II"),
    EINS(1, 0, 35, "I");

    @Getter
    private int minLevel;
    @Getter
    private int minEXP;
    @Getter
    private int size;
    @Getter
    private String name;

    IslandSize(int minLevel, int minEXP, int size, String name) {
        this.minEXP = minEXP;
        this.minLevel = minLevel;
        this.size = size;
        this.name = name;
    }

    public static IslandSize getSizeByLevel(int level) {
        return Arrays.stream(values()).filter(islandSize -> level >= islandSize.minLevel).findAny().orElse(null);
    }

    public static IslandSize getSizeByEXP(int exp) {
        return Arrays.stream(values()).filter(islandSize -> exp >= islandSize.minEXP).findAny().orElse(null);
    }
}