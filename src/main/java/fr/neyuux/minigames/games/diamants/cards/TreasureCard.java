package fr.neyuux.minigames.games.diamants.cards;

public class TreasureCard extends Card {

    private static int treasureCount = 0;
    private final int value;

    public TreasureCard() {
        super(Card.CardType.TREASURE);
        treasureCount++;
        this.value = (treasureCount % 4 == 0) ? 10 : 5;
    }

    public int getValue() {
        return value;
    }
}