package fr.neyuux.minigames;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Plugin extends JavaPlugin {

    private static Plugin INSTANCE;
    private static final String PREFIX = "§6§lMinigames §8§l» §r";
    private static final String WORLD_NAME = "Minigames";


    private GameManager gameManager;
    private World world;


    @Override
    public void onEnable() {

        INSTANCE = this;

        this.gameManager = new GameManager();

        this.world = Bukkit.getWorld(WORLD_NAME);

        super.onEnable();
    }

    public static Plugin getInstance() {
        return INSTANCE;
    }

    public static String getPrefix() {
        return PREFIX;
    }
}
