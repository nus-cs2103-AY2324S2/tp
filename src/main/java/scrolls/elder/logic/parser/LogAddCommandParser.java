package scrolls.elder.logic.parser;

import static scrolls.elder.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Date;
import java.util.stream.Stream;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.logic.commands.LogAddCommand;
import scrolls.elder.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LogAddCommand object
 */
public class LogAddCommandParser implements Parser<LogAddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the LogAddCommand
     * and returns an LogAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LogAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_START,
                CliSyntax.PREFIX_DURATION,
                CliSyntax.PREFIX_REMARKS);

        // Check for all Prefixes present
        if (!arePrefixesPresent(argMultimap,
            CliSyntax.PREFIX_START, CliSyntax.PREFIX_DURATION,
            CliSyntax.PREFIX_REMARKS) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LogAddCommand.MESSAGE_USAGE));
        }

        String[] pairIndexes = argMultimap.getPreamble().trim().split(" ");
        Index index1;
        Index index2;

        try {
            index1 = ParserUtil.parseIndex(pairIndexes[0]);
            index2 = ParserUtil.parseIndex(pairIndexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LogAddCommand.MESSAGE_USAGE), pe);
        }

        Date start = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_START).get());
        int duration = ParserUtil.parseInt(argMultimap.getValue(CliSyntax.PREFIX_DURATION).get());
        String remarks = argMultimap.getValue(CliSyntax.PREFIX_REMARKS).get().trim();

        return new LogAddCommand(index1, index2, duration, start, remarks);
    }

}
