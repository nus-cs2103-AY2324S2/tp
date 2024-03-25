package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteHousekeeperCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Type;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split(" ");
            Type type = ParserUtil.parseType(splitArgs[0]);
            Index index = ParserUtil.parseIndex(splitArgs[1]);
            if (type.equals(new Type("client"))) {
                return new DeleteClientCommand(index);
            } else if (type.equals(new Type("housekeeper"))) {
                return new DeleteHousekeeperCommand(index);
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
