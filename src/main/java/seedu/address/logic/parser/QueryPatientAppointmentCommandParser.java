package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.QueryPatientAppointmentCommand;
import seedu.address.logic.commands.QueryPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsPatientPredicate;

/**
 * Parses input arguments and creates a new QueryPatientAppointmentCommand object
 */
public class QueryPatientAppointmentCommandParser implements Parser<QueryPatientAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the QueryPatientAppointmentCommand
     * and returns a QueryPatientAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QueryPatientAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, QueryPatientAppointmentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new QueryPatientAppointmentCommand(new AppointmentContainsPatientPredicate(Arrays.asList(nameKeywords)));
    }
}
