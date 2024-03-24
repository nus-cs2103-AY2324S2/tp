package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import seedu.address.logic.commands.ChangeDataSourceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ChangeDataSourceParser implements Parser<ChangeDataSourceCommand>{
    /**
     * Parses the given {@code String} of arguments in the context of the CDCommand
     * and returns a CDCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeDataSourceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeDataSourceCommand.MESSAGE_USAGE));
        }
        Path newPath = Paths.get(args.trim());
        return new ChangeDataSourceCommand(newPath);
    }
}
