package fr.neyuux.minigames.items;

import fr.neyuux.minigames.GameClass;
import fr.neyuux.minigames.Plugin;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class OpComparatorItem extends CustomItemStack {

    public OpComparatorItem() {
        super(Material.REDSTONE_COMPARATOR);
        this.setDisplayName("§c§lConfiguration de la Partie");
        this.addGlowEffect();
        this.setUsable();
    }

    @Override
    public void use(HumanEntity player, Event event) {
        GameClass gameClass = Plugin.getInstance().getGameManager().getGameClass();
        if (gameClass != null) gameClass.getMainConfigInv().open((Player) player);
    }
}
