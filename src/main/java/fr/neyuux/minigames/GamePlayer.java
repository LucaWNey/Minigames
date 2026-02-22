package fr.neyuux.minigames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class GamePlayer {

    @EqualsAndHashCode.Include
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

    public @Nullable OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.playerUuid);
    }

    public boolean isOnline() {
        OfflinePlayer offlinePlayer = this.getOfflinePlayer();
        return offlinePlayer != null && offlinePlayer.isOnline();
    }

    public String getName() {
        OfflinePlayer offlinePlayer = this.getOfflinePlayer();
        return (offlinePlayer == null ? "PlaceHolder" : offlinePlayer.getName());
    }

    public void sendMessage(String message) {
        if (this.isOnline())
            Bukkit.getPlayer(this.playerUuid).sendMessage(message);
        else
            this.waitingMessages.add(message);
    }
}
