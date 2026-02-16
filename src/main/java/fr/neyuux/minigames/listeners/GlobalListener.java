package fr.neyuux.minigames.listeners;

import fr.neyuux.minigames.GameListener;
import fr.neyuux.minigames.GameManager;
import fr.neyuux.minigames.GamePlayer;
import fr.neyuux.minigames.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

public class GlobalListener extends GameListener {

    private final GameManager gameManager = Plugin.getInstance().getGameManager();


    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {

        Player player = ev.getPlayer();
        Optional<GamePlayer> optionalGamePlayer = this.gameManager.getPlayer(player);

        Plugin.updateTab(player);

        if (optionalGamePlayer.isPresent()) {

            GamePlayer gamePlayer = optionalGamePlayer.get();

            gamePlayer.getWaitingMessages().forEach(player::sendMessage);
            gamePlayer.getWaitingMessages().clear();

             if (gamePlayer.isSpectator()) {

                gameManager.processSpectator(gamePlayer);

             }

        }

    }
}
