package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.MailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class MailCommandParser implements Parser<MailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format where parameter is empty
     * @throws ParseException if the user input does not conform the expected group naming format
     */
    public MailCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new MailCommand();
        }

        String[] groupKeywords = trimmedArgs.split("\\s+");

        for (String keyword : groupKeywords) {
            if (!Group.isValidGroupName(keyword)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, Group.MESSAGE_CONSTRAINTS_KEYWORD));
            }
        }
        return new MailCommand(new GroupContainsKeywordsPredicate(Arrays.asList(groupKeywords)));
    }
}
