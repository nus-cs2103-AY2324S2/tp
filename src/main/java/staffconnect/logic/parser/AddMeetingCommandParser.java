package staffconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static staffconnect.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.util.stream.Stream;

import staffconnect.commons.core.index.Index;
import staffconnect.logic.commands.AddMeetingCommand;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.meeting.Description;
import staffconnect.model.meeting.MeetDateTime;
import staffconnect.model.meeting.Meeting;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_STARTDATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE),
                                     pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DESCRIPTION, PREFIX_STARTDATE);
        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_STARTDATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        MeetDateTime startDate = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_STARTDATE).get());

        Meeting meeting = new Meeting(description, startDate);
        return new AddMeetingCommand(index, meeting);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
