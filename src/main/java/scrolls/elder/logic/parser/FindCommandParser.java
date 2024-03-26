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
        VOLUNTEER_ONLY,
        BEFRIENDEE_ONLY,
        BOTH
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

        if (findType.equals(FindType.BOTH)) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(newNameKeywords)),
                    true, true);
        } else if (findType.equals(FindType.VOLUNTEER_ONLY)) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(newNameKeywords)),
                    true, false);
        } else {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(newNameKeywords)),
                    false, true);
        }

    }

    private static FindType parseForRoles(String[] nameKeywords) throws ParseException{
        boolean searchVolunteer = false;
        boolean searchBefriendee = false;

        // If both Volunteer and Befriendee roles are present, show error.
        if (Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "volunteer"))
                && Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "befriendee"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "volunteer"))) {
            searchVolunteer = true;
            searchBefriendee = false;
        }

        if (Arrays.stream(nameKeywords).anyMatch(string -> string.equals(PREFIX_ROLE + "befriendee"))) {
            searchBefriendee = true;
            searchVolunteer = false;
        }

         if (searchVolunteer) {
            return FindType.VOLUNTEER_ONLY;
        } else if (searchBefriendee) {
            return FindType.BEFRIENDEE_ONLY;
        } else {
            return FindType.BOTH;
        }

    }

}
