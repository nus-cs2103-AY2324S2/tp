package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderList;

/**
 * Deletes an order by its Order ID.
 */
public class CancelCommand extends Command {
    public static final String COMMAND_WORD = "cancel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Cancels an order by its Order ID.\n"
            + "Parameters: ORDER_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CANCEL_ORDER_SUCCESS = "Cancelled Order: %1$s";

    private final Index targetIndex;

    public CancelCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        OrderList orderList = model.getOrderList();

        if (targetIndex.getZeroBased() >= orderList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        model.deleteOrder(targetIndex.getOneBased());
        return new CommandResult(String.format(MESSAGE_CANCEL_ORDER_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CancelCommand)) {
            return false;
        }

        CancelCommand otherCancelCommand = (CancelCommand) other;
        return targetIndex.equals(otherCancelCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
