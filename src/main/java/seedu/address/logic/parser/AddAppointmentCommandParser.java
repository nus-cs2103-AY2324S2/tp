package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PATIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;
import seedu.address.model.util.RelationshipUtil;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {
    private final List<Patient> patients;
    private final List<Appointment> appointments;

    /**
     * Takes in multiple lists so as to perform validation checks against them
     *
     * @param patients List of patients
     * @param appointments List of appointments
     */
    public AddAppointmentCommandParser(List<Patient> patients, List<Appointment> appointments) {
        this.patients = patients;
        this.appointments = appointments;
    }
    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_ID, PREFIX_DATETIME,
                        PREFIX_ATTEND, PREFIX_APPOINTMENT_DESCRIPTION, PREFIX_FEEDBACK_SCORE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PATIENT_ID, PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_PATIENT_ID, PREFIX_DATETIME, PREFIX_ATTEND, PREFIX_APPOINTMENT_DESCRIPTION,
                PREFIX_FEEDBACK_SCORE);
        int studentId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT_ID).get()).getOneBased();

        if (!RelationshipUtil.personExists(studentId, patients)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_PATIENT_ID, studentId)
             );
        }

        LocalDateTime appointmentDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        if (RelationshipUtil.isAppointmentDateTimeAlreadyTaken(appointmentDateTime, this.appointments)) {
            throw new ParseException(Appointment.MESSAGE_DATETIME_ALREADY_TAKEN);
        }
        boolean hasAttended = ParserUtil.parseHasAttended(argMultimap.getValue(PREFIX_ATTEND).orElse(""));
        //TODO: remove after case log is implemented
        String appointmentDescription = ParserUtil.parseDescription(
                argMultimap.getValue(PREFIX_APPOINTMENT_DESCRIPTION).orElse(""));
        Integer feedbackScore = ParserUtil.parseFeedbackScore(
                argMultimap.getValue(PREFIX_FEEDBACK_SCORE).orElse(""));
        Appointment appointment = new Appointment(appointmentDateTime, studentId, appointmentDescription,
                                                  hasAttended, feedbackScore);
        return new AddAppointmentCommand(appointment);
    }
}
