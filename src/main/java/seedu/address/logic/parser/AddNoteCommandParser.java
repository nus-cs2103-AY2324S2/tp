package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;

import java.awt.dnd.InvalidDnDOperationException;

/**
 * Parses input arguments and creates a new {@code AddNoteCommand} object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddNoteCommand}
     * and returns a {@code AddNoteCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTE);

        IdentityCardNumber ic;
        try {
            String note = argMultimap.getValue(PREFIX_NOTE).orElse("");

            boolean isReplace;
            if (note.contains("-replace")) {
                isReplace = true;
                note = note.replace("-replace", "").trim();
            } else {
                isReplace = false;
            }
            ic = ParserUtil.parseIC(argMultimap.getPreamble());
            
            return new AddNoteCommand(new IdentityCardNumberMatchesPredicate(ic), new Note(note), isReplace);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE), ive);
        }
    }
}
