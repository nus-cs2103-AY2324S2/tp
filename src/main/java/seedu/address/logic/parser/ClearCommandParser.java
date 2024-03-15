package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAR_CONFIRM;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     */
    public ClearCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLEAR_CONFIRM);

        if (argMultimap.contains(PREFIX_CLEAR_CONFIRM)) {
            return new ClearCommand(true);
        } else {
            return new ClearCommand(false);
        }
    }

}
