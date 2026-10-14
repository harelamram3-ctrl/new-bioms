package com.newbiomesgen.biomes;

import org.bukkit.Particle;
import org.bukkit.potion.PotionEffectType;

public enum CustomBiomeType {
    VOLCANIC_CYBERPUNK("Volcanic Cyberpunk", Particle.FLAME, PotionEffectType.SPEED),
    GLACIAL_VOID("Glacial Void", Particle.SNOWFLAKE, PotionEffectType.SLOW),
    TOXIC_SPORE("Toxic Spore Jungle", Particle.SPORE_BLOSSOM_AIR, PotionEffectType.POISON),
    CHRONOS_WASTELAND("Chronos Wasteland", Particle.WAX_OFF, PotionEffectType.SLOW_FALLING),
    CRIMSON_BLOODLAND("Crimson Bloodland", Particle.CRIMSON_SPORE, PotionEffectType.INCREASE_DAMAGE),
    AETHERIAL_PEAKS("Aetherial Crystal Peaks", Particle.END_ROD, PotionEffectType.JUMP),
    SOUL_MARSH("Soul Shadow Marsh", Particle.SOUL, PotionEffectType.BLINDNESS),
    SOLAR_ECLIPSE("Solar Eclipse Desert", Particle.SMOKE_LARGE, PotionEffectType.NIGHT_VISION),
    LUMINOUS_OVERGROWTH("Luminous Overgrowth", Particle.VILLAGER_HAPPY, PotionEffectType.REGENERATION),
    INFERNAL_ABYSS("Infernal Void Abyss", Particle.DRIP_LAVA, PotionEffectType.FIRE_RESISTANCE),
    ABYSSAL_OCEAN("Abyssal Ocean", Particle.WATER_BUBBLE, PotionEffectType.WATER_BREATHING),
    THUNDERSTRUCK("Thunderstruck Badlands", Particle.CRIT, PotionEffectType.DAMAGE_RESISTANCE),
    DIAMOND_VALLEY("Diamond Vein Valley", Particle.ELECTRIC_SPARK, PotionEffectType.FAST_DIGGING),
    NEBULA_SKYLANDS("Nebula Skylands", Particle.PORTAL, PotionEffectType.LEVITATION),
    CURSED_HOLLOW("Cursed Pumpkin Hollow", Particle.SQUID_INK, PotionEffectType.CONFUSION),
    BREEZE_WOODS("Breeze Whispering Woods", Particle.CLOUD, PotionEffectType.SPEED),
    RADIOACTIVE_WASTELAND("Radioactive Wasteland", Particle.SLIME, PotionEffectType.HUNGER),
    DRAGON_GRAVEYARD("Dragon Graveyard", Particle.DRAGON_BREATH, PotionEffectType.WEAKNESS),
    FROSTFIRE_VOLCANO("Frostfire Volcano", Particle.SOUL_FIRE_FLAME, PotionEffectType.FIRE_RESISTANCE),
    ILLUSION_PRISM("Illusion Prism Realm", Particle.SPELL_MOB_AMBIENT, PotionEffectType.INVISIBILITY);

    private final String displayName;
    private final Particle particle;
    private final PotionEffectType effect;

    CustomBiomeType(String displayName, Particle particle, PotionEffectType effect) {
        this.displayName = displayName;
        this.particle = particle;
        this.effect = effect;
    }

    public String getDisplayName() { return displayName; }
    public Particle getParticle() { return particle; }
    public PotionEffectType getEffect() { return effect; }
}
