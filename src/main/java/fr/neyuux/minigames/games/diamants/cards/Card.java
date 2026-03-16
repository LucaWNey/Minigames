package fr.neyuux.minigames.games.diamants.cards;

import lombok.Getter;

public abstract class Card {

    @Getter
    private final CardType type;

    public Card(CardType type) {
        this.type = type;
    }

    public enum CardType {
        DIAMONDS,
        DANGER,
        TREASURE
    }
}