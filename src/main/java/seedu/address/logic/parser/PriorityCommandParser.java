package seedu.address.logic.parser;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.PriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Priority;

/**
 * Parses input arguments and creates a new {@code PriorityCommand} object
 */
public class PriorityCommandParser implements Parser<PriorityCommand> {

    private final String priority;

    public PriorityCommandParser(String priority) {
        this.priority = priority;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the PriorityCommand
     * and returns a PriorityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PriorityCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, PriorityCommand.MESSAGE_USAGE));
        }
        return new PriorityCommand(trimmedArgs, new Priority(priority));
    }
}
