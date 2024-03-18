package seedu.address.logic.parser;

import seedu.address.logic.commands.AddMeetingCommand;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Set;
import java.util.stream.Stream;


import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


public class AddMeetingParser implements Parser<AddMeetingCommand> {

    /**
    * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
    * and returns an AddMeetingCommand object for execution.
    * @throws ParseException if the user input does not conform the expected format
    */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TIME, PREFIX_LOCATION, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_TIME, PREFIX_LOCATION, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE, PREFIX_TIME, PREFIX_LOCATION, PREFIX_DESCRIPTION);
        Location location = ParserUtil.parseName(argMultimap.getValue(PREFIX_LOCATION).get());
        Time time = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_TIME).get());
        Date date = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_DATE).get());
        Description description = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Meeting meeting = new Meeting(date, time, location, description);

        return new AddMeetingCommand(meeting);
    }

    /**
    * Returns true if none of the prefixes contains empty {@code Optional} values in the given
    * {@code ArgumentMultimap}.
    */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
