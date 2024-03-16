package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Status;

/**
 * Parses input arguments and creates a new StatusCommand object
 */
public class StatusCommandParser implements Parser<StatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] parsedIndexStatus = args.trim().split(" ", 2);

        if (parsedIndexStatus.length < 2 || parsedIndexStatus[1].trim().equals("")) {
            throw new ParseException(StatusCommand.MESSAGE_USAGE);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(parsedIndexStatus[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE), pe);
        }

        Status status = ParserUtil.parseStatus(parsedIndexStatus[1]);

        return new StatusCommand(index, status);
    }
}
