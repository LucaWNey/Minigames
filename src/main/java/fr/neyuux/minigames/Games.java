package fr.neyuux.minigames;

import fr.neyuux.minigames.games.diamants.Diamants;
import lombok.Getter;

@Getter
public enum Games {
    NONE(null,0,0,0),
    DIAMANTS(Diamants.class, 0,0,0);

    private final Class<? extends GameClass> clazz;
    private final double spawnX;
    private final double spawnY;
    private final double spawnZ;

    Games(Class<? extends GameClass> clazz, double spawnX, double spawnY, double spawnZ) {
        this.clazz = clazz;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.spawnZ = spawnZ;
    }
}
