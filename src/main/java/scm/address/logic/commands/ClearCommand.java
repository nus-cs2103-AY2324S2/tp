package scm.address.logic.commands;

import static java.util.Objects.requireNonNull;

import scm.address.model.AddressBook;
import scm.address.model.Model;

/**
 * Clears the contact manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
