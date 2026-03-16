package fr.neyuux.minigames;

import fr.neyuux.minigames.games.diamants.Diamants;
import lombok.Getter;

@Getter
public enum Games {
    NONE("§6§lMinigames", null,0,0,0),
    DIAMANTS("§a§lDiamants", Diamants.class, 0,0,0);

    private final String prefix;
    private final Class<? extends GameClass> clazz;
    private final double spawnX;
    private final double spawnY;
    private final double spawnZ;

    Games(String prefix, Class<? extends GameClass> clazz, double spawnX, double spawnY, double spawnZ) {
        this.prefix = prefix;
        this.clazz = clazz;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.spawnZ = spawnZ;
    }
}
