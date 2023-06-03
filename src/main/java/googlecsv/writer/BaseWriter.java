package googlecsv.writer;

import logging.Logger;
import utils.StringUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseWriter<T> {

    private List<T> entries;
    private String outputFilename;

    public BaseWriter(String outputFilename) {

        entries = new ArrayList<>();
        this.outputFilename = outputFilename;

    }

    public void addEntry(T entry) {
        entries.add(entry);
    }

    public List<T> getEntries() {

        if (entries == null) {
            entries = new ArrayList<>();
        }

        return entries;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    protected abstract String getHeader();

    protected abstract String getEntryText(T entry);

    protected abstract String getEntryDelimiter(T entry);

    protected abstract String getFooter();

    public void writeFile() {

        if (StringUtils.isEmpty(getOutputFilename())) {
            Logger.error("Output filename is empty!");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getOutputFilename())))) {

            writer.write(getHeader());

            for (T entry : getEntries()) {

                writer.write(getEntryText(entry));
                writer.write(getEntryDelimiter(entry));

            }

            writer.write(getFooter());

        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Failed to create output file " + getOutputFilename());
        }
    }
}
