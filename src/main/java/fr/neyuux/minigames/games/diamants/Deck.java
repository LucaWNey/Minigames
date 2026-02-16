package fr.neyuux.minigames.games.diamants;

import fr.neyuux.minigames.GamePlayer;
import fr.neyuux.minigames.Plugin;
import fr.neyuux.minigames.games.diamants.cards.Card;
import fr.neyuux.minigames.games.diamants.cards.DangerCard;
import fr.neyuux.minigames.games.diamants.cards.DiamondsCard;
import fr.neyuux.minigames.games.diamants.cards.TreasureCard;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck = new ArrayList<>();

    public void fillDefault()
    {
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

    public void shuffle()
    {
        Collections.shuffle(deck, Plugin.RANDOM);
    }

    public Card draw()
    {
        return deck.get(0);
    }



}
