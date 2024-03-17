package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ID;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMER_ID, PREFIX_ORDER_ID);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CUSTOMER_ID, PREFIX_ORDER_ID);

        if (ParserUtil.isCustomer(args)) {
            EditCustomerCommandParser editCustomerCommandParser = new EditCustomerCommandParser();
            return editCustomerCommandParser.parse(args);
        } else {
            EditOrderCommandParser editOrderCommandParser = new EditOrderCommandParser();
            return editOrderCommandParser.parse(args);
        }
    }
}
