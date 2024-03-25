package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.QueryDoctorAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsDoctorPredicate;

/**
 * Parses input arguments and creates a new QueryDoctorAppointmentCommand object
 */
public class QueryDoctorAppointmentCommandParser implements Parser<QueryDoctorAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the QueryDoctorAppointmentCommand
     * and returns a QueryDoctorAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QueryDoctorAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, QueryDoctorAppointmentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new QueryDoctorAppointmentCommand(new AppointmentContainsDoctorPredicate(Arrays.asList(nameKeywords)));
    }
}
