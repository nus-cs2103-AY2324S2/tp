package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.AlwaysTruePredicate;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneMatchesPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);

        if (!argMultimap.getPreamble().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_NAME).isPresent() && !argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE);

        Predicate<Person> namePredicate;
        Predicate<Person> phonePredicate;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_NAME).get().trim();
            if (keyword.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String [] nameKeywords = keyword.split("\\s+");
            namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        } else {
            namePredicate = new AlwaysTruePredicate<Person>();
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phonePredicate = new PhoneMatchesPredicate(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        } else {
            phonePredicate = new AlwaysTruePredicate<Person>();
        }

        logger.info("----------------[namePredicate][" + namePredicate + "]");
        logger.info("----------------[phonePredicate][" + phonePredicate + "]");

        return new FindCommand(namePredicate, phonePredicate);
    }

}
