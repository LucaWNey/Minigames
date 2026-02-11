package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.GamePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DPlayer extends GamePlayer {
    public DPlayer(UUID playerUuid) {
        super(playerUuid);
    }

    public DPlayer(Player player) {
        super(player);
    }
}
