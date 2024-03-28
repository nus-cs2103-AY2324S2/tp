package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_GRADE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_TIMESLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_EDIT;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME_EDIT, PREFIX_PHONE_EDIT,
                PREFIX_EMAIL_EDIT, PREFIX_ADDRESS_EDIT, PREFIX_TIMESLOT_EDIT, PREFIX_GRADE_EDIT,
                OPTION_PRINT_NAME, OPTION_PRINT_PHONE, OPTION_PRINT_EMAIL,
                OPTION_PRINT_ADDRESS, OPTION_PRINT_TIMESLOT, OPTION_PRINT_GRADE);

        // Check if any option to print is requested
        if (argMultimap.getValue(OPTION_PRINT_NAME).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditStudentDescriptor(),
                    true, false, false,
                    false, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_PHONE).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditStudentDescriptor(),
                    false, true, false,
                    false, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_EMAIL).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditStudentDescriptor(),
                    false, false, true,
                    false, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_ADDRESS).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditStudentDescriptor(),
                    false, false,
                    false, true, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_TIMESLOT).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditStudentDescriptor(),
                    false, false, false,
                    false, true, false);
        } else if (argMultimap.getValue(OPTION_PRINT_GRADE).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditStudentDescriptor(),
                    false, false, false,
                    false, false, true);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME_EDIT, PREFIX_PHONE_EDIT,
                PREFIX_EMAIL_EDIT, PREFIX_ADDRESS_EDIT, PREFIX_TIMESLOT_EDIT, PREFIX_GRADE_EDIT);

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();

        if (argMultimap.getValue(PREFIX_NAME_EDIT).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME_EDIT).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE_EDIT).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE_EDIT).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL_EDIT).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL_EDIT).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS_EDIT).isPresent()) {
            editStudentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS_EDIT).get()));
        }
        // Check if PREFIX_TIMESLOT_EDIT is present before parsing multiple timeslots
        if (argMultimap.getValue(PREFIX_TIMESLOT_EDIT).isPresent()) {
            parseTimeslotsForEdit(argMultimap.getAllValues(PREFIX_TIMESLOT_EDIT))
                    .ifPresent(editStudentDescriptor::setTimeslots);
        }
        // Check if PREFIX_GRADE_EDIT is present before parsing multiple grades
        if (argMultimap.getValue(PREFIX_GRADE_EDIT).isPresent()) {
            parseGradesForEdit(argMultimap.getAllValues(PREFIX_GRADE_EDIT))
                    .ifPresent(editStudentDescriptor::setGrades);
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        if (!editStudentDescriptor.isSingleFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_EDITED_BUT_MORE_THAN_ONE);
        }

        return new EditCommand(index, editStudentDescriptor, false, false, false, false, false, false);
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

        Set<Timeslots> timeslotSet = new HashSet<>();
        for (String timeslotString : timeslots) {
            String trimmedTimeslotString = timeslotString.trim();
            // Remove leading and trailing curly braces if present
            if (trimmedTimeslotString.startsWith("[[") && trimmedTimeslotString.endsWith("]]")) {
                trimmedTimeslotString = trimmedTimeslotString.substring(2, trimmedTimeslotString.length() - 2);
            }
            // Split by commas to handle multiple timeslots
            String[] timeslotArray = trimmedTimeslotString.split(",");
            for (String timeslot : timeslotArray) {
                timeslotSet.add(new Timeslots(timeslot.trim()));
            }
        }

        return Optional.of(timeslotSet);
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
