package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTORNRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENTNRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.person.Nric;

/**
 * Parses addAppointment Command
 */
public class AddAppointmentCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_DOCTORNRIC, PREFIX_PATIENTNRIC);

        if (!argMultimap.getPreamble().isEmpty()
                || !arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_DOCTORNRIC, PREFIX_PATIENTNRIC)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE, PREFIX_DOCTORNRIC, PREFIX_PATIENTNRIC);
        AppointmentDate appointmentDate = ParserUtil.parseAppointmentDate(argMultimap.getValue(PREFIX_DATE).get());
        Nric doctorNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_DOCTORNRIC).get());
        Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_PATIENTNRIC).get());

        Appointment appointment = new Appointment(doctorNric, patientNric, appointmentDate);

        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
