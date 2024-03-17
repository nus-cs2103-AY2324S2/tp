package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;
import static staffconnect.model.person.comparators.FacultyComparator.FACULTY_COMPARATOR;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;

import java.util.Comparator;

import staffconnect.logic.commands.SortCommand;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.person.Person;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(parseComparatorForKeywords(trimmedArgs));
    }


    private Comparator<Person> parseComparatorForKeywords(String keyword) throws ParseException {

        if (keyword.equals(PREFIX_NAME.getPrefix())) {
            return NAME_COMPARATOR;
        } else if (keyword.equals(PREFIX_PHONE.getPrefix())) {
            return PHONE_COMPARATOR;
        } else if (keyword.equals(PREFIX_MODULE.getPrefix())) {
            return MODULE_COMPARATOR;
        } else if (keyword.equals(PREFIX_FACULTY.getPrefix())) {
            return FACULTY_COMPARATOR;
        } else if (keyword.equals(PREFIX_VENUE.getPrefix())) {
            return VENUE_COMPARATOR;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

    }


}
