package com.newbiomesgen;

import com.newbiomesgen.biomes.BiomeManager;
import com.newbiomesgen.biomes.VoidCrystalBiome;
import com.newbiomesgen.bosses.VoidSentinelBoss;
import com.newbiomesgen.commands.NBGCommand;
import com.newbiomesgen.listeners.ItemListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NewBiomesGen extends JavaPlugin {

    private VoidSentinelBoss bossManager;
    private BiomeManager biomeManager;

    @Override
    public void onEnable() {
        getLogger().info("NewBiomesGen starting up!");

        // 1. הפעלת מנהל 20 הביומים החדשים
        this.biomeManager = new BiomeManager(this);

        // 2. הפעלת לוגיקת ביום ה-Void הבסיסי
        new VoidCrystalBiome(this);

        // 3. הפעלת הבוס והרשמת האיוונטים
        this.bossManager = new VoidSentinelBoss(this);
        getServer().getPluginManager().registerEvents(bossManager, this);

        // 4. הרשמת מאזין האירועים לחפצים המיוחדים
        getServer().getPluginManager().registerEvents(new ItemListener(), this);

        // 5. רישום הפקודה הראשית (עם תמיכה בביומים)
        Objects.requireNonNull(getCommand("nbg")).setExecutor(new NBGCommand(bossManager));

        getLogger().info("NewBiomesGen successfully loaded with 20 custom biomes!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NewBiomesGen disabled.");
    }
}
