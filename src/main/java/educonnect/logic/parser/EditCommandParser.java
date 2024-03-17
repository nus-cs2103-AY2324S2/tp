package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.*;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_SUNDAY;
import static java.util.Objects.requireNonNull;

import educonnect.model.student.timetable.Timetable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import educonnect.commons.core.index.Index;
import educonnect.logic.commands.EditCommand;
import educonnect.logic.parser.exceptions.ParseException;
import educonnect.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID,
                        PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE, PREFIX_TAG, PREFIX_TIMETABLE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE);

        EditCommand.EditStudentDescriptor editPersonDescriptor = new EditCommand.EditStudentDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            editPersonDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
            editPersonDescriptor.setTelegramHandle(ParserUtil.parseTelegramHandle(argMultimap.getValue(
                        PREFIX_TELEGRAM_HANDLE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (argMultimap.getValue(PREFIX_TIMETABLE).isPresent()) {
            editPersonDescriptor.setTimetable(ParserUtil.parseTimetable(
                    tokenizeForTimetable(argMultimap.getValue(PREFIX_TIMETABLE).orElse(""))));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Tokenizes the input arguments for Timetable under Add Command.
     *
     * @param fullTimetableString a full {@code String} containing the arguments. E.g. "mon: 1-4, 12-14 tue: 14-16 ..."
     * @return an {@code ArrayList<String>}, with each entry containing arguments for each day of the Timetable week.
     */
    public static ArrayList<String> tokenizeForTimetable(String fullTimetableString) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(fullTimetableString, PREFIX_TIMETABLE_MONDAY, PREFIX_TIMETABLE_TUESDAY,
                        PREFIX_TIMETABLE_WEDNESDAY, PREFIX_TIMETABLE_THURSDAY, PREFIX_TIMETABLE_FRIDAY,
                        PREFIX_TIMETABLE_SATURDAY, PREFIX_TIMETABLE_SUNDAY);

        ArrayList<String> allDays = new ArrayList<>();
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_MONDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_TUESDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_WEDNESDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_THURSDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_FRIDAY).orElse(""));

        if (Timetable.is7Days()) {
            allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_SATURDAY).orElse(""));
            allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_SUNDAY).orElse(""));
        }

        return allDays;
    }
}
