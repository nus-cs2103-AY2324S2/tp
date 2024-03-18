package seedu.address.logic.parser;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {

    public ExportCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            return new ExportCommand();
        }
        String trimmedArgs = args.trim();
        if (!isValidFilename(trimmedArgs)) {
            throw new ParseException("Error: Invalid filename. Please provide a valid filename with the .csv extension.");
        }
        return new ExportCommand(trimmedArgs);
    }

    private boolean isValidFilename(String filename) {
        return filename.endsWith(".csv");
    }

}
