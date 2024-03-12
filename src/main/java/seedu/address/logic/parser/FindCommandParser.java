package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

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
        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> nameKeywords = new ArrayList<>();
        List<String> tagKeywords = new ArrayList<>();

        // Initial split by the prefixes " n/" and " t/" to handle names with spaces
        String[] parts = args.trim().split("(?= n/)|(?= t/)");

        for (String part : parts) {

            String[] splitParts = part.trim().split("\\s+", 2);
            String prefix = splitParts[0];

            if (splitParts.length < 2 || splitParts[1].trim().isEmpty()) {
                // If there is no keyword after the prefix OR it is empty
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String keyword = splitParts[1].trim();


            if (keyword.contains("/")) {
                throw new ParseException("Keywords must NOT contain `/`. \n"
                        + "'/' is a special character reserved for commands. \n");
            }

            if (prefix.equals("n/")) {
                if (keyword.contains(" ")) {
                    throw new ParseException("Names should not contain spaces. Use 'n/' prefix for EACH name.");
                }

                if (!keyword.matches("[a-zA-Z]+")) {
                    throw new ParseException("Name keywords must consist of only alphabets.");
                }

                nameKeywords.add(keyword);
            } else if (prefix.equals("t/")) {
                if (keyword.contains(" ")) {
                    throw new ParseException("Tags should not contain spaces. Use 't/' prefix for EACH tag.");
                }

                tagKeywords.add(keyword);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

        if (nameKeywords.isEmpty() && tagKeywords.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        System.out.println(nameKeywords);
        System.out.println(tagKeywords);

        return new FindCommand(new NameAndTagContainsKeywordsPredicate(nameKeywords, tagKeywords));
    }

}
