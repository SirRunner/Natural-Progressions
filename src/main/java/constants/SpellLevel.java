package constants;

import java.util.Arrays;

public enum SpellLevel {
    FIRST(1, 2),
    SECOND(2, 3),
    THIRD(3, 5),
    FOURTH(4, 8),
    FIFTH(5, 13),
    SIXTH(6, 21);

    private final int level;
    private final int manaCost;

    SpellLevel(int level, int manaCost) {

        this.level = level;
        this.manaCost = manaCost;

    }

    public int getLevel() {
        return level;
    }

    public int getManaCost() {
        return manaCost;
    }

    public static SpellLevel getByLevel(int level) {
        return Arrays.stream(values()).filter(spellLevel -> spellLevel.level == level).findFirst().orElse(null);
    }
}
