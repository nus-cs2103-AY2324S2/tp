package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameAndTagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        // Enforces /n and /t prefixes
        if (trimmedArgs.isEmpty() || (!trimmedArgs.startsWith("/n ") && !trimmedArgs.startsWith("/t "))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] splitArgs = trimmedArgs.split("/");
        Map<String, List<String>> keywordsMap = new HashMap<>();

        for (String arg : splitArgs) {
            String[] keyValue = arg.trim().split(" ", 2);
            if (keyValue.length < 2) {
                continue;
            }

            String key = keyValue[0].trim();
            String[] values = keyValue[1].trim().split("\\s+");

            // Check for duplicate prefixes
            if (keywordsMap.containsKey(key)) {
                throw new ParseException("Duplicate prefix /" + key + " is not allowed. \n"
                        + "If you want to pass multiple values for a single-valued field, "
                        + "please separate them with spaces."
                );
            }

            keywordsMap.put(key, Arrays.asList(values));
        }

        List<String> nameKeywords = keywordsMap.getOrDefault("n", Collections.emptyList());
        List<String> tagKeywords = keywordsMap.getOrDefault("t", Collections.emptyList());

        if (nameKeywords.isEmpty() && tagKeywords.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new NameAndTagContainsKeywordsPredicate(nameKeywords, tagKeywords));
    }
}
