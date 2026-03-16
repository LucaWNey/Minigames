package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.Plugin;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DiamantsGame {

    private final Diamants diamants;
    private final List<DPlayer> players;
    private final Deck deck;
    private int currentExpeditionNumber = 0;
    private Expedition currentExpedition;
    private int treasureCount = 0;

    public DiamantsGame(Diamants diamants) {
        this.diamants = diamants;
        this.players = Plugin.getInstance().getGameManager().getPlayingPlayers().stream().map(gamePlayer -> (DPlayer)gamePlayer).collect(Collectors.toList());
        this.deck = new Deck();
    }

    public void start() {
        deck.fillDefault();
        deck.shuffle();

        players.forEach(DPlayer::resetForNewRound);
    }

    public void startNextExpedition() {
        if (currentExpeditionNumber >= 5) {
            endGame();
            return;
        }

        List<DPlayer> activePlayers = players.stream()
                .filter(DPlayer::isAdvance)
                .filter(p -> p.getPlayer() != null && p.getPlayer().isOnline())
                .collect(Collectors.toList());

        if (activePlayers.isEmpty()) {
            endGame();
            return;
        }

        currentExpeditionNumber++;

        Bukkit.broadcastMessage(Plugin.getPrefix() + "§6§l=== EXPÉDITION #" + currentExpeditionNumber + " DÉMARRÉE ===");

        currentExpedition = new Expedition(this, currentExpeditionNumber, activePlayers);
        currentExpedition.start();
    }

    public void onExpeditionFinished() {
        List<DPlayer> remaining = currentExpedition.getPlayers();
        for (DPlayer player : players) {
            if (!remaining.contains(player)) {
                player.setAdvance(false);
            }
        }

        startNextExpedition();
    }

    public void endGame() {
        DPlayer winner = null;
        int maxDiamonds = 0;

        for (DPlayer player : players) {
            if (player.getDiamonds() > maxDiamonds) {
                maxDiamonds = player.getDiamonds();
                winner = player;
            }
        }

        Bukkit.broadcastMessage(Plugin.getPrefix() + "§6§l=== FIN DE LA PARTIE ===");
        if (winner != null) {
            Bukkit.broadcastMessage(Plugin.getPrefix() + "§6§lGAGNANT : §e" + winner.getPlayer().getName() +
                    " §7avec §e" + winner.getDiamonds() + " diamants§7!");
        }

        Bukkit.broadcastMessage(Plugin.getPrefix() + "§7Scores finaux :");
        for (DPlayer player : players) {
            String color = (player == winner) ? "§6" : "§7";
            Bukkit.broadcastMessage(Plugin.getPrefix() + color + player.getPlayer().getName() + " : " + player.getDiamonds() + " diamants");
        }

        players.forEach(DPlayer::resetForNewRound);
    }

    public DPlayer getDPlayer(org.bukkit.entity.Player player) {
        return players.stream()
                .filter(p -> p.getPlayerUuid().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }
}