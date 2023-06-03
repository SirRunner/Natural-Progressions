package googlecsv;

import utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class CSVObject {

    public static final List<String> booleanValues = Arrays.asList("TRUE", "FALSE");

    public abstract Set<String> getHandledColumns();

    public abstract void setByName(String key, String value);

    protected static boolean isValidBoolean(String value) {
        return booleanValues.stream().anyMatch(booleanValue -> StringUtils.equalsIgnoreCase(value, booleanValue));
    }
}
