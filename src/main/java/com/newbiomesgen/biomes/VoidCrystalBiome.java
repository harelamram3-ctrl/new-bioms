package com.newbiomesgen.biomes;

import com.newbiomesgen.NewBiomesGen;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VoidCrystalBiome {

    private final NewBiomesGen plugin;

    public VoidCrystalBiome(NewBiomesGen plugin) {
        this.plugin = plugin;
        startEnvironmentTask();
    }

    private void startEnvironmentTask() {
        // הרצת משימה כל שניה (20 טרקים)
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (isInVoidCrystalBiome(player)) {
                    // יצירת חלקיקים סגולים סביב השחקן
                    player.getWorld().spawnParticle(
                            Particle.SPELL_WITCH, 
                            player.getLocation().add(0, 1, 0), 
                            15, 0.5, 0.5, 0.5, 0.05
                    );

                    // אפקט Darkness קל כשהם בתוך הביום
                    player.addPotionEffect(new PotionEffect(
                            PotionEffectType.DARKNESS, 40, 0, false, false, true
                    ));
                }
            }
        }, 0L, 20L);
    }

    private boolean isInVoidCrystalBiome(Player player) {
        // כרגע בדיקה זמנית לביום - בהמשך נחבר את זה ל-Chunk Generator / Biome Custom Key
        return player.getLocation().getBlock().getBiome().name().contains("DARK_FOREST") 
                || player.getScoreboardTags().contains("in_void_crystal");
    }
}
