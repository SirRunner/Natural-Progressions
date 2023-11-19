package googlecsv;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JSONSpell {

    private int level;
    private int manaCost;
    private String name;
    private String schoolOfMagic;
    private String castingTime;
    private String range;
    private BaseSpell.SpellComponents components;
    private String duration;
    private List<String> effect;
    private List<String> damageTypes;
    private List<String> classes;

    public JSONSpell(Spell spell) {
        this.level = spell.getLevelAsInt();
        this.manaCost = spell.getManaCost();
        this.name = spell.getName();
        this.schoolOfMagic = StringUtils.capitalize(spell.getSchoolOfMagic().name().toLowerCase());
        this.castingTime = spell.getCastingTime();
        this.range = spell.getRange();
        this.components = spell.getComponents();
        this.duration = spell.getDuration();
        this.effect = Arrays.asList(spell.getEffectRaw().split("\\\\n"));
        this.damageTypes = spell.getDamageTypes().stream().map(Enum::name).map(String::toLowerCase).map(StringUtils::capitalize).sorted().collect(Collectors.toList());
        this.classes = spell.getClasses().stream().map(Enum::name).map(String::toLowerCase).map(StringUtils::capitalize).sorted().collect(Collectors.toList());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolOfMagic() {
        return schoolOfMagic;
    }

    public void setSchoolOfMagic(String schoolOfMagic) {
        this.schoolOfMagic = schoolOfMagic;
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

    public BaseSpell.SpellComponents getComponents() {
        return components;
    }

    public void setComponents(BaseSpell.SpellComponents components) {
        this.components = components;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getEffect() {
        return effect;
    }

    public void setEffect(List<String> effect) {
        this.effect = effect;
    }

    public List<String> getDamageTypes() {
        return damageTypes;
    }

    public void setDamageTypes(List<String> damageTypes) {
        this.damageTypes = damageTypes;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }
}
