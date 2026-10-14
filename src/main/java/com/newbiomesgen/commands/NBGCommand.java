package com.newbiomesgen.commands;

import com.newbiomesgen.bosses.VoidSentinelBoss;
import com.newbiomesgen.items.VoidItems;
import com.newbiomesgen.structures.CrystalCitadel;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NBGCommand implements CommandExecutor {

    private final VoidSentinelBoss bossManager;

    public NBGCommand(VoidSentinelBoss bossManager) {
        this.bossManager = bossManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("הפקודה הזו ניתנת לביצוע על ידי שחקנים בלבד.");
            return true;
        }

        if (!player.hasPermission("newbiomesgen.admin")) {
            player.sendMessage(ChatColor.RED + "אין לך הרשאה לבצע פקודה זו!");
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sendHelpMenu(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "spawn":
                if (args.length > 1 && args[1].equalsIgnoreCase("boss")) {
                    bossManager.spawnBoss(player.getLocation());
                    player.sendMessage(ChatColor.DARK_PURPLE + "✔ ה-Void Sentinel זימן בהצלחה במיקום שלך!");
                } else {
                    player.sendMessage(ChatColor.RED + "שימוש שגוי. נסה: /nbg spawn boss");
                }
                break;

            case "give":
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("sword")) {
                        player.getInventory().addItem(VoidItems.getVoidSword());
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "✔ קיבלת את ה-Void Sword!");
                    } else if (args[1].equalsIgnoreCase("wand")) {
                        player.getInventory().addItem(VoidItems.getVoidWand());
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "✔ קיבלת את ה-Void Wand!");
                    } else {
                        player.sendMessage(ChatColor.RED + "פריטים זמינים: sword, wand");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "שימוש שגוי. נסה: /nbg give <sword|wand>");
                }
                break;

            case "structure":
                if (args.length > 1 && args[1].equalsIgnoreCase("build")) {
                    CrystalCitadel.buildAltar(player.getLocation());
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "✔ מזבח ה-Void Citadel נבנה בהצלחה!");
                } else {
                    player.sendMessage(ChatColor.RED + "שימוש שגוי. נסה: /nbg structure build");
                }
                break;

            default:
                sendHelpMenu(player);
                break;
        }

        return true;
    }

    private void sendHelpMenu(Player player) {
        player.sendMessage(ChatColor.DARK_GRAY + "---------------------------------------------");
        player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "   🔮 NewBiomesGen - עזר למנהלים 🔮");
        player.sendMessage(ChatColor.DARK_GRAY + "---------------------------------------------");
        player.sendMessage(ChatColor.GOLD + "/nbg help " + ChatColor.WHITE + "- הופעת תפריט העזרה הזה");
        player.sendMessage(ChatColor.GOLD + "/nbg spawn boss " + ChatColor.WHITE + "- זימון הבוס Void Sentinel במיקום שלך");
        player.sendMessage(ChatColor.GOLD + "/nbg give sword " + ChatColor.WHITE + "- קבלת חרב ה-Void הקסומה");
        player.sendMessage(ChatColor.GOLD + "/nbg give wand " + ChatColor.WHITE + "- קבלת מטה ה-Void");
        player.sendMessage(ChatColor.GOLD + "/nbg structure build " + ChatColor.WHITE + "- בניית מזבח קריסטלים במיקום הנוכחי");
        player.sendMessage(ChatColor.DARK_GRAY + "---------------------------------------------");
    }
}
