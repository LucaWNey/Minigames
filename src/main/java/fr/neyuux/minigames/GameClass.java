package fr.neyuux.minigames;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class GameClass {

    protected final GameManager gameManager = Plugin.getInstance().getGameManager();


    public abstract Games getGameEnum();

    public abstract GamePlayer createPlayer(Player player);

    public abstract Set<GameListener> getListeners();

    public abstract void onEnable();

    public abstract void onDisable();


    public void processSpectator(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        Games game = this.getGameEnum();

        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(gameManager.getPlayers()
                .stream()
                .filter(gamePlayer1 -> !gamePlayer1.isSpectator() && gamePlayer1.isOnline())
                .map(gamePlayer1 -> gamePlayer1.getPlayer().getLocation())
                .findAny()
                .orElse(new Location(Plugin.getInstance().getWorld(), game.getSpawnX(), game.getSpawnY(), game.getSpawnZ())));



    }

}
