package de.plunamc.island.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlReader {

    public Map getBlockConfig(String s, Integer i){
        File file = new File("plugins/PluanMC-Island/blockconfig/blockconfig.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        String x = cfg.getString("ICE");
        Integer z = cfg.getInt("ICE: ");


        return null;
    }

}
