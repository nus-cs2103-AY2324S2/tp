package seedu.address.logic.commands.orders;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Adds an order to an assigned person.
 */
public class AddOrderCommand extends Command {
    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Index: 1, Details: Some order details, Deadline: 2024-07-23 00:00:00";

    public static final String COMMAND_WORD = "order";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates an order that is associated to a client."
            + "Multiple orders will be appended to each other, "
            + "and old orders will always be kept during this operation\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "DETAILS (in formation related to order), "
            + "DEADLINE (the date the order is due"
            + "r/ [ORDER]\n"
            + "Example: " + COMMAND_WORD + "d/1xRoses c/40 by/23-07-2024 00:00";

    public static final String MESSAGE_SUCCESS = "New Order added!";
    private final Order order;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(Order order) {
        requireAllNonNull(order);
        this.order = order;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        model.addOrder(this.order);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(this.order)));
    }
}
