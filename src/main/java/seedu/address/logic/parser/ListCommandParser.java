package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;

public class ListCommandParser implements Parser<ListCommand>{
    @Override
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ALIAS);


        List<Predicate> predicates = new ArrayList<>();

        if (!hasAtLeastOnePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ALIAS)
                || !argMultimap.getPreamble().isEmpty()) {
            // If there is no prefix specified, then display all records.
            // TODO: Show an error message here.
            return new ListCommand(new NameContainsKeywordsPredicate(Arrays.asList("")), new PhoneContainsKeywordsPredicate(Arrays.asList("")));
        }


        List<String> nameKeywords = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            nameKeywords.add(name.fullName);
        } else {
            nameKeywords.add("");
        }

        List<String> phoneKeywords = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            phoneKeywords.add(phone.value);
        } else {
            phoneKeywords.add("");
        }



        // Else we just return all available results that meets the criteria
        return new ListCommand(new NameContainsKeywordsPredicate(nameKeywords), new PhoneContainsKeywordsPredicate(phoneKeywords));
    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean hasAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
