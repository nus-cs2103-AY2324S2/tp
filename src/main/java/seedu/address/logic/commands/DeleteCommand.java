package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the NRIC number used in the displayed person list.\n"
            + "Parameters: NRIC (must be a existing NRIC in database)\n"
            + "Example: " + COMMAND_WORD + " S1234567A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Nric targetNric;

    public DeleteCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String msg = "";

        if (model.hasPersonWithNric(targetNric)) {
            msg = String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                    Messages.format(model.getPersonWithNric(targetNric)));
            model.deletePersonWithNric(targetNric);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NRIC);
        }

        return new CommandResult(msg);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetNric.equals(otherDeleteCommand.targetNric);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetNric", targetNric)
                .toString();
    }
}
