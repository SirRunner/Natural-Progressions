package utils;

import java.util.Collection;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static boolean containsIgnoreCase(Collection<String> collection, String str) {

        for (String entry: collection) {
            if (StringUtils.equalsIgnoreCase(entry, str)) {
                return true;
            }
        }

        return false;

    }
}
