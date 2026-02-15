package fr.neyuux.minigames.teams;

import fr.neyuux.minigames.GamePlayer;

import java.util.ArrayList;
import java.util.List;

public class GameTeam {

    private final List<GamePlayer> players;
    private final GameTeamColor teamColor;


    public GameTeam(List<GamePlayer> players, GameTeamColor teamColor) {
        this.players = players;
        this.teamColor = teamColor;
    }

    public GameTeam(GameTeamColor teamColor) {
        this.teamColor = teamColor;
        this.players = new ArrayList<>();
    }


    public void sendMessage(String message) {
        this.players
                .stream()
                .filter(GamePlayer::isOnline)
                .forEach(gamePlayer -> gamePlayer.sendMessage(message));
    }
}
