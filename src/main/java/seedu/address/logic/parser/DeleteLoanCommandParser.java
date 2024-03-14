package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteLoanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteLoanCommand object.
 */
public class DeleteLoanCommandParser implements Parser<DeleteLoanCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLoanCommand
     * and returns a DeleteLoanCommand object for execution.
     */
    public DeleteLoanCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LOAN_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_LOAN_INDEX)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLoanCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_LOAN_INDEX);
        try {
            Index personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            Index loanIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LOAN_INDEX).get());
            return new DeleteLoanCommand(personIndex, loanIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLoanCommand.MESSAGE_USAGE), pe);
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
