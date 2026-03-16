package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.games.diamants.cards.Card;
import fr.neyuux.minigames.games.diamants.cards.DangerCard;
import fr.neyuux.minigames.games.diamants.cards.DiamondsCard;
import fr.neyuux.minigames.games.diamants.cards.TreasureCard;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DiamantsConfiguration {

    public DiamantsConfiguration() {
        this.fillDefaultDeck();
    }

    private List<Card> defaultDeck = new ArrayList<>();


    public void fillDefaultDeck() {
        List<Card> deck = this.defaultDeck;


        for (int i = 0; i < 5; i++) {
            deck.add(new TreasureCard());
        }

        deck.add(new DiamondsCard(1));
        deck.add(new DiamondsCard(2));
        deck.add(new DiamondsCard(3));
        deck.add(new DiamondsCard(4));
        deck.add(new DiamondsCard(5));
        deck.add(new DiamondsCard(5));
        deck.add(new DiamondsCard(7));
        deck.add(new DiamondsCard(7));
        deck.add(new DiamondsCard(9));
        deck.add(new DiamondsCard(11));
        deck.add(new DiamondsCard(11));
        deck.add(new DiamondsCard(13));
        deck.add(new DiamondsCard(14));
        deck.add(new DiamondsCard(15));
        deck.add(new DiamondsCard(17));
        deck.add(new DangerCard(DangerCard.DangerCardType.ARAIGNEE));
        deck.add(new DangerCard(DangerCard.DangerCardType.ARAIGNEE));
        deck.add(new DangerCard(DangerCard.DangerCardType.ARAIGNEE));
        deck.add(new DangerCard(DangerCard.DangerCardType.DERZEK));
        deck.add(new DangerCard(DangerCard.DangerCardType.DERZEK));
        deck.add(new DangerCard(DangerCard.DangerCardType.DERZEK));
        deck.add(new DangerCard(DangerCard.DangerCardType.POULET));
        deck.add(new DangerCard(DangerCard.DangerCardType.POULET));
        deck.add(new DangerCard(DangerCard.DangerCardType.POULET));
        deck.add(new DangerCard(DangerCard.DangerCardType.LACHOSE));
        deck.add(new DangerCard(DangerCard.DangerCardType.LACHOSE));
        deck.add(new DangerCard(DangerCard.DangerCardType.LACHOSE));
        deck.add(new DangerCard(DangerCard.DangerCardType.BACKROOM));
        deck.add(new DangerCard(DangerCard.DangerCardType.BACKROOM));
        deck.add(new DangerCard(DangerCard.DangerCardType.BACKROOM));
    }

}
