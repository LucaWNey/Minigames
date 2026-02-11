package fr.neyuux.minigames;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Getter
public class GameManager {

    private Games currentGame = Games.NONE;
    private GameClass gameClass;


    public void closeGame() {
        this.gameClass.getListeners().forEach(GameListener::unregister);
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(new Location(Plugin.getInstance().getWorld(), Games.NONE.getSpawnX(), Games.NONE.getSpawnY(), Games.NONE.getSpawnZ())));


        this.gameClass = null;
        this.currentGame = Games.NONE;
    }

    public void launchGame(Games game) {

        if (this.currentGame != null)  {
            Bukkit.broadcastMessage(Plugin.getPrefix() + "§cImpossible de lancer un jeu sans être au lobby !");
            return;
        }

        try {
            this.gameClass = game.getClazz().newInstance();
            this.gameClass.getListeners().forEach(GameListener::register);
            Bukkit.getOnlinePlayers().forEach(player -> {

                player.teleport(new Location(Plugin.getInstance().getWorld(), game.getSpawnX(), game.getSpawnY(), game.getSpawnZ()));
                this.gameClass.createPlayer(player);

            });

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
