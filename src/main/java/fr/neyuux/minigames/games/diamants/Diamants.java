package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.GameClass;
import fr.neyuux.minigames.GameListener;
import fr.neyuux.minigames.GamePlayer;
import fr.neyuux.minigames.Games;
import fr.neyuux.minigames.listeners.DiamantsListener;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Diamants extends GameClass {

    private DiamantsGame currentGame;

    @Override
    public Games getGameEnum() {
        return Games.DIAMANTS;
    }

    @Override
    public GamePlayer createPlayer(Player player) {
        return new DPlayer(player);
    }

    @Override
    public Set<GameListener> getListeners() {
        return Collections.singleton(new DiamantsListener(this));
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public int getTeamsSize() {
        return 1;
    }

    public void startNewGame() {
        List<DPlayer> players = new ArrayList<>();

        for (GamePlayer gp : gameManager.getPlayers()) {
            if (gp instanceof DPlayer && gp.isOnline()) {
                players.add((DPlayer) gp);
            }
        }

        if (players.size() < 2) return;

        currentGame = new DiamantsGame(this, players);
        currentGame.start();
    }

    public DiamantsGame getCurrentGame() {
        return currentGame;
    }
}