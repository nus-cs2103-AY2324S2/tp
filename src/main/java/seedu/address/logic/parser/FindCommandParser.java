package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.ConditionContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_CONDITION);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_CONDITION);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            trimmedArgs = trimmedArgs.substring(2); // removes the n/
            String[] nameKeywords = trimmedArgs.split("\\s+");
            List<String> list = Arrays.asList(nameKeywords);
            return new FindCommand(new NameContainsKeywordsPredicate(list));
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            trimmedArgs = trimmedArgs.substring(2); // removes the a/
            String[] addressKeywords = trimmedArgs.split(",");
            List<String> list = Arrays.asList(addressKeywords);
            return new FindCommand(new AddressContainsKeywordsPredicate(list));
        } else if (argMultimap.getValue(PREFIX_CONDITION).isPresent()) {
            trimmedArgs = trimmedArgs.substring(4); // removes the con/
            String[] conditionKeywords = trimmedArgs.split(",");
            List<String> list = Arrays.asList(conditionKeywords);
            return new FindCommand(new ConditionContainsKeywordsPredicate(list));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
