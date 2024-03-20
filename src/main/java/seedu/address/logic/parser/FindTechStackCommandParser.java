package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindTechStackCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.TsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTechStackCommand object
 */
public class FindTechStackCommandParser  implements Parser<FindTechStackCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTechStackCommand
     * and returns a FindTechStackCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTechStackCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTechStackCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindTechStackCommand(new TsContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
