package fr.neyuux.minigames.interfaces;

import fr.neyuux.minigames.teams.GameTeam;

public interface Teams {

    Class<? extends GameTeam> getTeamClass();

}
