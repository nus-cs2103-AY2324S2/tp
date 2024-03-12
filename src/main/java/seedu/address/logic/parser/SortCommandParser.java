package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonHasTagPredicate;

import java.util.Arrays;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class SortCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String[] sortKeywords = trimmedArgs.split("\\s+");

        return new SortCommand(new PersonHasTagPredicate(Arrays.asList(sortKeywords)));
    }
}
