package seedu.address.logic.commands.orders;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes an existing order in the address book.
 */
public class DeleteOrderCommand extends Command {
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    public static final String COMMAND_WORD = "deleteOrder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    private final Index index;


    /**
     * Creates an DeleteOrderCommand to delete the specified {@code Order}
     */
    public DeleteOrderCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased()));
    }
}
