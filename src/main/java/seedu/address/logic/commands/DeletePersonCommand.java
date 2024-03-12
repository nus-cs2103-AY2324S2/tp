package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "delstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student with the NUSNET_ID. \n"
            + "Parameters: NUSNET_ID \n"
            + "Example: " + COMMAND_WORD + " e0957499";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";

    private final NusNet targetNusNet;

    public DeletePersonCommand(NusNet targetNusNet) {
        this.targetNusNet = targetNusNet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetNusNet == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.stream()
                .filter(person -> person.getNusNet().equals(targetNusNet))
                .findFirst()
                .orElse(null);

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_NUSNETID_NOT_FOUND);
        }

        assert(personToDelete != null);

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePersonCommand)) {
            return false;
        }

        DeletePersonCommand otherDeleteCommand = (DeletePersonCommand) other;

        return targetNusNet.equals(otherDeleteCommand.targetNusNet);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetNUSNET_ID", targetNusNet)
                .toString();
    }
}
