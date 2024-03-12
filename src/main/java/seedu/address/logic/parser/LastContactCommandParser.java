package seedu.address.logic.parser;

import seedu.address.logic.commands.LastContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LastContact;
import seedu.address.model.person.Name;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class LastContactCommandParser implements Parser<LastContactCommand> {

    public LastContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LastContactCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        LastContact lastcontact = ParserUtil.parseLastContact(argMultimap.getValue(PREFIX_DATETIME).get());
        return new LastContactCommand(name.fullName, lastcontact);
    }
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
