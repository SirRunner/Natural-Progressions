import googlecsv.Spell;
import googlecsv.reader.SpellCSVReader;
import googlecsv.writer.HomebrewerySpellWriter;
import logging.Logger;
import utils.StringUtils;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class GoogleDriveSpellGenerator {

    protected String inFileName;
    protected String outFileName;
    protected String folderName;

    public String getInFileName() {
        return inFileName;
    }

    public void setInFileName(String inFileName) {
        this.inFileName = inFileName;
    }

    public String getOutFileName() {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public File getSourceFile() {

        return new File(getFolderName() + getInFileName());
    }

    public String getOutputFilename() {
        return getFolderName() + getOutFileName();
    }

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

        HomebrewerySpellWriter writer = new HomebrewerySpellWriter(getOutputFilename());

        spells.forEach(writer::addEntry);

        writer.writeFile();
    }

    protected boolean isValidConfiguration() {

        if (StringUtils.isEmpty(getInFileName())) {

            Logger.error("Infile filename is missing");
            return false;

        }

        if (StringUtils.isEmpty(getOutFileName())) {

            Logger.error("Outfile filename is missing");
            return false;

        }

        if (StringUtils.isEmpty(getFolderName())) {

            Logger.error("Folder is missing");
            return false;

        }

        if (!getSourceFile().exists()) {

            Logger.error("Source file does not exist");
            return false;

        }

        return true;

    }

    public static void main(String[] args) {

        try {

            GoogleDriveSpellGenerator generator = new GoogleDriveSpellGenerator();

            generator.setInFileName("NP Spells - NP Spells.csv");
            generator.setOutFileName("Spells.txt");
            generator.setFolderName(System.getProperty("user.home") + "\\Downloads\\");

            generator.run();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
