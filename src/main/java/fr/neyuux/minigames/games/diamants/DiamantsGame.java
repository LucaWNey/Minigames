package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.games.diamants.Deck;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiamantsGame {

    private final Diamants diamants;
    @Getter private final List<DPlayer> players;
    @Getter private Deck deck;
    @Getter private int currentExpeditionNumber = 0;
    @Getter private Expedition currentExpedition;
    @Getter private boolean running = false;

    public DiamantsGame(Diamants diamants, List<DPlayer> players) {
        this.diamants = diamants;
        this.players = new ArrayList<>(players);
    }

    public void start() {
        deck = new Deck();
        deck.fillDefault();
        deck.shuffle();

        players.forEach(DPlayer::resetForNewRound);
        running = true;
    }

    public void startNextExpedition() {
        if (!running) return;

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

        broadcast("§6§l=== EXPÉDITION #" + currentExpeditionNumber + " DÉMARRÉE ===");

        currentExpedition = new Expedition(this, currentExpeditionNumber, activePlayers);
        currentExpedition.start();
    }

    public void onExpeditionFinished() {
        if (!running) return;

        List<DPlayer> remaining = currentExpedition.getPlayers();
        for (DPlayer player : players) {
            if (!remaining.contains(player)) {
                player.setAdvance(false);
            }
        }

        startNextExpedition();
    }

    public void endGame() {
        running = false;

        DPlayer winner = null;
        int maxDiamonds = 0;

        for (DPlayer player : players) {
            if (player.getDiamonds() > maxDiamonds) {
                maxDiamonds = player.getDiamonds();
                winner = player;
            }
        }

        broadcast("§6§l=== FIN DE LA PARTIE ===");
        if (winner != null) {
            broadcast("§6§lGAGNANT : §e" + winner.getPlayer().getName() +
                    " §7avec §e" + winner.getDiamonds() + " diamants§7!");
        }

        broadcast("§7Scores finaux :");
        for (DPlayer player : players) {
            String color = (player == winner) ? "§6" : "§7";
            broadcast(color + player.getPlayer().getName() + " : " + player.getDiamonds() + " diamants");
        }

        players.forEach(DPlayer::resetForNewRound);
    }

    public void broadcast(String message) {
        players.stream()
                .filter(p -> p.getPlayer() != null && p.getPlayer().isOnline())
                .forEach(p -> p.getPlayer().sendMessage(message));
    }

    public DPlayer getDPlayer(org.bukkit.entity.Player player) {
        return players.stream()
                .filter(p -> p.getPlayerUuid().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }
}