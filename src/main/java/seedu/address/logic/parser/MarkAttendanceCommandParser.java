package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSNET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NusNet;
import seedu.address.model.weeknumber.WeekNumber;

/**
 * Parses input arguments and creates a new MarkAttendanceCommand object.
 */
public class MarkAttendanceCommandParser implements Parser<MarkAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAttendanceCommand
     * and returns a MarkAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public MarkAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NUSNET, PREFIX_WEEK);

        if (!ArgumentMultimap.arePrefixesPresent(argMultimap, PREFIX_NUSNET, PREFIX_WEEK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAttendanceCommand.MESSAGE_USAGE));
        }

        NusNet nusNet = ParserUtil.parseNusNet(argMultimap.getValue(PREFIX_NUSNET).get());
        WeekNumber weekNumber = ParserUtil.parseWeekNumber(argMultimap.getValue(PREFIX_WEEK).get());

        return new MarkAttendanceCommand(nusNet, weekNumber);
    }
}
