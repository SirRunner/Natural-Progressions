package googlecsv.reader;

import com.opencsv.CSVReaderHeaderAware;
import googlecsv.CSVObject;
import logging.Logger;
import utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseReader<T extends CSVObject> {
    protected File file;

    public BaseReader() {
    }

    public BaseReader(File file) {
        this.file = file;
    }

    public BaseReader(String filename) {
        this(new File(filename));
    }

    public List<T> readFile() throws Exception {

        if (getFile() == null) {
            throw new Exception("File was not defined!");
        }

        List<T> returnList = getReturnObject();

        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new InputStreamReader(new FileInputStream(getFile()), StandardCharsets.UTF_8))) {

            Logger.info("Reading from file " + getFile().getName());

            Map<String, String> line = reader.readMap();

            while (line != null) {

                T object = getObject(line);

                if (object != null) {
                    returnList.add(object);
                }

                line = reader.readMap();

            }

        }

        return returnList;

    }

    protected List<T> getReturnObject() {
        return new ArrayList<>();
    }

    protected abstract boolean shouldSkipLine(Map<String, String> line);

    protected abstract T getObject();

    protected T getObject(Map<String, String> line) {

        T object = getObject();

        for (String key : line.keySet()) {

            if (shouldSkipLine(line)) {
                return null;
            }

            String value = StringUtils.trim(line.get(key));

            if (StringUtils.containsIgnoreCase(object.getHandledColumns(), key) && StringUtils.isNotEmpty(value)) {
                object.setByName(key, StringUtils.trim(value));
            }
        }

        return object;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
