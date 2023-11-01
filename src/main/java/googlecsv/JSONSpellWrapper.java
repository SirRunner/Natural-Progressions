package googlecsv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONSpellWrapper {

    List<JSONSpell> entries;

    public JSONSpellWrapper() {
        this.entries = new ArrayList<>();
    }

    public void addSpell(JSONSpell spell) {
        entries.add(spell);
    }
}
