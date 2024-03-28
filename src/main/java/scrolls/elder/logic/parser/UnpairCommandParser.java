package scrolls.elder.logic.parser;

import static java.util.Objects.requireNonNull;
import static scrolls.elder.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.logic.commands.UnpairCommand;
import scrolls.elder.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnpairCommand object
 */
public class UnpairCommandParser implements Parser<UnpairCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PairCommand
     * and returns an PairCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnpairCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] pairIndexes = args.trim().split(" ");
        Index index1;
        Index index2;

        try {
            index1 = ParserUtil.parseIndex(pairIndexes[0]);
            index2 = ParserUtil.parseIndex(pairIndexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnpairCommand.MESSAGE_USAGE), pe);
        }

        return new UnpairCommand(index1, index2);
    }
}
