package com.newbiomesgen.bosses;

import com.newbiomesgen.NewBiomesGen;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class VoidSentinelBoss implements Listener {

    private final NewBiomesGen plugin;

    public VoidSentinelBoss(NewBiomesGen plugin) {
        this.plugin = plugin;
    }

    public void spawnBoss(Location loc) {
        IronGolem boss = (IronGolem) loc.getWorld().spawnEntity(loc, EntityType.IRON_GOLEM);
        
        // הגדרת מאפייני הבוס
        boss.setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Void Sentinel");
        boss.setCustomNameVisible(true);
        
        if (boss.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500.0);
            boss.setHealth(500.0);
        }

        boss.addScoreboardTag("void_sentinel");
    }

    @EventHandler
    public void onBossAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof IronGolem boss && boss.getScoreboardTags().contains("void_sentinel")) {
            if (event.getEntity() instanceof Player player) {
                
                // מתקפת מכה סגולה שמניפה את השחקן לאוויר
                player.setVelocity(player.getLocation().getDirection().multiply(-1.5).setY(0.8));
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 0.5f);
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 30, 0.5, 0.5, 0.5, 0.1);
                
                player.sendMessage(ChatColor.RED + "ה-Void Sentinel הדף אותך אחורה!");
            }
        }
    }
}
