package de.plunamc.island.files;

import de.plunamc.island.PlunaIsland;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class BlockFile extends FileBase {

    private FileConfiguration config;

    public static Map<Material, Integer> blockExpMap;

    public BlockFile() {
        super(PlunaIsland.getInstance(), "/blockconfig/", "blockconfig");

        this.config = getConfig();
        this.blockExpMap = new HashMap<>();
        this.loadBlock();
    }

    public void loadBlock(){
        for (String key : this.config.getConfigurationSection("blocks").getKeys(false)) {
            this.blockExpMap.put(Material.getMaterial(key), this.config.getInt("blocks."+key));
     //       System.out.println(key + " - "+ this.getConfig().getInt("blocks."+key));
        }
    }
}
