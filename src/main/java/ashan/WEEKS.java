package ashan;

import org.apache.commons.text.WordUtils;
import utils.StringUtils;

public enum WEEKS {
    NOTHING("No change"),
    ATHLETICS("+4 to all Athletics checks"),
    CONSTITUTION("+4 to all Constitution checks"),
    ENDURANCE("+4 to all Endurance checks"),
    MENTAL_FORTITUDE("+4 to all Mental Fortitude checks"),
    ACROBATICS("+4 to all Acrobatics checks"),
    REFLEX("+4 to all Reflex checks"),
    SLEIGHT_OF_HAND("+4 to all Sleight of Hand checks"),
    STEALTH("+4 to all Stealth checks"),
    ARCANA("+4 to all Arcana checks"),
    HISTORY("+4 to all History checks"),
    INVESTIGATION("+4 to all Investigation checks"),
    MEDICINE("+4 to all Medicine checks"),
    NATURE("+4 to all Nature checks"),
    RIDDLE("+4 to all Riddle checks"),
    TRADITION("+4 to all Tradition checks"),
    ANIMAL_HANDLING("+4 to all Animal Handling checks"),
    INSIGHT("+4 to all Insight checks"),
    PERCEPTION("+4 to all Perception checks"),
    SURVIVAL("+4 to all Survival checks"),
    DECEPTION("+4 to all Deception checks"),
    INTIMIDATION("+4 to all Intimidation checks"),
    PERFORMANCE("+4 to all Performance checks"),
    PERSUASION("+4 to all Persuasion checks"),
    ABJURATION("+4 to all Abjuration magic checks"),
    CONJURATION("+4 to all Conjuration magic checks"),
    DIVINATION("+4 to all Divination magic checks"),
    ENCHANTMENT("+4 to all Enchantment magic checks"),
    EVOCATION("+4 to all Evocation magic checks"),
    ILLUSION("+4 to all Illusion magic checks"),
    MALEDICTION("+4 to all Malediction magic checks"),
    NECROMANCY("+4 to all Necromancy magic checks"),
    TRANSMUTATION("+4 to all Transmutation magic checks"),
    ALCHEMY("+4 to all Alchemist's Supplies checks"),
    BREWING("+4 to all Brewer's Supplies checks"),
    CALLIGRAPHY("+4 to all Calligrapher's Supplies checks"),
    CARPETING("+4 to all Carpenter's Tools checks"),
    CARTOGRAPHY("+4 to all Cartographer's Tools checks"),
    CLIMBING("+4 to all Climber's Kit checks"),
    SHOEMAKING("+4 to all Cobblers's Tools checks"),
    COOKING("+4 to all Cook's Utensils checks"),
    DISGUISING("+4 to all Disguise Kit checks"),
    FISHING("+4 to all Fisher's Equipment checks"),
    FORGERY("+4 to all Forgery Kit checks"),
    GLASSBLOWING("+4 to all Glassblower's Tools checks"),
    JEWELERS("+4 to all Jeweler's Tools checks"),
    HEALING("+4 to all Healer's Kit checks"),
    HERBALISM("+4 to all Herbalism's Kit checks"),
    LEATHERWORKING("+4 to all Leatherworker's Tools checks"),
    MASONRY("+4 to all Mason's Tools checks"),
    NAVIGATING("+4 to all Nagivator's Tools checks"),
    PAINTING("+4 to all Painter's Supplies checks"),
    POISONING("+4 to all Poisoner's Kit checks"),
    CERAMICS("+4 to all Potter's Tools checks"),
    SMITHING("+4 to all Smith's Tools checks"),
    STEALING("+4 to all Thieve's Tools checks"),
    TINKERING("+4 to all Tinker's Tools checks"),
    WEAVING("+4 to all Weaver's Tools checks"),
    WOODCARVING("+4 to all Woodcarver's Tools checks"),
    MIGHT("All nonmagic damage dice that are rolled are rolled twice, and the higher result is taken"),
    MAGIC("All magic damage dice that are rolled are rolled twice, and the higher result is taken"),
    CABIR("All Cabirs get +2 to every d20 roll they make"),
    CENTAUR("All Centaurs get +2 to every d20 roll they make"),
    CORAL_NAGA("All Coral Nagas get +2 to every d20 roll they make"),
    DARK_ELF("All Dark Elves get +2 to every d20 roll they make"),
    DEEP_NAGA("All Deep Nagas get +2 to every d20 roll they make"),
    DWARF("All Dwarves get +2 to every d20 roll they make"),
    ELF("All Elves get +2 to every d20 roll they make"),
    GNOLL("All Gnolls get +2 to every d20 roll they make"),
    GOBLIN("All Goblins get +2 to every d20 roll they make"),
    GREMLIN("All Gremlins get +2 to every d20 roll they make"),
    HARPY("All Harpies get +2 to every d20 roll they make"),
    HUMAN("All Humans get +2 to every d20 roll they make"),
    KOBOLD("All Kobolds get +2 to every d20 roll they make"),
    LIZARDMEN("All Lizardmen get +2 to every d20 roll they make"),
    MEDUSA("All Medusae get +2 to every d20 roll they make"),
    MERFOLK("All Merfolks get +2 to every d20 roll they make"),
    MINOTAUR("All Minotaurs get +2 to every d20 roll they make"),
    ORC("All Orcs get +2 to every d20 roll they make"),
    PIXIE("All Pixies get +2 to every d20 roll they make"),
    SHARKMEN("All Sharkmen get +2 to every d20 roll they make"),
    TIEFLING("All Tiefling get +2 to every d20 roll they make"),
    LUCK("All crit ranges are doubled (i.e. 20-20 becomes 19-20, and 18-20 becomes 15-20"),
    ILL_FAVOR("All crit ranges are halved [minimum 20-20] (i.e. 19-20 becomes 20-20, and 15-20 becomes 18-20"),
    SPEED("All base speeds are increased by 10 feet"),
    DELAY("All base speeds are decreased by 10 feet (minimum 5 feet)"),
    RECOVERY("HP and Mana recovery from rests are doubled"),
    RESTLESSNESS("HP and Manag recovery from rests are halved"),
    DRAGONBLOOD("All spell costs are halved"),
    DISEASE("All roles for Athletics, Constitution, Endurance and Mental Fortitude are made with disadvantage"),
    ARTISAN("All Artisans get a +2 to every d20 roll they make"),
    ACTOR("All Actors get a +2 to every d20 roll they make"),
    NATURAL("All Naturals get a +2 to every d20 roll they make"),
    NOVITIATE("All Novitiates get a +2 to every d20 roll they make"),
    OUTCAST("All Outcasts get a +2 to every d20 roll they make"),
    WAR_PRIEST("All War Priests get a +2 to every d20 roll they make");

    private final String description;

    WEEKS(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Week of " + WordUtils.capitalize(StringUtils.lowerCase(name().replace("_", " "))) + ": " + getDescription();
    }
}
