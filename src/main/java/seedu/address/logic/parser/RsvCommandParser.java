package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RsvCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reservation.Pax;
import seedu.address.model.reservation.RsvDate;
import seedu.address.model.reservation.RsvTime;

/**
 * Parses input arguments and creates a new RsvCommand object
 */
public class RsvCommandParser implements Parser<RsvCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RsvCommand
     * and returns an RsvCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RsvCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TIME, PREFIX_PAX);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RsvCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_TIME, PREFIX_PAX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RsvCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE, PREFIX_TIME, PREFIX_PAX);
        RsvDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        RsvTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Pax pax = ParserUtil.parsePax(argMultimap.getValue(PREFIX_PAX).get());

        return new RsvCommand(index, date, time, pax);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
