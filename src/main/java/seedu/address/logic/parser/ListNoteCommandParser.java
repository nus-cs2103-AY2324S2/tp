package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListNoteCommand object
 */
public class ListNoteCommandParser implements Parser<ListNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListNoteCommand
     * and returns an ListNoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListNoteCommand parse(String args) throws ParseException {

        if (args.isEmpty()) {
            return new ListNoteCommand();
        }

        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        Index patientIndex;

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListNoteCommand.MESSAGE_USAGE), pe);
        }

        return new ListNoteCommand(patientIndex);
    }
}
