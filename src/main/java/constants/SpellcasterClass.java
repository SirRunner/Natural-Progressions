package constants;

import utils.StringUtils;

import java.util.Arrays;

public enum SpellcasterClass {
    ARTIFICER,
    BARD,
    CLERIC,
    DRUID,
    SORCERER,
    WIZARD;

    public static SpellcasterClass getByName(String name) {
        return Arrays.stream(values()).filter(spellcaster -> StringUtils.equalsIgnoreCase(spellcaster.name(), name)).findFirst().orElse(null);
    }

    public String getFormattedName() {
        return StringUtils.capitalize(StringUtils.lowerCase(name()));
    }
}
