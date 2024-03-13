package seedu.address.logic.parser;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTCONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.LastContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LastContact;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new LastContact object.
 */
public class LastContactCommandParser implements Parser<LastContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LastContact
     * and returns an LastContact object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LastContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LASTCONTACT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LASTCONTACT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LastContactCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        LastContact lastcontact = ParserUtil.parseLastContact(argMultimap.getValue(PREFIX_LASTCONTACT).get());
        return new LastContactCommand(name.fullName, lastcontact);
    }
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
