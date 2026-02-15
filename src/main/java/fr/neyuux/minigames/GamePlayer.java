package fr.neyuux.minigames;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public abstract class GamePlayer {

    private final UUID playerUuid;
    private final List<String> waitingMessages = new ArrayList<>();
    private boolean spectator;

    public GamePlayer(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public GamePlayer(Player player) {
        this(player.getUniqueId());
    }


    public @Nullable Player getPlayer() {
        return Bukkit.getPlayer(this.playerUuid);
    }

    public boolean isOnline() {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(this.playerUuid);
        return offlinePlayer != null && offlinePlayer.isOnline();
    }

    public void sendMessage(String message) {
        if (this.isOnline())
            Bukkit.getPlayer(this.playerUuid).sendMessage(message);
        else
            this.waitingMessages.add(message);
    }
}
