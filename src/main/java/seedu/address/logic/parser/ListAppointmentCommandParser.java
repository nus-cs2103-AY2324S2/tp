package seedu.address.logic.parser;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsAppointmentIdPredicate;
import seedu.address.model.appointment.AppointmentContainsPatientIdPredicate;
import seedu.address.model.appointment.AppointmentContainsPatientNamePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.util.RelationshipUtil;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListAppointmentCommandParser implements Parser<ListAppointmentCommand> {

    private final List<Person> patients;
    public ListAppointmentCommandParser(List<Person> patients) {
        this.patients = patients;
    }
    @Override
    public ListAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                                           CliSyntax.PREFIX_NAME,
                                           CliSyntax.PREFIX_PATIENT_ID,
                                           CliSyntax.PREFIX_APPOINTMENT_ID);

        List<Predicate<Appointment>> predicates = new ArrayList<>();

        // Checks if there is at least one prefix available.
        boolean hasAtLeastOnePrefixPresent = ParserUtil.hasAtLeastOnePrefixPresent(
                argMultimap,
                CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_PATIENT_ID,
                CliSyntax.PREFIX_APPOINTMENT_ID);

        if (!hasAtLeastOnePrefixPresent || !argMultimap.getPreamble().isEmpty()) {
            // If there is no prefix specified, then display all records.
            // TODO: Show an error message here.
            return new ListAppointmentCommand(PREDICATE_SHOW_ALL_APPOINTMENTS);
        }


        // All these criterias are OR not AND
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            predicates.add(new AppointmentContainsPatientNamePredicate(
                    Collections.singletonList(name.fullName),
                    patients
            ));
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_PATIENT_ID).isPresent()) {
            int studentId = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_PATIENT_ID)
                                                          .get()).getOneBased();
            if (!RelationshipUtil.personExists(studentId, patients)) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_PATIENT_ID, studentId)
                );
            }
            predicates.add(
                    new AppointmentContainsPatientIdPredicate(Collections.singletonList(String.valueOf(studentId)))
            );
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_APPOINTMENT_ID).isPresent()) {
            int appointmentId = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_APPOINTMENT_ID)
                                                          .get()).getOneBased();
            predicates.add(
                    new AppointmentContainsAppointmentIdPredicate(
                            Collections.singletonList(String.valueOf(appointmentId))
                    )
            );
        }
        // Combine predicates with AND logic
        Predicate<Appointment> combinedPredicate = predicates.stream()
                .reduce(p -> true, Predicate::and);

        return new ListAppointmentCommand(combinedPredicate);
    }
}
