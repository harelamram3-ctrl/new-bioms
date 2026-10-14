package com.newbiomesgen.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class VoidItems {

    public static ItemStack getVoidSword() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Void Blade");
            meta.setLore(Arrays.asList(
                    ChatColor.GRAY + "חרב עתיקה שחושלה מליבת הקריסטלים.",
                    "",
                    ChatColor.LIGHT_PURPLE + "קליק ימני: " + ChatColor.YELLOW + "מזמן מטח מטאורים!"
            ));
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static ItemStack getVoidWand() {
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Wand of the Void");
            meta.setLore(Arrays.asList(
                    ChatColor.GRAY + "מטה ענבר האוגר אנרגיה סגולה עוצמתית.",
                    "",
                    ChatColor.LIGHT_PURPLE + "קליק ימני: " + ChatColor.YELLOW + "משגר גל אנרגיה המעיף אויבים!"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
}
