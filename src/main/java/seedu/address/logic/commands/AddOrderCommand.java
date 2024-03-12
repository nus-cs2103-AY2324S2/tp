package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Date;
import seedu.address.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an order to the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "d/ [DATE] r/ [DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/ 2024-01-01 r/ 100 chicken wings.";

    private final Index index;
    private final Date arrivalDate;
    private final String description;

    /**
     * Creates an AddOrderCommand to add the specified {@code Person}
     */
    public AddOrderCommand(Index index, Date arrivalDate, String description) {
        requireNonNull(index);
        requireNonNull(arrivalDate);
        requireNonNull(description);

        this.index = index;
        this.arrivalDate = arrivalDate;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        person.addOrder(this.arrivalDate, this.description);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(person)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOrderCommand)) {
            return false;
        }

        AddOrderCommand otherAddOrderCommand = (AddOrderCommand) other;
        return index.equals(otherAddOrderCommand.index)
                && arrivalDate.equals(otherAddOrderCommand.arrivalDate)
                && description.equals(otherAddOrderCommand.description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("arrivalDate", arrivalDate)
                .add("description", description)
                .toString();
    }
}
