package seedu.TeachStack.logic.parser;

import static seedu.TeachStack.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.TeachStack.logic.commands.DeleteCommand;
import seedu.TeachStack.logic.parser.exceptions.ParseException;
import seedu.TeachStack.model.person.StudentId;

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
            StudentId id = ParserUtil.parseStudentId(args);
            return new DeleteCommand(id);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
