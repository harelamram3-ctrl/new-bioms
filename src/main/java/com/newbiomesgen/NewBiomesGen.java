package com.newbiomesgen;

import com.newbiomesgen.biomes.VoidCrystalBiome;
import com.newbiomesgen.bosses.VoidSentinelBoss;
import org.bukkit.plugin.java.JavaPlugin;

public final class NewBiomesGen extends JavaPlugin {

    private VoidSentinelBoss bossManager;

    @Override
    public void onEnable() {
        getLogger().info("NewBiomesGen starting up!");

        // הפעלת לוגיקת הביום
        new VoidCrystalBiome(this);

        // הפעלת הבוס והרשמת האיוונטים
        this.bossManager = new VoidSentinelBoss(this);
        getServer().getPluginManager().registerEvents(bossManager, this);

        getLogger().info("NewBiomesGen successfully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NewBiomesGen disabled.");
    }
}
