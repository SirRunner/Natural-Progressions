package googlecsv;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Spell extends BaseSpell {
    public static final String LEVEL = "Level";
    public static final String SPELL = "Spell";
    public static final String SCHOOL_OF_MAGIC = "School of Magic";
    public static final String CASTING_TIME = "Casting Time";
    public static final String RANGE = "Range";
    public static final String VERBAL = "Verbal";
    public static final String SOMATIC = "Somatic";
    public static final String MATERIAL = "Material";
    public static final String DURATION = "Duration";
    public static final String EFFECT = "Effect";
    public static final String DAMAGE_TYPE = "Damage Type";
    public static final String ARTIFICER = "Artificer";
    public static final String BARD = "Bard";
    public static final String CLERIC = "Cleric";
    public static final String DRUID = "Druid";
    public static final String SORCERER = "Sorcerer";
    public static final String WIZARD = "Wizard";

    protected static Set<String> HANDLED_COLUMNS = new HashSet<>(Arrays.asList(LEVEL, SPELL, SCHOOL_OF_MAGIC, CASTING_TIME, RANGE, VERBAL, SOMATIC, MATERIAL, DURATION, EFFECT, DAMAGE_TYPE, ARTIFICER, BARD, CLERIC, DRUID, SORCERER, WIZARD));

    public Spell() {
        super();
    }

    @Override
    public Set<String> getHandledColumns() {
        return HANDLED_COLUMNS;
    }

    @Override
    public void setByName(String key, String value) {
        switch (key) {
            case LEVEL -> setLevel(value);
            case SPELL -> setName(value);
            case SCHOOL_OF_MAGIC -> setSchoolOfMagic(value);
            case CASTING_TIME -> setCastingTime(value);
            case RANGE -> setRange(value);
            case VERBAL -> setVerbal(value);
            case SOMATIC -> setSomatic(value);
            case MATERIAL -> setMaterial(value);
            case DURATION -> setDuration(value);
            case EFFECT -> setEffectRaw(value);
            case DAMAGE_TYPE -> setDamageTypes(value);
            case ARTIFICER, BARD, CLERIC, DRUID, SORCERER, WIZARD -> addClass(key, value);
        }
    }
}
