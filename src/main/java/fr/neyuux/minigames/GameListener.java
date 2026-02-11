package fr.neyuux.minigames;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class GameListener implements Listener {

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, Plugin.getInstance());
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
