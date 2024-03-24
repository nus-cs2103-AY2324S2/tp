package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTE, PREFIX_FLAG);
        String trimmedArgs = args.trim();

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE)
                || trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        try {
            IdentityCardNumber ic = ParserUtil.parseIC(argMultimap.getPreamble());
            String note = "";
            boolean isReplace = false;

            if (argMultimap.getValue(PREFIX_FLAG).isPresent()) {
                int startIndex = args.indexOf(PREFIX_NOTE.getPrefix()) + PREFIX_NOTE.getPrefix().length();
                int endIndex = args.indexOf(PREFIX_FLAG.getPrefix());
                note = args.substring(startIndex, endIndex).trim();
                isReplace = true;
            } else {
                note = argMultimap.getValue(PREFIX_NOTE).orElse("");
            }

            return new AddNoteCommand(new IdentityCardNumberMatchesPredicate(ic), new Note(note), isReplace);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(IdentityCardNumber.MESSAGE_CONSTRAINTS), ive);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
