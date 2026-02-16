package fr.neyuux.minigames.games.diamants.cards;

import lombok.Getter;

public class DangerCard extends Card {
    @Getter
    private final DangerCardType type;

    public DangerCard(DangerCardType type){
        this.type=type;
    }

    public enum DangerCardType {
        ARAIGNEE,
        DERZEK,
        POULET,
        LACHOSE,
        BACKROOM,
    }








}
