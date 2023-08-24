package de.plunamc.island.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Random;

public class Itemmanager {

    public static ItemStack createItem(Material mat, int amount, String name){
        ItemStack it = new ItemStack(mat,amount);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        return it;
    }

    public static ItemStack createItemWithLore(Material mat, int amount, String name, List<String> lore){
        ItemStack it = new ItemStack(mat,amount);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        it.setItemMeta(meta);
        return it;
    }
    public static ItemStack createSpawnerItem(Material mat, int amount){
        ItemStack it = new ItemStack(mat,amount);
        ItemMeta meta = it.getItemMeta();
        it.setItemMeta(meta);
        return it;
    }


    public static ItemStack createMarketItemWithLore(Material mat, List<String> lore){
        ItemStack it = new ItemStack(mat,1);
        ItemMeta meta = it.getItemMeta();
        meta.setLore(lore);
        it.setItemMeta(meta);
        return it;
    }
    public static ItemStack createMarketItemWithLoreColoredName(Material mat, String color, List<String> lore){
        ItemStack it = new ItemStack(mat,1);
        ItemMeta meta = it.getItemMeta();
//        TODO: reparieren
//        meta.setDisplayName(color + meta.getDisplayName());
        meta.setLore(lore);
        it.setItemMeta(meta);
        return it;
    }
    public static ItemStack createItemWithModelData(Material mat, int amount, String name, int modeldata){
        ItemStack it = new ItemStack(mat,amount);
        ItemMeta meta = it.getItemMeta();
        meta.setCustomModelData(modeldata);
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        return it;
    }
    public static ItemStack createItemWithModelDataAndLore(Material mat, int amount, String name, int modeldata, List<String> lore){
        ItemStack it = new ItemStack(mat,amount);
        ItemMeta meta = it.getItemMeta();
        meta.setCustomModelData(modeldata);
        meta.setLore(lore);
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        return it;
    }
    public static ItemStack createSkull(String name, String text){
        ItemStack it = new ItemStack(Material.PLAYER_HEAD,1);
        SkullMeta meta = (SkullMeta) it.getItemMeta();
        meta.setDisplayName(text);
        meta.setOwner(name);
        it.setItemMeta(meta);
        return it;
    }
    public static ItemStack createSkullWithLore(String name, String text, List<String> lore){
        ItemStack it = new ItemStack(Material.PLAYER_HEAD,1);
        SkullMeta meta = (SkullMeta) it.getItemMeta();
        meta.setLore(lore);
        meta.setDisplayName(text);
        meta.setOwner(name);
        it.setItemMeta(meta);
        return it;
    }
    public static void clearPlayer(Player p){
        p.setHealth(20.0);
        p.setFoodLevel(20);
        p.setLevel(0);
        p.setExp(0);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        for(PotionEffect effect : p.getActivePotionEffects()){
            p.removePotionEffect(effect.getType());
        }
    }
    public static int randomInt(int min, int max){
        Random r = new Random();
        int i = r.nextInt(max-min);
        return i;
    }

}

