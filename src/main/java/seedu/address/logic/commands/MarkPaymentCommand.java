package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Marks payment of a student's monthly tuition fees by calling his / her displayed index from the address book.
 */
public class MarkPaymentCommand extends Command {

    public static final String COMMAND_WORD = "mark_payment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Completes payment for the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_PAYMENT_PERSON_SUCCESS = "Payment marked as completed for Student: %1$s";

    private final Index targetIndex;

    /**
     * Mark payment as completed with target index.
     *
     * @param targetIndex Index of ListView.
     */
    public MarkPaymentCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMark = lastShownList.get(targetIndex.getZeroBased());
        if (personToMark instanceof Person) {
            model.deletePerson(personToMark);
        } else {
            throw new CommandException(Messages.MESSAGE_PERSON_IS_NOT_STUDENT);
        }
        return new CommandResult(String.format(MESSAGE_MARK_PAYMENT_PERSON_SUCCESS, Messages.format(personToMark)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkPaymentCommand)) {
            return false;
        }

        MarkPaymentCommand otherMarkPaymentCommand = (MarkPaymentCommand) other;
        return targetIndex.equals(otherMarkPaymentCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
