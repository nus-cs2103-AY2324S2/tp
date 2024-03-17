package seedu.address.logic.commands.orders;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays the list of all client orders in the address book.
 */
public class ViewOrdersCommand extends Command {

    public static final String COMMAND_WORD = "viewOrders";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all orders of the user."
            + "Parameters: none required."
            + "Example: " + COMMAND_WORD;


    public static final String MESSAGE_SUCCESS = "Here are all your orders: ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
