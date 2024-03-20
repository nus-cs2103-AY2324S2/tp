package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDIO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Changes the  of an existing person in the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Remark command not implemented yet";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports contacts from specified filepath "
            + "Existing contacts will.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_IMPORT + "import] "
            + "Example: " + COMMAND_WORD + PREFIX_IMPORT + "C:usr/lib/text.csv";
    private static final String MESSAGE_ARGUMENTS = "filePath: %s";
    private static final String MESSAGE_IMPORT_SUCCESS = "Imported Contacts from: %s";
    private static final String MESSAGE_DATA_LOAD_ERROR = "Unable to load data from %s";
    private static final String MESSAGE_PARSE_ERROR = "Invalid data format in %s";
    private final Path filePath;
    private final AddCommandParser addCommandParser = new AddCommandParser();

    /**
     * Represents the order of the data that should be parsed into the addCommandParser
     */
    private final String[] header = {"name", "phone", "email", "address", "matric", "reflection", "studio", "tags"};

    /**
     * Represents a mapping of String to prefix of the data that should be parsed into the addCommandParser.
     */
    private final Map<String, Prefix> prefixMap = Map.of(
            "name", PREFIX_NAME,
            "phone", PREFIX_PHONE,
            "email", PREFIX_EMAIL,
            "address", PREFIX_ADDRESS,
            "matric", PREFIX_MATRIC_NUMBER,
            "reflection", PREFIX_REFLECTION,
            "studio", PREFIX_STUDIO,
            "tags", PREFIX_TAG
    );

    /**
     * @param filePath absolute path of file (path starts from C:...)
     */
    public ImportCommand(Path filePath) {
        requireAllNonNull(filePath);

        this.filePath = filePath;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        try {
            List<Map<String, String>> data = readCsvFile();
            for (Map<String, String> personData : data) {
                try {
                    String addCommandInput = convertToAddCommandInput(personData);
                    AddCommand addCommand = parseAddCommand(addCommandInput);
                    addCommand.execute(model);
                } catch (ParseException e) {
                    throw new CommandException(String.format(MESSAGE_PARSE_ERROR, personData));
                } catch (CommandException e) {
                    throw new CommandException(e.getMessage());
                }
            }
        } catch (DataLoadingException e) {
            throw new CommandException(String.format(MESSAGE_DATA_LOAD_ERROR, filePath));
        }

        return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, filePath.toString()));
    }
    /**
     * Reads the csv file and returns a list of maps,
     * where each map represents a row of person's data in the csv file.
     * @throws DataLoadingException
     */
    public List<Map<String, String>> readCsvFile() throws DataLoadingException {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath.toString())).build();
            List<String[]> rows = reader.readAll();
            List<Map<String, String>> data = new ArrayList<>();
            String[] header = rows.get(0);
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < header.length; j++) {
                    map.put(header[j], row[j]);
                }
                data.add(map);
            }
            return data;
        } catch (IOException | CsvException e) {
            throw new DataLoadingException(e);
        }
    }

    /**
     * Converts a map of person data to a string that can be parsed by the addCommandParser
     * @param personData
     * @return
     */
    public String convertToAddCommandInput(Map<String, String> personData) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (String key : header) {
            // Maybe in the future, I can add a check to see if the value is empty
            // Maybe in the future, I make CliSyntax an enum class?
            if (personData.get(key).isEmpty()) {
                // skip empty values
                continue;
            }
            if (key.equals("tags")) {
                // tag is a special case, it can have multiple values
                String tags = personData.get(key);
                String[] tagArray = tags.split(";");
                for (String tag : tagArray) {
                    sb.append(prefixMap.get(key).getPrefix());
                    sb.append(tag);
                    sb.append(" ");
                }
            } else {
                sb.append(prefixMap.get(key).getPrefix());
                sb.append(personData.get(key));
            }
            sb.append(" ");
        }
        return sb.toString();
    }

    public AddCommand parseAddCommand(String input) throws ParseException {
        return addCommandParser.parse(input);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand e = (ImportCommand) other;
        return filePath.equals(e.filePath);
    }

}
