package fr.neyuux.minigames;

import io.github.pr0methean.betterrandom.prng.AesCounterRandom;
import io.github.pr0methean.betterrandom.seed.DefaultSeedGenerator;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

@Getter
public class Plugin extends JavaPlugin {

    private static Plugin INSTANCE;
    private static final String PREFIX = "§6§lMinigames §8§l» §r";
    private static final String WORLD_NAME = "Minigames";
    public static final AesCounterRandom RANDOM = new AesCounterRandom(DefaultSeedGenerator.DEFAULT_SEED_GENERATOR);


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
