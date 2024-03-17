package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.FindAndExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindAndExportCommand object.
 */
public class FindAndExportCommandParser implements Parser<FindAndExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAndExportCommand
     * and returns a FindAndExportCommand object for execution.
     *
     * @param args The input arguments to be parsed.
     * @return The constructed FindAndExportCommand.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindAndExportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_FILENAME);
        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAndExportCommand.MESSAGE_USAGE));
        }
        String tag = argMultimap.getPreamble().trim();
        String name = argMultimap.getValue(PREFIX_NAME).orElse(null);
        String address = argMultimap.getValue(PREFIX_ADDRESS).orElse(null);
        String filename = argMultimap.getValue(PREFIX_FILENAME).orElse("default_filename.json");

        return new FindAndExportCommand(tag, name, address, filename);
    }
}
