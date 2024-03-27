package seedu.hirehub.logic.parser;

import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.commands.DeleteApplicationCommand;
import seedu.hirehub.logic.commands.InitDeleteApplicationCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class InitDeleteApplicationCommandParser implements Parser<InitDeleteApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InitDeleteApplicationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new InitDeleteApplicationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicationCommand.MESSAGE_USAGE), pe);
        }
    }

}
