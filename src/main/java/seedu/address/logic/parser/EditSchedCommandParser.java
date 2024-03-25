package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIFIC_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditSchedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Schedule;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditSchedCommandParser implements Parser<EditSchedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSchedCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SCHEDULE, PREFIX_START,
                PREFIX_END, PREFIX_ADD_PARTICIPANTS, PREFIX_REMOVE_PARTICIPANTS, PREFIX_SPECIFIC_PARTICIPANTS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditSchedCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SCHEDULE, PREFIX_START,
                PREFIX_END, PREFIX_ADD_PARTICIPANTS, PREFIX_REMOVE_PARTICIPANTS, PREFIX_SPECIFIC_PARTICIPANTS);

        if (argMultimap.getValue(PREFIX_SPECIFIC_PARTICIPANTS).isPresent()
                && (argMultimap.getValue(PREFIX_ADD_PARTICIPANTS).isPresent()
                || argMultimap.getValue(PREFIX_REMOVE_PARTICIPANTS).isPresent())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditSchedCommand.MESSAGE_USAGE));
        }

        EditSchedCommand.EditScheduleDescriptor editScheduleDescriptor =
                new EditSchedCommand.EditScheduleDescriptor();

        if (argMultimap.getValue(PREFIX_SCHEDULE).isPresent()) {
            editScheduleDescriptor.setSchedName(argMultimap.getValue(PREFIX_SCHEDULE).get());
        }
        if (argMultimap.getValue(PREFIX_START).isPresent()) {
            editScheduleDescriptor.setStartTime(LocalDateTime.parse(argMultimap.getValue(PREFIX_START).get(),
                    Schedule.CUSTOM_DATETIME));
        }
        if (argMultimap.getValue(PREFIX_END).isPresent()) {
            editScheduleDescriptor.setEndTime(LocalDateTime.parse(argMultimap.getValue(PREFIX_END).get(),
                    Schedule.CUSTOM_DATETIME));
        }
        if (argMultimap.getValue(PREFIX_ADD_PARTICIPANTS).isPresent()) {
            editScheduleDescriptor.setToAddParticipantList(
                    ParserUtil.parseIndexArrayList(argMultimap.getValue(PREFIX_ADD_PARTICIPANTS).get()));
        }
        if (argMultimap.getValue(PREFIX_REMOVE_PARTICIPANTS).isPresent()) {
            editScheduleDescriptor.setToRemoveParticipantList(
                    ParserUtil.parseIndexArrayList(argMultimap.getValue(PREFIX_REMOVE_PARTICIPANTS).get()));
        }
        if (argMultimap.getValue(PREFIX_SPECIFIC_PARTICIPANTS).isPresent()) {
            editScheduleDescriptor.setNewParticipantList(
                    ParserUtil.parseIndexArrayList(argMultimap.getValue(PREFIX_SPECIFIC_PARTICIPANTS).get()));
        }

        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditSchedCommand(index, editScheduleDescriptor);
    }
}

