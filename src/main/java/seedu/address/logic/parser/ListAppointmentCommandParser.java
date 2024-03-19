package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.StudentIdContainsIndexPredicate;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListAppointmentCommandParser implements Parser<ListAppointmentCommand> {
    @Override
    public ListAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID);

        List<Predicate<Appointment>> predicates = new ArrayList<>();

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STUDENT_ID);

        // All these criterias are OR not AND
//        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
//            Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
////            predicates.add(new NameContainsKeywordsPredicate(Collections.singletonList(name.fullName)));
//        }

        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            int studentId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_ID).get()).getOneBased();
            predicates.add(new StudentIdContainsIndexPredicate(studentId));
        }

        // Combine predicates with AND logic
        Predicate<Appointment> combinedPredicate = predicates.stream()
                .reduce(p -> true, Predicate::and);

        return new ListAppointmentCommand(combinedPredicate);
    }

}
