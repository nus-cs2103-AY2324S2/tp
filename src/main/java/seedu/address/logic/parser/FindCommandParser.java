package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonFieldsContainKeywordPredicate;
/**
 * Parses input arguments and creates a new FindCommand object.
 * This class is responsible for handling the parsing of input arguments provided by the user
 * for the find command. It supports filtering persons by categories, descriptions, and tags.
 * The input arguments are expected to contain specific prefixes that denote the type of filter
 * to apply.
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * The method expects arguments to contain at least one valid prefix (category/description or tag).
     * Each category must have a corresponding description if specified. Tags can be listed
     * separately and are split by whitespace.
     *
     * @param args the input arguments provided by the user for the find command.
     * @return a FindCommand object ready for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_TAG);
        boolean isCategoryDescriptionPresent = arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
            && arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION);
        boolean isTagPresent = arePrefixesPresent(argMultimap, PREFIX_TAG);
        if (!isCategoryDescriptionPresent && !isTagPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Map<String, String> categoryDescriptionMap = new HashMap<>();
        Set<String> tags = new HashSet<>();
        if (isCategoryDescriptionPresent) {
            List<String> categories = argMultimap.getAllValues(PREFIX_CATEGORY);
            List<String> descriptions = argMultimap.getAllValues(PREFIX_DESCRIPTION);
            for (int i = 0; i < categories.size(); i++) {
                if (i < descriptions.size()) {
                    categoryDescriptionMap.put(categories.get(i).trim(), descriptions.get(i).trim());
                } else {
                    throw new ParseException("Each category must have a corresponding description.");
                }
            }
        }
        if (isTagPresent) {
            tags.addAll(Arrays.asList(argMultimap.getValue(PREFIX_TAG).get().split("\\s+")));
        }
        return new FindCommand(new PersonFieldsContainKeywordPredicate(categoryDescriptionMap, tags));
    }
    /**
     * Checks if the specified prefixes are present in the argument multimap and not empty.
     * This utility method is used to validate that the necessary prefixes for a command are provided
     * by the user and that they contain values.
     *
     * @param argumentMultimap the multimap of arguments parsed from the user's input.
     * @param prefixes the prefixes to check for presence and non-empty values.
     * @return true if all specified prefixes are present and contain non-empty values, otherwise false.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent()
            && !argumentMultimap.getValue(prefix).get().isEmpty());
    }
}

