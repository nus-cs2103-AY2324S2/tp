package seedu.address.logic.parser;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {
    public static final String MESSAGE_NO_EXTENSION = "Error: Invalid filename. "
            + "Please provide a valid filename with the .csv extension.";

    public static final String MESSAGE_SPACE_DETECTED = "Error: Invalid filename. "
            + "Please ensure the filename does not contain leading, trailing spaces, or end with a period.";

    public static final String MESSAGE_NO_SPECIAL_CHARACTER = "Error: Invalid filename. "
            + "Please ensure the filename does not contain any of the following characters: < > : \" / \\ | ? *";


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
            throw new ParseException(getErrorMessage(trimmedArgs));
        }
        return new ExportCommand(trimmedArgs);
    }

    private boolean isValidFilename(String filename) {
        // Check if filename ends with ".csv"
        if (!filename.endsWith(".csv")) {
            return false;
        }

        // Check for leading, trailing spaces, or period at the end of filename
        if (filename.startsWith(" ") || filename.endsWith(" ") || filename.endsWith(".")) {
            return false;
        }

        // Check for special characters
        String restrictedChars = "<>:\"/\\|?*";
        for (char c : restrictedChars.toCharArray()) {
            if (filename.indexOf(c) != -1) {
                return false;
            }
        }

        return true;
    }

    private String getErrorMessage(String filename) {
        if (!filename.endsWith(".csv")) {
            return MESSAGE_NO_EXTENSION;
        }

        if (filename.startsWith(" ") || filename.endsWith(" ") || filename.endsWith(".")) {
            return MESSAGE_SPACE_DETECTED;
        }

        String restrictedChars = "<>:\"/\\|?*";
        for (char c : restrictedChars.toCharArray()) {
            if (filename.indexOf(c) != -1) {
                return MESSAGE_NO_SPECIAL_CHARACTER;
            }
        }

        return null;
    }
}

