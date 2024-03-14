package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Comparator;

import staffconnect.logic.commands.FindCommand;
import staffconnect.logic.commands.SortCommand;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.person.Person;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new SortCommand(parseComparatorForKeywords(trimmedArgs));
    }


    private Comparator<Person> parseComparatorForKeywords(String keyword) throws ParseException {

        if (keyword.equals(PREFIX_NAME.getPrefix())) {
            return new Comparator<Person>() {
                @Override
                public int compare(Person person1, Person person2) {
                    return person1.getName().toString().compareToIgnoreCase(person2.getName().toString());
                }
            };
        } else if (keyword.equals(PREFIX_PHONE.getPrefix())) {
            return new Comparator<Person>() {
                @Override
                public int compare(Person person1, Person person2) {
                    return person1.getPhone().toString().compareToIgnoreCase(person2.getPhone().toString());
                }
            };
        } else if (keyword.equals(PREFIX_MODULE.getPrefix())) {
            return new Comparator<Person>() {
                @Override
                public int compare(Person person1, Person person2) {
                    return person1.getModule().toString().compareToIgnoreCase(person2.getModule().toString());
                }
            };
        } else if (keyword.equals(PREFIX_VENUE.getPrefix())) {
            return new Comparator<Person>() {
                @Override
                public int compare(Person person1, Person person2) {
                    return person1.getVenue().toString().compareToIgnoreCase(person2.getVenue().toString());
                }
            };
        } else {
            throw new ParseException(SortCommand.MESSAGE_USAGE);
        }




    }
}
