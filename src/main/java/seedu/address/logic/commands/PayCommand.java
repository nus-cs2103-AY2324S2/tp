package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_MOBILE;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Generates a QR code and shows it to the user if the person has a valid Singapore phone number.
 * The QR code can be scanned by a banking application to transfer money to the person.
 */
public class PayCommand extends Command {

    public static final String COMMAND_WORD = "pay";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generates a PayNow QR code for the person identified by the index number used "
            + "in the displayed person list. The person must have a valid Singapore phone number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_GENERATE_QR_SUCCESS = "Generated QR code for Person: %1$s";

    private final Index targetIndex;

    public PayCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToPay = lastShownList.get(targetIndex.getZeroBased());
        if (!personToPay.getPhone().isSingaporeanNumber()) {
            throw new CommandException(MESSAGE_INVALID_MOBILE);
        }
        return new CommandResult(
                String.format(MESSAGE_GENERATE_QR_SUCCESS, Messages.format(personToPay)), personToPay);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PayCommand)) {
            return false;
        }
        PayCommand otherCommand = (PayCommand) other;
        return Objects.equals(targetIndex, otherCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
