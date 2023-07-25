package de.yupiter.island.island;

import lombok.Getter;

import java.util.Arrays;

public enum IslandSize {

    EXTRA_LARGE(50, 125, "XL"),
    LARGE(25, 75, "Groß"),
    NORMAL(10, 50, "Normal"),
    SMALL(1, 35, "Klein");

    @Getter
    private int minLevel;
    @Getter
    private int size;
    @Getter
    private String name;

    IslandSize(int minLevel, int size, String name){
        this.minLevel = minLevel;
        this.size = size;
        this.name = name;
    }

    public static IslandSize getSizeByLevel(int level){
        return Arrays.stream(values()).filter(islandSize -> level >= islandSize.minLevel).findAny().orElse(null);
    }
}
