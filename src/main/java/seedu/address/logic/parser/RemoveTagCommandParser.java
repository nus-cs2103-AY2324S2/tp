package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;



/**
 * Parses input arguments and creates a new AddCommand object
 */
public class RemoveTagCommandParser implements Parser<RemoveTagCommand> {

    
    public RemoveTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        System.out.println("RemoveTagCommandParser: parsing the input");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        System.out.println(arePrefixesPresent(argMultimap, PREFIX_TAG) + " " + !argMultimap.getAllValues(PREFIX_TAG).isEmpty());
        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTagCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        System.out.println("RemoveTagCommandParser: returning the RemoveTagCommand object " + index.toString() + " " + tagSet.toString());
        return new RemoveTagCommand(index, tagSet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
