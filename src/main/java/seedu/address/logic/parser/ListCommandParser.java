package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL, PREFIX_ALIAS);


        List<Predicate<Person>> predicates = new ArrayList<>();

        if (!hasAtLeastOnePrefixPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL, PREFIX_ALIAS)
                || !argMultimap.getPreamble().isEmpty()) {
            // If there is no prefix specified, then display all records.
            // TODO: Show an error message here.
            return new ListCommand(PREDICATE_SHOW_ALL_PERSONS);
        }


        // All these criterias are OR not AND
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            predicates.add(new NameContainsKeywordsPredicate(Collections.singletonList(name.fullName)));
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
            predicates.add(new PhoneContainsKeywordsPredicate(Collections.singletonList(phone.value)));
        }


        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            String email = argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get();
            predicates.add(new EmailContainsKeywordPredicate(Collections.singletonList(email)));
        }

        // Combine predicates with AND logic
        Predicate<Person> combinedPredicate = predicates.stream()
                .reduce(p -> true, Predicate::and);

        return new ListCommand(combinedPredicate);
    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean hasAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
