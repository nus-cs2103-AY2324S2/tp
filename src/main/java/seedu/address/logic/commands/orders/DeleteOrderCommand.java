package seedu.address.logic.commands.orders;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderId;


/**
 * Removes an existing order in the address book.
 */
public class DeleteOrderCommand extends Command {
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_SUCCESS = "Deleted Order: %1$s";

    public static final String COMMAND_WORD = "deleteOrder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    private final OrderId index;



    /**
     * Creates an DeleteOrderCommand to delete the specified {@code Order}
     */
    public DeleteOrderCommand(OrderId index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Order> orderList = model.getOrderList();
        Order selectedOrderToDelete = null;

        for (Order order: orderList) {
            if (order.getOrderId() == this.index) {
                selectedOrderToDelete = order;
                break;
            }
        }

        if (selectedOrderToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        model.deleteOrder(selectedOrderToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(selectedOrderToDelete)));

    }
}
