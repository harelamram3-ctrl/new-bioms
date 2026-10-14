package com.newbiomesgen.commands;

import com.newbiomesgen.bosses.VoidSentinelBoss;
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

        if (args.length > 0 && args[0].equalsIgnoreCase("spawn")) {
            if (args.length > 1 && args[1].equalsIgnoreCase("boss")) {
                bossManager.spawnBoss(player.getLocation());
                player.sendMessage(ChatColor.DARK_PURPLE + "ה-Void זומן בהצלחה במיקום שלך!");
                return true;
            }
        }

        player.sendMessage(ChatColor.YELLOW + "שימוש: /nbg spawn boss");
        return true;
    }
}
