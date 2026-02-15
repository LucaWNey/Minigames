package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.GamePlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DPlayer extends GamePlayer {

    @Getter @Setter
    private boolean next = true;

    @Getter
    private int diamonds = 0;

    @Getter
    private final List<String> dangersMeets = new ArrayList<>();

    public DPlayer(UUID playerUuid) {
        super(playerUuid);
    }

    public DPlayer(Player player) {
        super(player);
    }

    public void addDiamonds(int amount)
    {
        if (amount > 0) {
            this.diamonds += amount;
        }
    }

    public void resetForNewRound()
    {
        this.next = true;
        this.dangersMeets.clear();
    }

    public boolean hasMet(String danger)
    {
        return dangersMeets.contains(danger);
    }

    public void addDanger(String danger)
    {
        //condition ?
        this.dangersMeets.add(danger);
    }

    @Override
    public String toString()
    {
        return String.format("DPlayer{name=%s, diamonds=%d, next=%s}",
                getPlayer().getName(), diamonds, next);
    }
}