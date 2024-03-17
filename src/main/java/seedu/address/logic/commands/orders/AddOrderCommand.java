package seedu.address.logic.commands.orders;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Date;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

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
            + "Example: " + COMMAND_WORD + " 1 " + "d/1xRoses + by/23-07-2024";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Details: %2$s, Deadline: %3$tF %3$tT";

    private final Index index;
    private final String details;
    private final Date deadline;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(Index index, String details, Date deadline) {
        requireAllNonNull(index, details, deadline);

        this.index = index;
        this.details = details;
        this.deadline = deadline;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased(), details, deadline));
    }
}
