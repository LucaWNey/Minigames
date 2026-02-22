package fr.neyuux.minigames.teams;

import fr.neyuux.minigames.GamePlayer;
import fr.neyuux.minigames.Plugin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public abstract class GameTeam {

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


    public void join(GamePlayer player) {
        this.players.forEach(gamePlayer -> gamePlayer.sendMessage(Plugin.getPrefix() + teamColor.getColor() + player.getName() + " a rejoint votre équipe !"));

        this.players.add(player);

        player.sendMessage(Plugin.getPrefix() + teamColor.getColor() + "Vous avez rejoint l'équipe " + teamColor.getDisplayName() + " !");
    }

    public void leave(GamePlayer player) {
        this.players.remove(player);

        player.sendMessage(Plugin.getPrefix() + teamColor.getColor() + "Vous avez quitté l'équipe " + teamColor.getDisplayName() + " !");

        this.players.forEach(gamePlayer -> gamePlayer.sendMessage(Plugin.getPrefix() + teamColor.getColor() + player.getName() + " a quitte votre équipe !"));
    }

    public void leaveAll() {
        for (GamePlayer player : new ArrayList<>(this.players)) {
            this.players.remove(player);
        }
    }

    public void sendMessage(String message) {
        this.players
                .stream()
                .filter(GamePlayer::isOnline)
                .forEach(gamePlayer -> gamePlayer.sendMessage(message));
    }
}
