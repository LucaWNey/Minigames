package fr.neyuux.minigames.games.diamants.cards;

import lombok.Getter;

public class DiamondsCard extends Card {
    @Getter
    private final int diamonds;

    public DiamondsCard(int diamonds)
    {
        this.diamonds=diamonds;
    }
}