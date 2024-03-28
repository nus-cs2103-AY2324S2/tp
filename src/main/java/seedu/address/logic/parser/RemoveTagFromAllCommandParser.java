package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveTagFromAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;



/**
 * Parses input arguments and creates a new AddCommand object
 */
public class RemoveTagFromAllCommandParser implements Parser<RemoveTagFromAllCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveTagFromAllCommand
     * and returns an RemoveTagFromAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveTagFromAllCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveTagFromAllCommand.MESSAGE_USAGE));
        }
        Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        return new RemoveTagFromAllCommand(tagSet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
