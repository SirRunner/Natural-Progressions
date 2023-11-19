import googlecsv.JSONSpell;
import googlecsv.Spell;
import googlecsv.reader.SpellCSVReader;
import googlecsv.writer.JSONSpellWriter;
import logging.Logger;
import utils.StringUtils;

import java.io.File;
import java.util.List;

public class GoogleDriveSpellGeneratorJSON extends GoogleDriveSpellGenerator {

    /* Overriding to use a different writer */
    @Override
    protected void run() throws Exception {

        if (!isValidConfiguration()) {
            throw new Exception("Invalid configuration");
        }

        SpellCSVReader reader = new SpellCSVReader(getSourceFile());

        List<Spell> spells = reader.readFile();

        spells.sort((spell1, spell2) -> {

            if (spell1.getLevelAsInt() == spell2.getLevelAsInt()) {
                return StringUtils.compare(spell1.getName(), spell2.getName());
            }

            return Integer.compare(spell1.getLevelAsInt(), spell2.getLevelAsInt());
        });

        JSONSpellWriter writer = new JSONSpellWriter(getOutputFilename());

        spells.stream().map(JSONSpell::new).forEach(writer::addEntry);

        writer.writeFile();
    }

    public static void main(String[] args) {

        try {

            GoogleDriveSpellGeneratorJSON generator = new GoogleDriveSpellGeneratorJSON();

            generator.setInFileName("NP Spells - NP Spells.csv");
            generator.setOutFileName("spells.json");
            generator.setFolderName(System.getProperty("user.home") + "\\Downloads\\");

            generator.run();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
