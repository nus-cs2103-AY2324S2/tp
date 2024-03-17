package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ID;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;

public class CustomerOrderDifferentiator {
    public boolean isCustomer(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMER_ID, PREFIX_ORDER_ID);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CUSTOMER_ID, PREFIX_ORDER_ID);

        boolean hasCustomer = argMultimap.getValue(PREFIX_CUSTOMER_ID).isPresent();
        boolean hasOrder = argMultimap.getValue(PREFIX_ORDER_ID).isPresent();
        if (hasCustomer && !hasOrder) {
            return true;
        } else if (!hasCustomer && hasOrder) {
            return false;
        } else if (hasCustomer && hasOrder){
            throw new ParseException(Messages.MESSAGE_COEXISTING_CUSTOMER_AND_ORDER);
        } else {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
