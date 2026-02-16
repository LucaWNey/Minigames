package fr.neyuux.minigames.games.diamants.cards;

import lombok.Getter;

public class Card {

    @Getter
    private CardType type;

    public enum CardType {
        DIAMONDS,
        DANGER,
        TREASURE
    }

}
