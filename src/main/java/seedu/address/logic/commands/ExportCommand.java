package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Exports the information stored in the AddressBook to a CSV file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Exported all person's information to CSV file. \n"
            + "CSV file can be found in addressbookdata file.";

    public String csvFilePath = "./addressbookdata/addressbook.csv";

    /**
     * Updates the CSV file path if needed.
     *
     * @param filePath The file path to change to.
     */
    public void updateCsvFilePath(String filePath) {
        this.csvFilePath = filePath;
    }

    /**
     * Creates the directory for the CSV file if it does not exist.
     *
     * @param csvFile The CSV file for which the directory needs to be created.
     * @throws CommandException If directory fails to be created.
     */
    public void createCsvDirectory(File csvFile) throws CommandException {
        File csvParentDirectory = csvFile.getParentFile();
        if (!csvParentDirectory.exists()) {
            boolean isCreated = csvParentDirectory.mkdir();
            if (!isCreated) {
                throw new CommandException("Could not create directory for CSV file.");
            }
        }
    }

    /**
     * Read the JSON file and returns the contents as a JSON tree.
     *
     * @param jsonFile The JSON file to be read and mapped to the CSV schema.
     * @return The JSON tree representing the content of the JSON file.
     * @throws CommandException If an error occurs while reading the JSON file and mapping it to the CSV schema.
     */
    public JsonNode readJsonFile(File jsonFile) throws CommandException {
        try {
            JsonNode jsonTree = new ObjectMapper().readTree(jsonFile);
            return jsonTree;
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                throw new CommandException("Cannot find JSON file to export.");
            } else if (e instanceof JsonParseException) {
                throw new CommandException("Error parsing JSON data.");
            } else if (e instanceof JsonMappingException) {
                throw new CommandException("Error mapping JSON data to CSV schema.");
            } else {
                throw new CommandException("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Reads the persons array in the JSON file and returns the contents as a JSON tree.
     *
     * @param jsonTree The JSON tree where the persons array should be read from.
     * @return The JSON tree representing the content of the persons array in the release file.
     * @throws CommandException If the persons array in the JSON file is empty.
     */
    public JsonNode readPersonsArray(JsonNode jsonTree) throws CommandException {
        JsonNode personsArray = jsonTree.get("persons");
        if (personsArray == null || personsArray.size() == 0) {
            throw new CommandException("The JSON File is empty.");
        }
        return personsArray;
    }

    /**
     * Builds the CSV schema from the JSON array.
     *
     * @param jsonTree The JSON array to derive the schema from.
     * @return The CSV schema.
     */
    public CsvSchema buildCsvSchema(JsonNode jsonTree) {
        Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder
                .build()
                .withHeader();
        return csvSchema;
    }

    /**
     * Writes JSON data to a CSV file.
     *
     * @param csvFile The CSV file to write to.
     * @param jsonTree The JSON array to be written to CSV.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public void writeToCsvFile(File csvFile, JsonNode jsonTree) throws IOException {
        CsvSchema csvSchema = buildCsvSchema(jsonTree);

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(csvFile, jsonTree);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path jsonFilePath = model.getAddressBookFilePath();
        File jsonFile = jsonFilePath.toFile();
        File csvFile = new File(csvFilePath);

        createCsvDirectory(csvFile);

        try {
            JsonNode jsonTree = readJsonFile(jsonFile);
            JsonNode personsArray = readPersonsArray(jsonTree);
            writeToCsvFile(csvFile, personsArray);

            return new CommandResult(MESSAGE_SUCCESS);

        } catch (IOException e) {
            throw new CommandException("Error: " + e.getMessage());
        }
    }
}
