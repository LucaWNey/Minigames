package fr.neyuux.minigames.games.diamants.cards;

import lombok.Getter;

public abstract class Card {

    @Getter
    private CardType type;

    public enum CardType {
        DIAMONDS,
        DANGER,
        TREASURE
    }

}
