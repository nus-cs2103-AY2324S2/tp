package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import seedu.address.logic.commands.ReadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReadCommand object
 */
public class ReadCommandParser implements Parser<ReadCommand> {
    /**
     * Parses the given {@code String} of argument in the context of the ReadCommand
     * and returns an ReadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReadCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC);

        if (argMultimap.getValue(PREFIX_NRIC).isEmpty()) {
            throw new ParseException(ReadCommand.MESSAGE_NOT_READ);
        }

        return new ReadCommand(ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()));
    }
}

