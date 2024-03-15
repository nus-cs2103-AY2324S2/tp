package scrolls.elder.logic.parser;

import static scrolls.elder.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;
import java.util.stream.Stream;

import scrolls.elder.logic.commands.AddCommand;
import scrolls.elder.logic.parser.exceptions.ParseException;
import scrolls.elder.model.person.Address;
import scrolls.elder.model.person.Befriendee;
import scrolls.elder.model.person.Email;
import scrolls.elder.model.person.Name;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.Phone;
import scrolls.elder.model.person.Volunteer;
import scrolls.elder.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        boolean isVolunteer = false;
        boolean isBefriendee = false;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_ROLE,
                        CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS,
                        CliSyntax.PREFIX_TAG,
                        CliSyntax.PREFIX_NAME);

        // Check for all Prefixes present
        if (!arePrefixesPresent(argMultimap,
                CliSyntax.PREFIX_ROLE, CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // Check No duplicates for PREFIXES
        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_ROLE,
                CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_ADDRESS,
                CliSyntax.PREFIX_NAME);

        String role = argMultimap.getValue(CliSyntax.PREFIX_ROLE).get();
        if (role.equals("volunteer")) {
            isVolunteer = true;
        } else if (role.equals("befriendee")) {
            isBefriendee = true;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }


        Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));

        // temporary solution, delete after merging
        Person person = null;

        if (isVolunteer) {
            person = new Volunteer(name, phone, email, address, tagList);
        } else if (isBefriendee) {
            person = new Befriendee(name, phone, email, address, tagList);
        }

        return new AddCommand(person);
    }

}
