package googlecsv;

import constants.DamageType;
import constants.SchoolOfMagic;
import constants.SpellLevel;
import constants.SpellcasterClass;
import logging.Logger;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseSpell extends CSVObject {

    private SpellLevel level;
    private String name;
    private SchoolOfMagic schoolOfMagic;
    private String castingTime;
    private String range;
    private SpellComponents components;
    private String duration;
    private String effect;
    private String effectRaw;
    private Set<DamageType> damageTypes;
    private Set<SpellcasterClass> classes;

    public BaseSpell() {
        components = new SpellComponents();
        classes = new HashSet<>();
    }

    public SpellLevel getLevel() {
        return level;
    }

    public int getLevelAsInt() {
        return getLevel().getLevel();
    }

    public void setLevel(SpellLevel level) {
        this.level = level;
    }

    public void setLevel(int level) {

        SpellLevel spellLevel = SpellLevel.getByLevel(level);

        if (spellLevel == null) {
            Logger.error("Level " + level + " is not a valid spell level");
            return;
        }

        setLevel(spellLevel);

    }

    public void setLevel(String level) {

        if (!StringUtils.isNumeric(level)) {
            Logger.error("Level " + level + " is not numeric!");
            return;
        }

        setLevel(Integer.parseInt(level));

    }

    public int getManaCost() {
        return getLevel().getManaCost();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchoolOfMagic getSchoolOfMagic() {
        return schoolOfMagic;
    }

    public void setSchoolOfMagic(SchoolOfMagic schoolOfMagic) {
        this.schoolOfMagic = schoolOfMagic;
    }

    public void setSchoolOfMagic(String schoolOfMagic) {

        SchoolOfMagic magicSchool = SchoolOfMagic.getByName(schoolOfMagic);

        if (magicSchool == null) {
            Logger.error("School of magic " + schoolOfMagic + " is not a valid school!");
            return;
        }

        setSchoolOfMagic(magicSchool);

    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public SpellComponents getComponents() {
        return components;
    }

    public String getComponentsAsString() {
        return getComponents().toString();
    }

    public boolean isVerbal() {
        return getComponents().isVerbal();
    }

    public boolean isSomatic() {
        return getComponents().isSomatic();
    }

    public String getMaterials() {
        return getComponents().getMaterial();
    }

    public void setComponents(SpellComponents components) {
        this.components = components;
    }

    public void setVerbal(boolean verbal) {
        getComponents().setVerbal(verbal);
    }

    public void setVerbal(String verbal) {

        if (!isValidBoolean(verbal)) {
            Logger.error(verbal + " is not a valid boolean value for Verbal");
        }

        setVerbal(Boolean.parseBoolean(verbal));

    }

    public void setSomatic(boolean somatic) {
        getComponents().setSomatic(somatic);
    }

    public void setSomatic(String somatic) {

        if (!isValidBoolean(somatic)) {
            Logger.error(somatic + " is not a valid boolean value for Somatic");
        }

        setSomatic(Boolean.parseBoolean(somatic));

    }

    public void setMaterial(String material) {
        getComponents().setMaterial(material);
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEffect() {

        if (StringUtils.isEmpty(effect)) {

            effect = StringUtils.replace(effectRaw, "•", " - ");
            effect = StringUtils.replace(effect, "\\n", "\n:\n");
            effect = StringUtils.replace(effect, "|\n:\n|", "|\n|");
            effect = StringUtils.replace(effect, "\\t", "\t");

        }

        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getEffectRaw() {
        return effectRaw;
    }

    public void setEffectRaw(String effectRaw) {
        this.effectRaw = effectRaw;
    }

    public Set<DamageType> getDamageTypes() {

        if (damageTypes == null) {
            damageTypes = new HashSet<>();
        }

        return damageTypes;

    }

    public String getDamageTypesAsString() {

        List<String> damageTypes = getDamageTypes().stream().map(DamageType::getFormattedName).sorted().collect(Collectors.toList());

        return StringUtils.join(damageTypes, ", ");

    }

    public void setDamageTypes(Set<DamageType> damageTypes) {
        this.damageTypes = damageTypes;
    }

    public void setDamageTypes(String damageTypes) {

        String[] types = StringUtils.split(damageTypes, ",");

        for (String type : types) {
            addDamageType(type);
        }

    }

    public void addDamageType(String type) {

        DamageType damageType = DamageType.getByName(type);

        if (damageType == null) {
            Logger.error("Damage type " + type + " is invalid");
            return;
        }

        addDamageType(damageType);

    }

    public void addDamageType(DamageType damageType) {
        getDamageTypes().add(damageType);
    }

    public Set<SpellcasterClass> getClasses() {

        if (classes == null) {
            classes = new HashSet<>();
        }

        return classes;

    }

    public String getClassesAsString() {

        List<String> orderedClasses = getClasses().stream().map(SpellcasterClass::getFormattedName).sorted().collect(Collectors.toList());

        return StringUtils.join(orderedClasses, ", ");

    }

    public void setClasses(Set<SpellcasterClass> classes) {
        this.classes = classes;
    }

    public void addClass(SpellcasterClass castingClass) {
        getClasses().add(castingClass);
    }

    public void addClass(String castingClass) {

        SpellcasterClass classname = SpellcasterClass.getByName(castingClass);

        if (classname == null) {
            Logger.error("Unknown class " + castingClass);
            return;
        }

        addClass(classname);

    }

    public void addClass(String classname, String booleanValue) {

        if (!isValidBoolean(booleanValue)) {
            Logger.error(booleanValue + " is not a valid boolean value for " + classname + " flag");
        }

        addClass(classname, Boolean.parseBoolean(booleanValue));

    }

    public void addClass(String classname, boolean shouldAdd) {

        if (!shouldAdd) {
            return;
        }

        addClass(classname);

    }

    public static class SpellComponents {

        protected static final String VERBAL = "V";
        protected static final String SOMATIC = "S";
        protected static final String MATERIAL = "M";

        boolean verbal;
        boolean somatic;
        String material;

        public boolean isVerbal() {
            return verbal;
        }

        public void setVerbal(boolean verbal) {
            this.verbal = verbal;
        }

        public boolean isSomatic() {
            return somatic;
        }

        public void setSomatic(boolean somatic) {
            this.somatic = somatic;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        @Override
        public String toString() {

            List<String> components = new ArrayList<>();

            if (isVerbal()) {
                components.add(VERBAL);
            }

            if (isSomatic()) {
                components.add(SOMATIC);
            }

            if (StringUtils.isNotEmpty(getMaterial())) {
                components.add(MATERIAL + " (" + getMaterial() + ")");
            }

            return StringUtils.join(components, ",");
        }
    }
}
