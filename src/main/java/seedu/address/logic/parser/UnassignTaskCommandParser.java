package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.UnassignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignTaskCommand object
 */
public class UnassignTaskCommandParser implements Parser<UnassignTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTaskCommand
     * and returns an AssignTaskCommand object for execution.
     *
     * @param args The string containing the taskID and employeeID separated by a space.
     * @return A new AssignTaskCommand object with the parsed taskID and employeeID.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public UnassignTaskCommand parse(String args) throws ParseException {
        try {
            args = args.trim();
            String[] parts = args.split(" ");

            int arg1 = Integer.parseInt(parts[0]);
            int arg2 = Integer.parseInt(parts[1]);

            return new UnassignTaskCommand(arg1, arg2);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignTaskCommand.MESSAGE_USAGE));
        }
    }
}
