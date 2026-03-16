package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.Plugin;
import fr.neyuux.minigames.games.diamants.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck = new ArrayList<>();

    public void fillDefault()
    {
        this.deck.clear();
        this.deck.addAll(Diamants.CONFIG.getDefaultDeck());
    }

    public void shuffle()
    {
        Collections.shuffle(deck, Plugin.RANDOM);
    }

    public Card draw()
    {
        return deck.remove(0);
    }



}
