package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSNET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NusNet;
import seedu.address.model.weeknumber.WeekNumber;


/**
 * Parses input arguments and creates a new UnmarkAttendanceCommand object.
 */
public class UnmarkAttendanceCommandParser implements Parser<UnmarkAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkAttendanceCommand
     * and returns a UnmarkAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public UnmarkAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NUSNET, PREFIX_WEEK);

        if (!ArgumentMultimap.arePrefixesPresent(argMultimap, PREFIX_NUSNET, PREFIX_WEEK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkAttendanceCommand.MESSAGE_USAGE));
        }

        NusNet nusNet = ParserUtil.parseNusNet(argMultimap.getValue(PREFIX_NUSNET).get());
        WeekNumber weekNumber = ParserUtil.parseWeekNumber(argMultimap.getValue(PREFIX_WEEK).get());

        return new UnmarkAttendanceCommand(nusNet, weekNumber);
    }
}
