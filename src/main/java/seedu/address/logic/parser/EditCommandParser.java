package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.grade.Grade;
import seedu.address.model.timeslots.Timeslots;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TIMESLOT, PREFIX_GRADE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStudentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTimeslotsForEdit(argMultimap.getAllValues(PREFIX_TIMESLOT)).ifPresent(editStudentDescriptor::setTimeslots);
        parseGradesForEdit(argMultimap.getAllValues(PREFIX_GRADE)).ifPresent(editStudentDescriptor::setGrades);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }

    /**
     * Parses {@code Collection<String> timeslots} into a {@code Set<Timeslot>} if {@code timeslots} is non-empty.
     * If {@code timeslots} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Timeslot>} containing zero timeslots.
     */
    private Optional<Set<Timeslots>> parseTimeslotsForEdit(Collection<String> timeslots) throws ParseException {
        assert timeslots != null;

        if (timeslots.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> timeslotSet = timeslots.size()
                == 1 && timeslots.contains("") ? Collections.emptySet() : timeslots;
        return Optional.of(ParserUtil.parseTimeslots(timeslotSet));
    }

    /**
     * Parses {@code Collection<String> grades} into a {@code Set<Grade>} if {@code grades} is non-empty.
     * If {@code grades} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Grade>} containing zero grades.
     */
    private Optional<Set<Grade>> parseGradesForEdit(Collection<String> grades) throws ParseException {
        assert grades != null;

        if (grades.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> gradeSet = grades.size() == 1 && grades.contains("") ? Collections.emptySet() : grades;
        return Optional.of(ParserUtil.parseGrades(gradeSet));
    }
}
