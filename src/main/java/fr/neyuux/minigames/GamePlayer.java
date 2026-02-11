package fr.neyuux.minigames;

import lombok.Data;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public abstract class GamePlayer {

    private final UUID playerUuid;

    public GamePlayer(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public GamePlayer(Player player) {
        this(player.getUniqueId());
    }

}
