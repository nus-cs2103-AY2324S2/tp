package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RETURN_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkLoanCommand;
import seedu.address.logic.commands.LinkLoanCommand.LinkLoanDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LinkLoanCommand object
 */
public class LinkLoanCommandParser implements Parser<LinkLoanCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an LinkLoanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkLoanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VALUE, PREFIX_START_DATE, PREFIX_RETURN_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_VALUE, PREFIX_START_DATE, PREFIX_RETURN_DATE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkLoanCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkLoanCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_VALUE, PREFIX_START_DATE, PREFIX_RETURN_DATE);

        LinkLoanDescriptor linkLoanDescriptor = ParserUtil.parseLoan(argMultimap.getValue(PREFIX_VALUE).get(),
                argMultimap.getValue(PREFIX_START_DATE).get(),
                argMultimap.getValue(PREFIX_RETURN_DATE).get());

        return new LinkLoanCommand(linkLoanDescriptor, index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
