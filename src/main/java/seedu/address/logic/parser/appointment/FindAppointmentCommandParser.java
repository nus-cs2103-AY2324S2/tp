package seedu.address.logic.parser.appointment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.FindAppointmentCommand;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindAppointmentCommand and returns a FindAppointmentCommand object for
     * execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public FindAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new FindAppointmentCommand(AppointmentContainsKeywordsPredicate.build(Arrays.asList(keywords)));
    }

}
