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
        
        boss.setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Void Sentinel");
        boss.setCustomNameVisible(true);
        
        if (boss.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500.0);
            boss.setHealth(500.0);
        }

        boss.addScoreboardTag("void_sentinel");

        // הפעלת לוגיקת מתקפות מיוחדות של הבוס כל עוד הוא חי
        startBossAbilityLoop(boss);
    }

    private void startBossAbilityLoop(IronGolem boss) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!boss.isValid() || boss.isDead()) {
                    this.cancel();
                    return;
                }

                // בדיקה אם יש שחקן קרוב כדי לשגר מטאור
                for (org.bukkit.entity.Entity entity : boss.getNearbyEntities(15, 15, 15)) {
                    if (entity instanceof Player player) {
                        castMeteorAbility(boss, player);
                        break; // מטאור אחד בכל מחזור לקרב מעניין
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // כל 5 שניות (100 טייקים)
    }

    private void castMeteorAbility(IronGolem boss, Player target) {
        Location targetLoc = target.getLocation();
        boss.getWorld().playSound(targetLoc, Sound.ENTITY_ENDER_DRAGON_FLAP, 1.5f, 0.2f);
        target.sendMessage(ChatColor.LIGHT_PURPLE + "ה-Void Sentinel מזמין מטאור לעברך!");

        // יצירת אפקט מטאור נוחת מלמעלה
        new BukkitRunnable() {
            double yOffset = 10.0;

            @Override
            public void run() {
                if (yOffset <= 0 || targetLoc.getWorld() == null) {
                    // פגיעת המטאור בקרקע
                    targetLoc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, targetLoc, 1);
                    targetLoc.getWorld().playSound(targetLoc, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0.5f);
                    
                    // גרימת נזק והעיפה
                    for (org.bukkit.entity.Entity nearby : targetLoc.getWorld().getNearbyEntities(targetLoc, 4, 4, 4)) {
                        if (nearby instanceof Player p) {
                            p.damage(10.0, boss);
                            p.setVelocity(p.getLocation().getDirection().multiply(-1).setY(1.0));
                        }
                    }
                    this.cancel();
                    return;
                }

                Location particleLoc = targetLoc.clone().add(0, yOffset, 0);
                targetLoc.getWorld().spawnParticle(Particle.DRAGON_BREATH, particleLoc, 20, 0.5, 0.5, 0.5, 0.05);
                targetLoc.getWorld().spawnParticle(Particle.PORTAL, particleLoc, 10, 0.2, 0.2, 0.2, 0.1);
                yOffset -= 2.0;
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }

    @EventHandler
    public void onBossAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof IronGolem boss && boss.getScoreboardTags().contains("void_sentinel")) {
            if (event.getEntity() instanceof Player player) {
                player.setVelocity(player.getLocation().getDirection().multiply(-1.5).setY(0.8));
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 0.5f);
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 30, 0.5, 0.5, 0.5, 0.1);
                player.sendMessage(ChatColor.RED + "ה-Void Sentinel הדף אותך אחורה!");
            }
        }
    }
}
