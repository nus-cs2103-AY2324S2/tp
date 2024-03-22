package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAttendanceCommand;
import seedu.address.logic.commands.EditAttendanceCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AttendanceStatus;
import seedu.address.model.tag.Attendance;

/**
 * Parses input arguments and creates a new EditAttendanceCommand object
 */
public class EditAttendanceCommandParser implements Parser<EditAttendanceCommand> {

    @Override
    public EditAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_RECORD, PREFIX_ATTENDANCE_STATUS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAttendanceCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_ATTENDANCE_RECORD, PREFIX_ATTENDANCE_STATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAttendanceCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ATTENDANCE_RECORD, PREFIX_ATTENDANCE_STATUS);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        AttendanceStatus attendanceStatus = ParserUtil.parsesAttendanceStatus(
                argMultimap.getValue(PREFIX_ATTENDANCE_RECORD).get(),
                argMultimap.getValue(PREFIX_ATTENDANCE_STATUS).get());
        editPersonDescriptor.setAttendances(new Attendance(attendanceStatus));

        return new EditAttendanceCommand(index, editPersonDescriptor);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Attendance>> parseAttendanceForEdit(Collection<String> attendances) throws ParseException {
        assert attendances != null;

        if (attendances.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = attendances.size() == 1 && attendances.contains("")
                ? Collections.emptySet() : attendances;
        return Optional.of(ParserUtil.parseAttendances(tagSet));
    }
}
