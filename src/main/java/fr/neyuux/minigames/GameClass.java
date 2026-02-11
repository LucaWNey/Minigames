package fr.neyuux.minigames;

import org.bukkit.entity.Player;

import java.util.Set;

public abstract class GameClass {

    public abstract Games getGameEnum();

    public abstract GamePlayer createPlayer(Player player);

    public abstract Set<GameListener> getListeners();

    public abstract void onEnable();

    public abstract void onDisable();

}
