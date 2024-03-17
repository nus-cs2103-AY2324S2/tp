package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.orders.DeleteOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.OrderId;

/**
 * Parses input arguments and creates a new DeleteOrderCommand object
 */
public class DeleteOrderCommandParser implements Parser<DeleteOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteOrderCommand and returns a DeleteOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOrderCommand parse(String args) throws ParseException {
        try {
            OrderId orderId = new OrderId(String.valueOf(ParserUtil.parseIndex(args)));
            return new DeleteOrderCommand(orderId);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
