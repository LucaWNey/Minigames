package fr.neyuux.minigames.games.diamants.cards;

import fr.neyuux.minigames.Plugin;
import fr.neyuux.minigames.games.diamants.Diamants;
import lombok.Getter;

@Getter
public class TreasureCard extends Card {


    private final int value;

    public TreasureCard() {
        super(Card.CardType.TREASURE);

        Diamants diamants = (Diamants) Plugin.getInstance().getGameManager().getGameClass();

        this.value = (diamants.getCurrentGame().getTreasureCount() == 2) ? 10 : 5;
    }

}