package seedu.hirehub.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.commands.DeleteApplicationCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ApplicationCommand object
 */
public class DeleteApplicationCommandParser implements Parser<DeleteApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicationCommand
     * and returns a DeleteApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteApplicationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteApplicationCommand.MESSAGE_USAGE), pe);
        }
    }
}
