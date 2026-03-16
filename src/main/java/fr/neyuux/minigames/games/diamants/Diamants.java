package fr.neyuux.minigames.games.diamants;

import fr.minuskube.inv.SmartInventory;
import fr.neyuux.minigames.GameClass;
import fr.neyuux.minigames.GameListener;
import fr.neyuux.minigames.GamePlayer;
import fr.neyuux.minigames.Games;
import fr.neyuux.minigames.games.diamants.inventories.DiamantsConfigInv;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Set;

@Getter
public class Diamants extends GameClass {

    public static final DiamantsConfiguration CONFIG = new DiamantsConfiguration();

    private DiamantsGame currentGame;

    @Override
    public Games getGameEnum() {
        return Games.DIAMANTS;
    }

    @Override
    public SmartInventory getMainConfigInv() {
        return DiamantsConfigInv.INVENTORY;
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
        currentGame = new DiamantsGame(this);
        currentGame.start();
    }

}