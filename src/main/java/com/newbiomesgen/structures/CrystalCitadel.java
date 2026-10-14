package com.newbiomesgen.structures;

import org.bukkit.Location;
import org.bukkit.Material;

public class CrystalCitadel {

    public static void buildAltar(Location center) {
        int radius = 3;

        // בסיס מזבח מ-Obsidian ו-Amethyst
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Location blockLoc = center.clone().add(x, -1, z);
                if (Math.abs(x) == radius || Math.abs(z) == radius) {
                    blockLoc.getBlock().setType(Material.OBSIDIAN);
                } else {
                    blockLoc.getBlock().setType(Material.AMETHYST_BLOCK);
                }
            }
        }

        // עמודי קריסטל בפינות
        center.clone().add(2, 0, 2).getBlock().setType(Material.BUDDING_AMETHYST);
        center.clone().add(2, 1, 2).getBlock().setType(Material.LARGE_AMETHYST_BUD);

        center.clone().add(-2, 0, 2).getBlock().setType(Material.BUDDING_AMETHYST);
        center.clone().add(-2, 1, 2).getBlock().setType(Material.LARGE_AMETHYST_BUD);

        center.clone().add(2, 0, -2).getBlock().setType(Material.BUDDING_AMETHYST);
        center.clone().add(2, 1, -2).getBlock().setType(Material.LARGE_AMETHYST_BUD);

        center.clone().add(-2, 0, -2).getBlock().setType(Material.BUDDING_AMETHYST);
        center.clone().add(-2, 1, -2).getBlock().setType(Material.LARGE_AMETHYST_BUD);
    }
}
