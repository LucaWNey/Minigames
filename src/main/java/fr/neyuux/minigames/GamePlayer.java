package fr.neyuux.minigames;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public abstract class GamePlayer {

    private final UUID playerUuid;
    private final List<String> waitingMessages = new ArrayList<>();

    public GamePlayer(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public GamePlayer(Player player) {
        this(player.getUniqueId());
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
