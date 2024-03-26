package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.QueryDoctorAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsDoctorPredicate;

/**
 * Parses input arguments and creates a new QueryDoctorAppointmentCommand object
 */
public class QueryDoctorAppointmentCommandParser implements Parser<QueryDoctorAppointmentCommand> {
    private static final Logger logger = Logger.getLogger(QueryDoctorAppointmentCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the QueryDoctorAppointmentCommand
     * and returns a QueryDoctorAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QueryDoctorAppointmentCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Parsing QueryDoctorAppointmentCommand arguments: " + args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, QueryDoctorAppointmentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        logger.log(Level.INFO, "Name keywords: " + Arrays.toString(nameKeywords));

        return new QueryDoctorAppointmentCommand(new AppointmentContainsDoctorPredicate(Arrays.asList(nameKeywords)));
    }
}
