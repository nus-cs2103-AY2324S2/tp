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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path jsonFilePath = model.getAddressBookFilePath();
        File jsonFile = jsonFilePath.toFile();
        String csvFilePath = "./addressbookdata/addressbook.csv";
        File csvFile = new File(csvFilePath);

        File csvParentDirectory = csvFile.getParentFile();
        if (!csvParentDirectory.exists()) {
            boolean isCreated = csvParentDirectory.mkdir();
            if (!isCreated) {
                throw new CommandException("Could not create directory for CSV file.");
            }
        }

        try {
            JsonNode jsonTree = new ObjectMapper().readTree(jsonFile);

            JsonNode personsArray = jsonTree.get("persons");

            Builder csvSchemaBuilder = CsvSchema.builder();
            JsonNode firstObject = personsArray.elements().next();
            firstObject.fieldNames().forEachRemaining(fieldName -> {
                csvSchemaBuilder.addColumn(fieldName);
            });
            CsvSchema csvSchema = csvSchemaBuilder
                    .build()
                    .withHeader();

            CsvMapper csvMapper = new CsvMapper();
            csvMapper.writerFor(JsonNode.class)
                    .with(csvSchema)
                    .writeValue(csvFile, personsArray);

            return new CommandResult(MESSAGE_SUCCESS);

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
}
