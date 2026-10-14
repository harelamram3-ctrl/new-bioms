package com.newbiomesgen;

import org.bukkit.plugin.java.JavaPlugin;

public final class NewBiomesGen extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("NewBiomesGen has been enabled successfully!");
        // כאן נרשום את ה-Biomes, המבנים והבוסים הראשונים שלנו
    }

    @Override
    public void onDisable() {
        getLogger().info("NewBiomesGen has been disabled.");
    }
}
