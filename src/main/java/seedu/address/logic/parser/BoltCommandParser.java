package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOLT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BoltCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Bolt;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class BoltCommandParser implements Parser<BoltCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BoltCommand
     * and returns an BoltCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BoltCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOLT); // tokenize args by PREFIX_BOLT


        if (!arePrefixesPresent(argMultimap, PREFIX_BOLT)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));
        } // if no PREFIX used, or if the Preamble is empty, we throw a ParseException

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE), pe);
        }

        Bolt bolt = ParserUtil.parseBolt(argMultimap.getValue(PREFIX_BOLT).get());
        return new BoltCommand(index, bolt);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
