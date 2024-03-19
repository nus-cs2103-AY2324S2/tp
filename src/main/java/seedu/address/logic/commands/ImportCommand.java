package seedu.address.logic.commands;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

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

        return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, filePath));
    }

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
        } catch (Exception e) {
            throw new DataLoadingException(e);
        }
    }

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

    public String convertToAddCommandInput(Map<String, String> personData) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (String key : header) {
            // Maybe in the future, I can add a check to see if the value is empty
            // Maybe in the future, I make CliSyntax an enum class?
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
