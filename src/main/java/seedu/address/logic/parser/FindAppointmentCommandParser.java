package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.FindAppointmentPredicate;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns a FindAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_APPOINTMENT_ID);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_APPOINTMENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_APPOINTMENT_ID);
        int studentId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_ID).get()).getOneBased();
        int appointmentId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_APPOINTMENT_ID).get()).getOneBased();

        return new FindAppointmentCommand(new FindAppointmentPredicate(studentId, appointmentId));
    }

}
