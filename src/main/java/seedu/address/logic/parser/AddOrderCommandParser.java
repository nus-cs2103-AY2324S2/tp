package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Date;

/**
 * Parses input arguments and creates a new AddOrderCommand object
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_REMARK);

        if (!argMultimap.arePrefixesPresent(PREFIX_DATE, PREFIX_REMARK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddOrderCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE, PREFIX_REMARK);

        Date arrivalDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        String remark = argMultimap.getValue(PREFIX_REMARK).get();

        return new AddOrderCommand(index, arrivalDate, remark);
    }
}
