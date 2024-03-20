package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

public class ForceDeleteAllCommand extends Command {
    public static final String COMMAND_WORD = "delete-all-f";
    public static final String MESSAGE_SUCCESS = "Successfully deleted all data";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
