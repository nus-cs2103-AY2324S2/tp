package scrolls.elder.logic.parser;

import static scrolls.elder.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static scrolls.elder.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Arrays;

import scrolls.elder.logic.commands.FindCommand;
import scrolls.elder.logic.parser.exceptions.ParseException;
import scrolls.elder.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    enum FindType {
        SEARCH_VOLUNTEER_ONLY,
        SEARCH_BEFRIENDEE_ONLY,
        SEARCH_BOTH
    }


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

        FindType findType = parseForRoles(trimmedArgs.split("\\s+"));

        String newArgs = args.replace("r/volunteer", "").replace("r/befriendee", "");
        String newTrimmedArgs = newArgs.trim();

        if (newTrimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] newNameKeywords = newTrimmedArgs.split("\\s+");

        if (findType.equals(FindType.SEARCH_BOTH)) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(newNameKeywords)),
                    true, true);
        } else if (findType.equals(FindType.SEARCH_VOLUNTEER_ONLY)) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(newNameKeywords)),
                    true, false);
        } else {
            assert findType.equals(FindType.SEARCH_BEFRIENDEE_ONLY);

            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(newNameKeywords)),
                    false, true);
        }

    }

    private static FindType parseForRoles(String[] nameKeywords) throws ParseException {
        boolean isSearchingVolunteer = false;
        boolean isSearchingBefriendee = false;

        // If both Volunteer and Befriendee roles are present, show error.
        if (Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "volunteer"))
                && Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "befriendee"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "volunteer"))) {
            isSearchingVolunteer = true;
            isSearchingBefriendee = false;
        }

        if (Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "befriendee"))) {
            isSearchingBefriendee = true;
            isSearchingVolunteer = false;
        }

        if (isSearchingVolunteer) {
            return FindType.SEARCH_VOLUNTEER_ONLY;
        } else if (isSearchingBefriendee) {
            return FindType.SEARCH_BEFRIENDEE_ONLY;
        } else {
            return FindType.SEARCH_BOTH;
        }

    }

}
