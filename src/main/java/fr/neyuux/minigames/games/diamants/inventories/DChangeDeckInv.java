package fr.neyuux.minigames.games.diamants.inventories;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.neyuux.minigames.games.diamants.Diamants;
import fr.neyuux.minigames.games.diamants.DiamantsConfiguration;
import fr.neyuux.minigames.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DChangeDeckInv implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("diamants_config_deck_inv")
            .provider(new DChangeDeckInv())
            .size(6, 9)
            .title("§a§lConfig §2§lDeck")
            .closeable(true)
            .build();

    private final DiamantsConfiguration config = Diamants.CONFIG;


    @Override
    public void init(Player player, InventoryContents contents) {

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }

}