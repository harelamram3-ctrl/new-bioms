package com.newbiomesgen.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || !item.hasItemMeta() || item.getItemMeta().getDisplayName() == null) return;

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            String name = item.getItemMeta().getDisplayName();

            // יכולת חרב ה-Void
            if (name.contains("Void Blade")) {
                event.setCancelled(true);
                Location targetLoc = player.getTargetBlockExact(30) != null ? 
                        player.getTargetBlockExact(30).getLocation() : player.getLocation().add(player.getLocation().getDirection().multiply(5));
                
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1.0f, 0.5f);
                player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, targetLoc, 1);
                
                for (Entity e : targetLoc.getWorld().getNearbyEntities(targetLoc, 3, 3, 3)) {
                    if (e instanceof LivingEntity target && e != player) {
                        target.damage(12.0, player);
                    }
                }
                player.sendMessage(ChatColor.DARK_PURPLE + "שיגרת פיצוץ קריסטל למיקום הנבחר!");
            }

            // יכולת מטה ה-Void
            else if (name.contains("Wand of the Void")) {
                event.setCancelled(true);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.5f);
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation().add(0, 1, 0), 50, 1, 0.5, 1, 0.1);

                for (Entity e : player.getNearbyEntities(8, 8, 8)) {
                    if (e instanceof LivingEntity target && e != player) {
                        target.setVelocity(player.getLocation().getDirection().multiply(2.0).setY(1.0));
                        target.damage(6.0, player);
                    }
                }
            }
        }
    }
}
