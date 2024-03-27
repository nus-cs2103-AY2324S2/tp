package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditNoteCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteCommand
     * and returns an EditNoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TIME, PREFIX_NOTE);

        Index patientIndex;
        Index noteIndex;

        try {
            Index[] indices = ParserUtil.parseIndices(argMultimap.getPreamble());
            patientIndex = indices[0];
            noteIndex = indices[1];
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE, PREFIX_TIME, PREFIX_NOTE);

        EditNoteDescriptor editNoteDescriptor = createEditNoteDescriptor(argMultimap);

        if (!editNoteDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditNoteCommand.MESSAGE_NOTE_NOT_EDITED);
        }

        return new EditNoteCommand(patientIndex, noteIndex, editNoteDescriptor);

    }

    private EditNoteDescriptor createEditNoteDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editNoteDescriptor.setDate(ParserUtil.parseLocalDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editNoteDescriptor.setTime(ParserUtil.parseLocalTime(argMultimap.getValue(PREFIX_TIME).get()));
        }

        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            editNoteDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_NOTE).get()));
        }

        return editNoteDescriptor;
    }
}
