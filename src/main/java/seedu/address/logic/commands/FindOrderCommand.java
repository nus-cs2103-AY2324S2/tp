package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Finds and list order with the corresponding index.
 */
public class FindOrderCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the details of the order identified "
            + "by the specified index and displays them as a list.\n"
            + "Parameters: "
            + "[" + PREFIX_ORDER + "ORDER]"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ORDER + "19 ";

    private final Predicate<Order> predicate;

    public FindOrderCommand(Predicate<Order> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindOrderCommand)) {
            return false;
        }

        FindOrderCommand otherFindOrderCommand = (FindOrderCommand) other;
        return predicate.equals(otherFindOrderCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
