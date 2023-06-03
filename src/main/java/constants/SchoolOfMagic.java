package constants;

import utils.StringUtils;

import java.util.Arrays;

public enum SchoolOfMagic {
    ABJURATION,
    CONJURATION,
    DIVINATION,
    ENCHANTMENT,
    EVOCATION,
    ILLUSION,
    MALEDICTION,
    NECROMANCY,
    TRANSMUTATION;

    public String lowerCase() {
        return StringUtils.lowerCase(name());
    }

    public static SchoolOfMagic getByName(String name) {
        return Arrays.stream(values()).filter(type -> StringUtils.equalsIgnoreCase(name, type.name())).findFirst().orElse(null);
    }
}
