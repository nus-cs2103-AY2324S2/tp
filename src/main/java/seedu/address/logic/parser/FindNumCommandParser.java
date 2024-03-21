package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindNumCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsDigitsPredicate;

/**
 * Parses input arguments and creates a new FindNumCommand object
 */
public class FindNumCommandParser implements Parser<FindNumCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindNumCommand
     * and returns a FindNumCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindNumCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNumCommand.MESSAGE_USAGE));
        }

        String[] phoneKeywords = trimmedArgs.split("\\s+");

        // Check if all phone numbers in input are valid
        for (String phoneKeyword : phoneKeywords) {
            if (!Phone.isValidPhone(phoneKeyword)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNumCommand.MESSAGE_USAGE));
            }
        }

        return new FindNumCommand(new PhoneContainsDigitsPredicate(Arrays.asList(phoneKeywords)));
    }

}
