package fr.neyuux.minigames.games.diamants.inventories;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.neyuux.minigames.Plugin;
import fr.neyuux.minigames.games.diamants.Diamants;
import fr.neyuux.minigames.games.diamants.DiamantsConfiguration;
import fr.neyuux.minigames.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DiamantsConfigInv implements InventoryProvider {

    public static final ClickableItem GLASS_PANE = ClickableItem.empty(new CustomItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14).setDisplayName("§f").build());

    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("diamants_config_main_inv")
            .provider(new DiamantsConfigInv())
            .size(5, 9)
            .title("§c§lConfig §a§lDiamants")
            .closeable(true)
            .build();

    private final DiamantsConfiguration config = Diamants.CONFIG;


    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(0, 0, GLASS_PANE);
        contents.set(0, 1, GLASS_PANE);
        contents.set(0, 7, GLASS_PANE);
        contents.set(0, 8, GLASS_PANE);

        contents.set(1, 0, GLASS_PANE);
        contents.set(1, 8, GLASS_PANE);

        contents.set(3, 0, GLASS_PANE);
        contents.set(3, 8, GLASS_PANE);

        contents.set(4, 0, GLASS_PANE);
        contents.set(4, 1, GLASS_PANE);
        contents.set(4, 7, GLASS_PANE);
        contents.set(4, 8, GLASS_PANE);


        contents.set(1, 2, ClickableItem.of(new CustomItemStack(Material.ITEM_FRAME, 1)
                        .setDisplayName("§2Changer le §lDeck")
                        .setLore("§fPermet de changer le", "§ftype de jeu de la partie.", "", "§eActuel : §c§l" + config.getDefaultDeck().size())
                        .build()
                , onClick -> DChangeDeckInv.INVENTORY.open(player)));

        /*contents.set(3, 3, ClickableItem.of(new CustomItemStack(Material.SKULL_ITEM, 1, (byte)3)
                        .setDisplayName("§6Joueurs")
                        .setLore("§fPermet de gérer", "§fles joueurs", "§f§o(spectateur, etc)")
                        .setSkullOwner(player.getName())
                , onClick -> ListPlayersInv.INVENTORY.open((Player) onClick.getWhoClicked())));

        contents.set(3, 5, ClickableItem.of(new CustomItemStack(Material.BARRIER, 1)
                        .setDisplayName("§bReset la Map")
                        .setLore("§fPermet de reset", "§fla map.")
                , onClick -> ResetInv.INVENTORY.open(player)));

        contents.set(1, 4, ClickableItem.of(new CustomItemStack(Material.APPLE, 1)
                        .setDisplayName("§f§lParamètres de la Partie")
                        .setLore("§fPermet de changer les", "§foptions de la partie.")
                , onClick -> ParametersInv.INVENTORY.open(player)));

        contents.set(1, 6, ClickableItem.of(new CustomItemStack(Material.EMPTY_MAP, 1)
                        .setDisplayName("§6§lRôles")
                        .setLore("§fPermet de gérer les", "§frôles de la partie.")
                , onClick -> RoleDecksInv.INVENTORY.open(player)));*/
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        contents.set(4, 2, ClickableItem.empty(new CustomItemStack(Material.SIGN)
                .setDisplayName("§cListe des §lConfigurateurs")
                .setLore(Plugin.getInstance().getGameManager().getOPs().stream()
                        .map(human -> "§c" + human.getName())
                        .collect(Collectors.toCollection(ArrayList::new)))
                .build()));
    }

}