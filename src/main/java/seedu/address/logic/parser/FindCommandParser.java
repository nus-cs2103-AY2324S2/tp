package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.*;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonFieldsContainKeywordPredicate;

public class FindCommandParser implements Parser<FindCommand> {
    
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_TAG);
        
        // This checks if at least one category-description pair or tag is present.
        boolean isCategoryDescriptionPresent = arePrefixesPresent(argMultimap, PREFIX_CATEGORY) && arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION);
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
                // Ensures a description is paired with each category.
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
    
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        // Checks that each prefix has at least one value associated with it.
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent() && !argumentMultimap.getValue(prefix).get().isEmpty());
    }
}

