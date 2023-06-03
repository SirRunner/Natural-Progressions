package constants;

import utils.StringUtils;

import java.util.Arrays;

public enum DamageType {
    ACID,
    BLUDGEONING,
    COLD,
    FIRE,
    FORCE,
    LIGHTNING,
    NECROTIC,
    PIERCING,
    POISON,
    PSYCHIC,
    RADIANT,
    SLASHING,
    THUNDER;

    private boolean magical;

    public boolean isMagical() {
        return magical;
    }

    public void setMagical(boolean magical) {
        this.magical = magical;
    }

    public static DamageType getByName(String name) {
        return Arrays.stream(values()).filter(type -> StringUtils.equalsIgnoreCase(StringUtils.deleteWhitespace(name), type.name())).findFirst().orElse(null);
    }

    public String getFormattedName() {
        return StringUtils.capitalize(StringUtils.lowerCase(name()));
    }
}
