package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports the information of Persons listed in the interface of the addressbook to a CSV file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Exported all currently listed person(s)'s information to a "
            + "CSV file. \n"
            + "CSV file can be found in addressbookdata file.";
    public static final String MESSAGE_NOTHING_TO_EXPORT_FAILURE = "Nothing to export.";
    public static final String MESSAGE_WRITE_TO_JSON_FAILURE = "Could not write information in filtered list to "
            + "JSON storage.";
    public static final String MESSAGE_JSON_FILE_NOT_FOUND_FAILURE = "Cannot find JSON file to export.";
    public static final String MESSAGE_PARSE_JSON_FILE_FAILURE = "Error parsing JSON data.";
    public static final String MESSAGE_MAPPING_JSON_TO_CSV_FAILURE = "Error mapping JSON data to CSV schema.";
    public static final String MESSAGE_EMPTY_JSON_FILE_FAILURE = "The JSON File is empty.";
    public static final String MESSAGE_CREATE_CSV_DIRECTORY_FAILURE = "Could not create directory for CSV file.";

    private Path filteredJsonFilePath = Paths.get("data", "filteredaddressbook.json");
    private String csvFilePath = "./addressbookdata/avengersassemble.csv";

    /**
     * Gets the current CSV file path.
     *
     * @return The file path that the data is being exported to.
     */
    public String getCsvFilePath() {
        return this.csvFilePath;
    }

    /**
     * Updates the CSV file path if needed.
     *
     * @param filePath The file path to change to.
     */
    public void updateCsvFilePath(String filePath) {
        this.csvFilePath = filePath;
    }

    /**
     * Gets the current JSON file path in which the filtered lists of persons will be written to.
     *
     * @return The file path that the filtered persons list is being written to.
     */
    public Path getFilteredJsonFilePath() {
        return this.filteredJsonFilePath;
    }

    /**
     * Updates the filtered JSON file path in which the filtered lists of persons will be written to.
     *
     * @param filePath The JSON file path to be written to.
     */
    public void updateFilteredJsonFilePath(Path filePath) {
        this.filteredJsonFilePath = filePath;
    }

    /**
     * Checks if the list is empty and throws an exception if the list is empty.
     *
     * @param personList The list in which to check if it is empty.
     * @throws CommandException If the list is empty.
     */
    public void checkForEmptyList(ObservableList<Person> personList) throws CommandException {
        if (personList.isEmpty()) {
            throw new CommandException(MESSAGE_NOTHING_TO_EXPORT_FAILURE);
        }
    }

    /**
     * Adds all persons in ObservableList to AddressBook.
     *
     * @param addressBook The address book where persons should be added into.
     * @param personList List containing persons to be added into address book.
     */
    public void addToAddressBook(AddressBook addressBook, ObservableList<Person> personList) {
        for (Person person : personList) {
            addressBook.addPerson(person);
        }
    }

    /**
     * Writes the information in the address book to a JSON file.
     *
     * @param jsonAddressBookStorage The JSON file to write information into.
     * @param addressBook The address book from which to read the information from.
     * @throws CommandException If information cannot be written into the JSON file.
     */
    public void writeToJsonFile(JsonAddressBookStorage jsonAddressBookStorage, AddressBook addressBook)
            throws CommandException {
        try {
            jsonAddressBookStorage.saveAddressBook(addressBook);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_WRITE_TO_JSON_FAILURE);
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
                throw new CommandException(MESSAGE_JSON_FILE_NOT_FOUND_FAILURE);
            } else if (e instanceof JsonParseException) {
                throw new CommandException(MESSAGE_PARSE_JSON_FILE_FAILURE);
            } else if (e instanceof JsonMappingException) {
                throw new CommandException(MESSAGE_MAPPING_JSON_TO_CSV_FAILURE);
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
            throw new CommandException(MESSAGE_EMPTY_JSON_FILE_FAILURE);
        }
        return personsArray;
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
                throw new CommandException(MESSAGE_CREATE_CSV_DIRECTORY_FAILURE);
            }
        }
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

        // The Code below removes examscores from the json tree
        // Potential future implementation: Export Command also exports exam scores

        // Create a new array node to hold the modified json nodes
        ArrayNode modifiedJsonTree = JsonNodeFactory.instance.arrayNode();
        // Iterate over each json node in the json tree
        for (JsonNode jsonNode : jsonTree) {
            // Create a copy of the json node
            ObjectNode modifiedJsonNode = ((ObjectNode) jsonNode).deepCopy();
            // Remove the 'examScores' field from the copy
            modifiedJsonNode.remove("examScores");
            // Add the modified json node to the new array node
            modifiedJsonTree.add(modifiedJsonNode);
        }

        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(csvFile, modifiedJsonTree);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            ObservableList<Person> filteredPersonObservableList = model.getFilteredPersonList();
            checkForEmptyList(filteredPersonObservableList);

            AddressBook filteredPersonAddressBook = new AddressBook();
            addToAddressBook(filteredPersonAddressBook, filteredPersonObservableList);

            JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filteredJsonFilePath);
            writeToJsonFile(jsonAddressBookStorage, filteredPersonAddressBook);

            File jsonFile = filteredJsonFilePath.toFile();
            File csvFile = new File(csvFilePath);

            JsonNode jsonTree = readJsonFile(jsonFile);
            JsonNode personsArray = readPersonsArray(jsonTree);

            createCsvDirectory(csvFile);

            writeToCsvFile(csvFile, personsArray);

            return new CommandResult(MESSAGE_SUCCESS);

        } catch (IOException e) {
            throw new CommandException("Error: " + e.getMessage());
        }
    }
}
