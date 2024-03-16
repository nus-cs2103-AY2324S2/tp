package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Comparator;

import staffconnect.logic.commands.SortCommand;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.person.Person;
import staffconnect.model.person.comparators.ModuleComparator;
import staffconnect.model.person.comparators.NameComparator;
import staffconnect.model.person.comparators.PhoneComparator;
import staffconnect.model.person.comparators.VenueComparator;

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

        if (keyword.equals(PREFIX_MODULE.getPrefix())) {
            return ModuleComparator.MODULE_COMPARATOR;
        } else if (keyword.equals(PREFIX_NAME.getPrefix())) {
            return NameComparator.NAME_COMPARATOR;
        } else if (keyword.equals(PREFIX_PHONE.getPrefix())) {
            return PhoneComparator.PHONE_COMPARATOR;
        } else if (keyword.equals(PREFIX_VENUE.getPrefix())) {
            return VenueComparator.VENUE_COMPARATOR;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

    }


}
