package googlecsv.reader;

import googlecsv.Spell;
import utils.StringUtils;

import java.io.File;
import java.util.Map;

public class SpellCSVReader extends BaseReader<Spell> {

    protected File file;

    public SpellCSVReader(File file) {
        super(file);
    }

    @Override
    protected boolean shouldSkipLine(Map<String, String> line) {
        return StringUtils.isEmpty(line.get(Spell.SPELL));
    }

    @Override
    public Spell getObject() {
        return new Spell();
    }
}
