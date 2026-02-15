package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.GameClass;
import fr.neyuux.minigames.GameListener;
import fr.neyuux.minigames.GamePlayer;
import fr.neyuux.minigames.Games;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Set;

public class Diamants extends GameClass {


    @Override
    public Games getGameEnum() {
        return Games.DIAMANTS;
    }

    @Override
    public GamePlayer createPlayer(Player player) {
        DPlayer dPlayer = new DPlayer(player);
        return dPlayer;
    }

    @Override
    public Set<GameListener> getListeners() {
        return Collections.emptySet();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}
