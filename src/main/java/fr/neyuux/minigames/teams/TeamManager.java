package fr.neyuux.minigames.teams;

import fr.neyuux.minigames.GameManager;
import fr.neyuux.minigames.GamePlayer;
import fr.neyuux.minigames.Plugin;
import fr.neyuux.minigames.interfaces.Teams;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamManager {

    public final List<GameTeam> teams = new ArrayList<>();

    private final GameManager gameManager = Plugin.getInstance().getGameManager();


    public void createTeam(List<GamePlayer> players, GameTeamColor color) {

        if (!(gameManager.getGameClass() instanceof Teams)) {
            Bukkit.getLogger().severe("Impossible de creer une team dans un mode sans Teams");
            return;
        }

        try {
            GameTeam team = ((Teams)this.gameManager.getGameClass()).getTeamClass().getConstructor(List.class, GameTeamColor.class).newInstance(players, color);

            teams.add(team);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Bukkit.broadcastMessage(Plugin.getPrefix() + "§4[§cErreur§4] §cUn problème est survenu lors de la création d'une team.");
            throw new RuntimeException(e);
        }
    }

    public void createTeam(GameTeamColor color) {
        this.createTeam(new ArrayList<>(), color);
    }

    public void createTeam() {
        this.createTeam(GameTeamColor.getNext());
    }

    public void removeTeam(GameTeam team) {

        this.teams.remove(team);
    }

}
