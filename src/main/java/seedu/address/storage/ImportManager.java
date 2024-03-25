package seedu.address.storage;

import static seedu.address.commons.util.StringFormatter.capitalizeWords;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.util.FileUtil;

/**
 * Manages imports for the application.
 */
public class ImportManager implements Import {

    private Path pathToImportFrom;
    private Path pathToImportTo;

    /**
     * Creates an ImportManager with the specified paths.
     * @param pathToImportFrom Path to import from.
     * @param pathToImportTo Path to import to.
     */
    public ImportManager(Path pathToImportFrom, Path pathToImportTo) {
        this.pathToImportFrom = pathToImportFrom;
        this.pathToImportTo = pathToImportTo;
    }

    @Override
    public Path getPathToImportFrom() {
        return this.pathToImportFrom;
    }

    @Override
    public Path getPathToImportTo() {
        return this.pathToImportTo;
    }

    @Override
    public void setPathToImportTo(Path newPath) {
        this.pathToImportTo = newPath;
    }

    @Override
    public void setPathToImportFrom(Path newPath) {
        this.pathToImportFrom = newPath;
    }

    @Override
    public void importCsvFileAndConvertToJsonFile() throws IOException {
        String jsonString = convertCsvContentsToJsonContents();
        FileUtil.writeToFile(pathToImportTo, jsonString);
    }

    @Override
    public void importCsvFileAndAddToJsonFile() throws IOException {
        String existingJsonString = FileUtil.readFromFile(pathToImportTo).trim();
        Set<String> existingIds = extractStudentIds(existingJsonString);

        String newJsonContent = convertCsvContentsToJsonContents();
        ensureNoDuplicateIds(newJsonContent, existingIds); // Throws exception if duplicates are found

        // Correctly modify JSON strings for concatenation
        String modifiedExistingJson = existingJsonString.substring(0, existingJsonString.lastIndexOf("]")).trim();
        String modifiedNewJsonContent = newJsonContent.substring(
                newJsonContent.indexOf("[") + 1, newJsonContent.lastIndexOf("]")).trim();

        if (!modifiedExistingJson.endsWith("[") && !modifiedNewJsonContent.isEmpty()) {
            modifiedNewJsonContent = ", " + modifiedNewJsonContent;
        }

        String combinedJson = modifiedExistingJson + modifiedNewJsonContent + "\n]}";
        FileUtil.writeToFile(pathToImportTo, combinedJson);
    }

    private Set<String> extractStudentIds(String jsonString) {
        Set<String> ids = new HashSet<>();
        Pattern pattern = Pattern.compile("\"studentId\"\\s*:\\s*\"(\\d+)\"");
        Matcher matcher = pattern.matcher(jsonString);
        while (matcher.find()) {
            String id = matcher.group(1);
            ids.add(id);
        }
        return ids;
    }

    private void ensureNoDuplicateIds(String newJsonContent, Set<String> existingIds) throws IOException {
        Pattern pattern = Pattern.compile("\"studentId\": \"(\\d+)\"");
        Matcher matcher = pattern.matcher(newJsonContent);
        while (matcher.find()) {
            String newId = matcher.group(1);
            if (existingIds.contains(newId)) {
                throw new IOException("Duplicate StudentId found: " + newId);
            }
        }
    }

    /**
     * Converts the import csv contents to json contents to be stored.
     *
     * @return String to be stored in the json file.
     */
    private String convertCsvContentsToJsonContents() throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();
        String csvContents = FileUtil.readFromFile(pathToImportFrom);
        String[] lines = csvContents.split("\n");

        jsonStringBuilder.append("{ \"persons\": [");

        // Skip the first line (header) and process the rest
        for (int i = 1; i < lines.length; i++) {
            if (i > 1) {
                jsonStringBuilder.append(",");
            }
            jsonStringBuilder.append("\n").append(convertLineToJsonPerson(lines[i].trim()));
        }

        jsonStringBuilder.append("\n]}");
        return jsonStringBuilder.toString();
    }

    /**
     * Converts a line in the import csv to a json person.
     * @param line csv line to be converted
     * @return String representation of a person in correct json format.
     */
    private String convertLineToJsonPerson(String line) {
        String[] data = line.split(",");
        StringJoiner tagsJoiner = new StringJoiner("\", \"", "[\"", "\"]");
        if (data.length > 6) {
            for (String tag : data[6].split(";")) {
                tagsJoiner.add(capitalizeWords(tag.trim()));
            }
        }
        return String.format(
                "  { \"studentId\": \"%s\", \"name\": \"%s\", \"parentPhoneNumberOne\": \"%s\", "
                        + "\"parentPhoneNumberTwo\": \"%s\", \"email\": \"%s\", \"address\": \"%s\", \"tags\": %s }",
                data[0], capitalizeWords(data[1]), data[2], data[3], data[4], capitalizeWords(data[5]), tagsJoiner);
    }

}
