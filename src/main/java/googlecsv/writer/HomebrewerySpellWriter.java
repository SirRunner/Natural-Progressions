package googlecsv.writer;

import constants.SpellLevel;
import constants.SpellcasterClass;
import googlecsv.Spell;
import utils.IntegerUtils;

import java.util.*;
import java.util.stream.Collectors;

public class HomebrewerySpellWriter extends BaseWriter<Spell> {

    protected Map<SpellcasterClass, Map<SpellLevel, Set<String>>> classToSpellsListsByLevel;
    protected SpellLevel currentLevel = null;

    public HomebrewerySpellWriter(String outputFilename) {

        super(outputFilename);

        classToSpellsListsByLevel = new HashMap<>();

    }

    @Override
    public void addEntry(Spell entry) {

        super.addEntry(entry);

        for (SpellcasterClass casterClass : entry.getClasses()) {

            if (!classToSpellsListsByLevel.containsKey(casterClass)) {
                classToSpellsListsByLevel.put(casterClass, new HashMap<>());
            }

            Map<SpellLevel, Set<String>> levelToSpellList = classToSpellsListsByLevel.get(casterClass);
            SpellLevel level = entry.getLevel();

            if (!levelToSpellList.containsKey(level)) {
                levelToSpellList.put(level, new LinkedHashSet<>());
            }

            levelToSpellList.get(level).add(entry.getName());

        }
    }

    protected List<?> getSortedList(Collection<?> collection) {
        return collection.stream().sorted().collect(Collectors.toList());
    }

    @Override
    protected String getHeader() {
        return "## Spells\n\n";
    }

    protected void getClassList(SpellcasterClass castingClass, StringBuilder str, Map<SpellLevel, Set<String>> spellsByLevel) {

        str.append("{{spellList,wide\n");
        str.append("\n");
        str.append("### ").append(castingClass.getFormattedName()).append("\n");
        str.append("\n");

        for (Object levelAsObject : getSortedList(spellsByLevel.keySet())) {
            getSpellList((SpellLevel) levelAsObject, str, spellsByLevel.get(levelAsObject));
        }

        str.append("}}").append("\n");
        str.append("\n");
        str.append("{{pageNumber,auto}}\n");
        str.append("{{footnote Chapter VII: Magic and Spellcasting | Spell Lists }}\n");
        str.append("\n");
        str.append("\\page\n");
        str.append("\n");

    }

    protected void getSpellList(SpellLevel level, StringBuilder str, Set<String> spells) {

        str.append("##### Level ").append(level.getLevel()).append("\n");

        for (Object spellAsObject : getSortedList(spells)) {
            str.append(" - ").append((String) spellAsObject).append("\n");
        }

        str.append("\n");

    }

    @Override
    protected String getEntryText(Spell entry) {

        String header = "";

        if (currentLevel == null || currentLevel != entry.getLevel()) {

            if (currentLevel != null) {
                header += """
                        {{pageNumber,auto}}
                        {{footnote Chapter VII: Magic and Spellcasting | Casting A Spell }}

                        \\page

                        """;
            }

            currentLevel = entry.getLevel();
            header += "### Level " + entry.getLevelAsInt();
            header += "\n";
            header += "\n";

        }

        String body = "#### " + entry.getName() + "\n" +
                "*" + IntegerUtils.ordinal(entry.getLevelAsInt()) + "-level " + entry.getSchoolOfMagic().lowerCase() + "*\n" +
                "\n" +
                "**Casting Time:** \t\t\t\t\t\t\t\t\t\t\t\t:: " + entry.getCastingTime() + "\n" +
                "**Mana Cost:**\t\t\t\t\t\t\t\t\t\t\t\t\t\t:: " + entry.getManaCost() + " mana" + "\n" +
                "**Range:**        \t\t\t\t\t\t\t\t\t\t\t\t:: " + entry.getRange() + "\n" +
                "**Components:**   \t\t\t\t\t\t\t\t\t\t\t\t:: " + entry.getComponentsAsString() + "\n" +
                "**Duration:**     \t\t\t\t\t\t\t\t\t\t\t\t:: " + entry.getDuration() + "\n" +
                "**Magic Archetype Implementations:**\t\t\t:: " + entry.getClassesAsString() + "\n";

        if (!entry.getDamageTypes().isEmpty()) {
            body += "**Potential Damage Types:**\t\t\t\t\t\t\t\t:: " + entry.getDamageTypesAsString() + "\n";
        }

        return header + body +
                "\n" +
                entry.getEffect() + "\n";

    }

    @Override
    protected String getEntryDelimiter(Spell entry) {
        return "\n";
    }

    @Override
    protected String getFooter() {

        StringBuilder str = new StringBuilder();

        str.append("{{pageNumber,auto}}\n");
        str.append("{{footnote Chapter VII: Magic and Spellcasting | Spells }}\n");
        str.append("\n");
        str.append("\\page\n");
        str.append("\n");

        str.append("## Spell List\n");
        str.append("\n");

        for (Object castingClassAsObject : getSortedList(classToSpellsListsByLevel.keySet())) {
            getClassList((SpellcasterClass) castingClassAsObject, str, classToSpellsListsByLevel.get(castingClassAsObject));
        }

        str.append("\n");

        return str.toString();
    }
}
