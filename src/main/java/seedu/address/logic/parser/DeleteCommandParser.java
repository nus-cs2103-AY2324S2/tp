package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

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
        requireNonNull(args);
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        // Check if "uid" is part of the arguments and parse accordingly
        if (trimmedArgs.startsWith("uid/")) {
            return parseDeleteByUniqueId(trimmedArgs);
        }

        try {
            Index index = ParserUtil.parseIndex(trimmedArgs);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            // If parsing the index fails, try to parse as a name
            return parseDeleteByName(trimmedArgs);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @param args Arguments to be parsed
     * @return DeleteCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    private DeleteCommand parseDeleteByUniqueId(String args) throws ParseException {
        String[] splitArgs = args.split("/", 2);
        if (splitArgs.length != 2 || splitArgs[1].isEmpty() || !splitArgs[1].matches("[0-9]+")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        UniqueId uid = new UniqueId(splitArgs[1]);
        return new DeleteCommand(uid);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @param name Name of the employee to be deleted
     * @return DeleteCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    private DeleteCommand parseDeleteByName(String name) throws ParseException {
        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        return new DeleteCommand(name);
    }
}
