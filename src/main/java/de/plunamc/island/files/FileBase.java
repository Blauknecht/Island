package de.plunamc.island.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileBase {

    private String path;
    private String fileName;
    private File file;
    private FileConfiguration cfg;
    private Plugin plugin;

    public FileBase(Plugin plugin, String path, String fileName) {
        this.plugin = plugin;
        this.path = path;
        this.fileName = fileName;
        reloadConfig();
    }

    public void reloadConfig() {
        if(this.file == null) {
            this.file = new File(this.plugin.getDataFolder() + this.path, this.fileName + ".yml");
        }
        if(!this.file.exists()) {
            this.file.getParentFile().mkdir();
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig() {
        if(this.cfg == null) {
            reloadConfig();
        }
        return this.cfg;
    }

    public void saveConfig() {
        if((this.file == null) || (this.cfg == null)) {
            return;
        }
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
