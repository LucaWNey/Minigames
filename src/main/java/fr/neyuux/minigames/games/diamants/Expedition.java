package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.games.diamants.cards.*;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import fr.neyuux.minigames.Plugin;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Expedition {

    private final DiamantsGame game;
    @Getter private final int expeditionNumber;
    @Getter private List<DPlayer> players;
    @Getter private int currentRound = 0;
    private final List<Card> dangersThisExpedition = new ArrayList<>();
    private final List<Card> cardsDrawn = new ArrayList<>();
    private boolean waitingForChoices = false;

    public Expedition(DiamantsGame game, int expeditionNumber, List<DPlayer> players) {
        this.game = game;
        this.expeditionNumber = expeditionNumber;
        this.players = new ArrayList<>(players);
    }

    public void start() {
        currentRound = 0;
        dangersThisExpedition.clear();
        cardsDrawn.clear();

        broadcast("§6§l=== EXPÉDITION #" + expeditionNumber + " ===");
        broadcast("§7" + players.size() + " joueurs participent");

        startNextRound();
    }

    private void startNextRound() {
        if (players.isEmpty()) {
            endExpedition();
            return;
        }

        currentRound++;
        waitingForChoices = true;

        for (DPlayer player : players) {
            player.getPlayer().sendMessage(ChatColor.GOLD + "--- Manche " + currentRound + " ---");
            player.getPlayer().sendMessage(ChatColor.YELLOW + "Tu as " + player.getDiamonds() + " diamants");
            player.getPlayer().sendMessage(ChatColor.GREEN + "Veux-tu continuer ? (on va foutre un bouton en vrai)");
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (waitingForChoices) {
                    waitingForChoices = false;
                    processRound();
                }
            }
        }.runTaskLater(Plugin.getInstance(), 200L); // 200 ticks = 10 secondes
    }

    public void handlePlayerChoice(DPlayer player, boolean wantsToContinue) {
        if (!waitingForChoices) return;

        player.setAdvance(wantsToContinue);
        player.getPlayer().sendMessage(wantsToContinue ?
                ChatColor.GREEN + "Tu continues l'expédition !" :
                ChatColor.RED + "Tu quittes l'expédition enculé !");
    }

    private void processRound() {
        if (!waitingForChoices) return;
        waitingForChoices = false;

        List<DPlayer> staying = players.stream()
                .filter(DPlayer::isAdvance)
                .collect(Collectors.toList());

        List<DPlayer> leaving = players.stream()
                .filter(p -> !p.isAdvance())
                .collect(Collectors.toList());

        for (DPlayer player : leaving) {
            player.getPlayer().sendMessage(ChatColor.GREEN +
                    "Tu repars avec " + player.getDiamonds() + " diamants !");
        }

        if (staying.isEmpty()) {
            endExpedition();
            return;
        }

        Card drawnCard = game.getDeck().draw();
        if (drawnCard == null) {
            game.endGame();
            return;
        }

        cardsDrawn.add(drawnCard);

        String cardMessage = getCardMessage(drawnCard);
        for (DPlayer player : staying) {
            player.getPlayer().sendMessage(cardMessage);
        }


        players = new ArrayList<>(staying);

        if (currentRound >= 5) {
            endExpedition();
        } else {
            startNextRound();
        }
    }

    private String getCardMessage(Card card) {
        if (card instanceof DangerCard) {
            return ChatColor.RED + "Carte piochée : DANGER - " + ((DangerCard) card).getType().name();
        } else if (card instanceof TreasureCard) {
            return ChatColor.GOLD + "Carte piochée : TRÉSOR (" + ((TreasureCard) card).getValue() + ")";
        } else if (card instanceof DiamondsCard) {
            return ChatColor.AQUA + "Carte piochée : DIAMANT (" + ((DiamondsCard) card).getDiamonds() + ")";
        }
        return ChatColor.WHITE + "Carte piochée : BITE";
    }

    private void applyCardEffect(Card card, List<DPlayer> staying, List<DPlayer> leaving) {
        if (card instanceof DangerCard) {
            applyDangerEffect((DangerCard) card, staying);
        } else if (card instanceof TreasureCard) {
            applyTreasureEffect((TreasureCard) card, staying, leaving);
        } else if (card instanceof DiamondsCard) {
            applyDiamondsEffect((DiamondsCard) card, staying, leaving);
        }
    }


    private void applyDangerEffect(DangerCard danger, List<DPlayer> players) {
        DangerCard.DangerCardType type = danger.getType();

        boolean alreadyMet = dangersThisExpedition.stream()
                .filter(c -> c instanceof DangerCard)
                .map(c -> (DangerCard) c)
                .anyMatch(d -> d.getType() == type);

        if (alreadyMet) {
            for (DPlayer player : players) {
                player.getPlayer().sendMessage(ChatColor.RED +
                        "§c§lC'est la deuxième fois que vous rencontrez la calamité " + type.name() + " ! Bonne chance ^^");
                        // La du coup va falloir faire du cas par cas?
                player.setAdvance(false);

            }
            this.players.removeAll(players);
        } else {
            dangersThisExpedition.add(danger);
            for (DPlayer player : players) {
                player.addDanger(type.name());


                //condition ou le joueur s'echappe
                player.getPlayer().sendMessage(ChatColor.YELLOW +
                        "§eVous avez survécu au " + type.name() + " !");
            }
        }
    }

    private void applyTreasureEffect(TreasureCard treasure, List<DPlayer> staying, List<DPlayer> leaving) {
        int treasureValue = treasure.getValue();

        if (staying.size() == 1 && leaving.isEmpty()) {
            DPlayer soloStayer = staying.get(0);
            soloStayer.addDiamonds(treasureValue);
            soloStayer.getPlayer().sendMessage(ChatColor.GOLD + "§6Tu es seul à rester ! Tu récupères tout le trésor : " + treasureValue + " diamants !");
            return;
        }

        if (leaving.size() == 1 && staying.isEmpty()) {
            DPlayer soloLeaver = leaving.get(0);
            soloLeaver.addDiamonds(treasureValue);
            soloLeaver.getPlayer().sendMessage(ChatColor.GOLD + "§6Tu pars seul(e) avec tout le trésor : " + treasureValue + " diamants !");
            return;
        }

        String message = "§7Le trésor de " + treasureValue + " diamants ne peut pas être partagé... Personne ne le prend.";

        for (DPlayer player : staying) {
            player.getPlayer().sendMessage(message);
        }
        for (DPlayer player : leaving) {
            player.getPlayer().sendMessage(message);
        }
    }

    private void applyDiamondsEffect(DiamondsCard diamonds, List<DPlayer> staying, List<DPlayer> leaving) {
        int totalValue = diamonds.getDiamonds();
        int totalPlayers = staying.size() + leaving.size();

        if (totalPlayers == 0) return;

        int sharePerPlayer = totalValue / totalPlayers;

        for (DPlayer player : staying) {
            player.addDiamonds(sharePerPlayer);
            player.getPlayer().sendMessage(ChatColor.GREEN + "§aTu reçois " + sharePerPlayer + " diamants");
        }

        int givenToStaying = sharePerPlayer * staying.size();
        int remainingForLeaving = totalValue - givenToStaying;

        if (!leaving.isEmpty() && remainingForLeaving > 0) {
            int shareForLeaving = remainingForLeaving / leaving.size();
            for (DPlayer player : leaving) {
                player.addDiamonds(shareForLeaving);
                player.getPlayer().sendMessage(ChatColor.GREEN + "§aEn partant, tu reçois " + shareForLeaving + " diamants");
            }
        }
    }

    private void endExpedition() {
        broadcast("§6§l=== EXPÉDITION #" + expeditionNumber + " TERMINÉE ===");


        game.onExpeditionFinished();
    }

    private void broadcast(String message) {
        players.stream()
                .filter(p -> p.getPlayer() != null && p.getPlayer().isOnline())
                .forEach(p -> p.getPlayer().sendMessage(message));
    }

    public List<DPlayer> getRemainingPlayers() {
        return new ArrayList<>(players);
    }
}