package com.newbiomesgen;

import com.newbiomesgen.biomes.VoidCrystalBiome;
import com.newbiomesgen.bosses.VoidSentinelBoss;
import com.newbiomesgen.commands.NBGCommand;
import com.newbiomesgen.listeners.ItemListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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

        // הרשמת ה-Listeners החדשים לחפצים
        getServer().getPluginManager().registerEvents(new ItemListener(), this);

        // רישום הפקודה הראשית
        Objects.requireNonNull(getCommand("nbg")).setExecutor(new NBGCommand(bossManager));

        getLogger().info("NewBiomesGen successfully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NewBiomesGen disabled.");
    }
}
