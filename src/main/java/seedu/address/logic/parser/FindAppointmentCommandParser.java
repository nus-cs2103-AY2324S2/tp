package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentIdContainsIndexPredicate;
import seedu.address.model.appointment.StudentIdContainsIndexPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_STUDENT_ID, CliSyntax.PREFIX_APPOINTMENT_ID);

        List<Predicate<Appointment>> predicates = new ArrayList<>();

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_STUDENT_ID, CliSyntax.PREFIX_APPOINTMENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_STUDENT_ID, CliSyntax.PREFIX_APPOINTMENT_ID);
        int studentId = ParserUtil.parseStudentId(argMultimap.getValue(CliSyntax.PREFIX_STUDENT_ID).get());
        int appointmentId = ParserUtil.parseStudentId(argMultimap.getValue(CliSyntax.PREFIX_STUDENT_ID).get());

        predicates.add(new StudentIdContainsIndexPredicate(studentId));
        predicates.add(new AppointmentIdContainsIndexPredicate(appointmentId));

        // Combine predicates with AND logic
        Predicate<Appointment> combinedPredicate = predicates.stream()
                .reduce(p -> true, Predicate::and);
        return new FindAppointmentCommand(combinedPredicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
