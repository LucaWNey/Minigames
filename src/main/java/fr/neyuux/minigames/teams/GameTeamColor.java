package fr.neyuux.minigames.teams;

import lombok.Getter;
import org.bukkit.DyeColor;

@Getter
public enum GameTeamColor {

    RED("Red", "Rouge", "§c", DyeColor.RED),
    BLUE("Blue", "Bleu", "§9", DyeColor.BLUE),
    GREEN("Green", "Vert", "§a", DyeColor.LIME),
    LIGHT_PURPLE("Pink", "Rose", "§d", DyeColor.PINK),
    YELLOW("Yellow", "Jaune", "§e", DyeColor.YELLOW),
    AQUA("Light Blue","Bleu Clair", "§b", DyeColor.LIGHT_BLUE),
    GRAY("Gray", "Gris", "§7", DyeColor.SILVER),
    GOLD("Orange", "Orange", "§6", DyeColor.ORANGE),
    DARK_PURPLE("Purple", "Violet", "§5", DyeColor.PURPLE),
    DARK_RED("Dark Red", "Rouge Foncé", "§4", DyeColor.RED),
    DARK_BLUE("Dark Blue", "Bleu Foncé", "§1", DyeColor.BLUE),
    DARK_GREEN("Dark Green", "Vert Foncé", "§2", DyeColor.GREEN),
    DARK_AQUA("Cyan", "Cyan", "§3", DyeColor.CYAN),
    DARK_GRAY("Dark Gray", "Gris Foncé", "§8", DyeColor.GRAY),
    WHITE("White", "Blanc", "§f", DyeColor.WHITE);

    GameTeamColor(String name, String displayName, String color, DyeColor dc) {
        this.name = name;
        this.displayName = displayName;
        this.color = color;
        this.dyecolor = dc;
    }

    private static GameTeamColor CURRENT = GameTeamColor.values()[0];


    private final String name;
    private final String displayName;
    private final String color;
    private final DyeColor dyecolor;


    public static GameTeamColor getNext() {
        int ord = CURRENT.ordinal() + 1;
        GameTeamColor[] values = GameTeamColor.values();

        if (ord == values.length) ord = 0;

        CURRENT = values[ord];

        return CURRENT;
    }

    public static void resetNext() {
        CURRENT = GameTeamColor.values()[0];
    }
}
