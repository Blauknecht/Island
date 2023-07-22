package de.yupiter.island;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class YupiterIsland extends JavaPlugin {

    @Getter
    private static YupiterIsland instance;

    @Getter
    private String prefix = "§aYuIs §7";

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}