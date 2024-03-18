package seedu.address.logic.parser;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new Exportommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {
    public static final String MESSAGE_INVALID_FILENAME = "Error: Invalid filename. " +
            "Please provide a valid filename with the .csv extension.";

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     *
     * @param args The user input string containing the filename for export.
     * @return An ExportCommand object representing the parsed command.
     * @throws ParseException if the user input does not conform to the expected format
     *                        or if the filename is invalid.
     */
    public ExportCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            return new ExportCommand();
        }
        String trimmedArgs = args.trim();
        if (!isValidFilename(trimmedArgs)) {
            throw new ParseException(MESSAGE_INVALID_FILENAME);
        }
        return new ExportCommand(trimmedArgs);
    }

    private boolean isValidFilename(String filename) {
        return filename.endsWith(".csv");
    }

}
