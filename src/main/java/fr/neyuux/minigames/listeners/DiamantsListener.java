package fr.neyuux.minigames.listeners;

import fr.neyuux.minigames.GameListener;
import fr.neyuux.minigames.games.diamants.Diamants;
import fr.neyuux.minigames.games.diamants.DPlayer;
import fr.neyuux.minigames.games.diamants.DiamantsGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class DiamantsListener extends GameListener {

    private final Diamants diamants;

    public DiamantsListener(Diamants diamants) {
        this.diamants = diamants;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        DiamantsGame game = diamants.getCurrentGame();

        if (game != null && game.isRunning()) {
            DPlayer dPlayer = game.getDPlayer(player);
            if (dPlayer != null && game.getCurrentExpedition() != null) {
                //CHais pas fais tes trucs de mrd
            }
        }
    }
}