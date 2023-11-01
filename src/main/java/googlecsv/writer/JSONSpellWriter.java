package googlecsv.writer;

import com.google.gson.Gson;
import googlecsv.JSONSpell;
import googlecsv.JSONSpellWrapper;

import java.util.Collections;

public class JSONSpellWriter extends BaseWriter<JSONSpellWrapper> {



    public JSONSpellWriter(String outputFilename) {

        super(outputFilename);

        this.addEntry(new JSONSpellWrapper());
    }

    @Override
    protected String getHeader() {
        return "";
    }

    public void addEntry(JSONSpell spell) {
        getEntries().get(0).addSpell(spell);
    }

    @Override
    protected String getEntryText(JSONSpellWrapper entry) {

        Gson gson = new Gson();

        return gson.toJson(entry);
    }

    @Override
    protected String getEntryDelimiter(JSONSpellWrapper entry) {
        return "\n";
    }

    @Override
    protected String getFooter() {
        return "";
    }
}
