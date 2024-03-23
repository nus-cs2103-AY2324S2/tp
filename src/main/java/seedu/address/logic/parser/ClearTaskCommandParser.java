package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ClearTaskCommand} object
 */
public class ClearTaskCommandParser implements Parser<ClearTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code ClearTaskCommand}
     * and returns a {@code ClearTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearTaskCommand.MESSAGE_USAGE), pe);
        }

        return new ClearTaskCommand(index);
    }

}
