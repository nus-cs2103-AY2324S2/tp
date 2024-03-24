package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ClientStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ClientStatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of the client identified "
            + "by the index number used in the last person listing. "
            + "Existing policy will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[DIRECTION] (must be 'up' or 'down')\n"
            + "Example: " + COMMAND_WORD + " 1 up";

    public static final String MESSAGE_STATUS_UP_SUCCESS = "Upgraded status of Client: %1$s";
    public static final String MESSAGE_STATUS_DOWN_SUCCESS = "Downgraded status of Client: %1$s";
    public static final String MESSAGE_PERSON_NOT_CLIENT_FAILURE =
            "Invalid person. Only clients can be assigned a policy";

    private final Index index;
    private final String direction;

    /**
     * @param index of the person in the filtered person list to update the status
     * @param direction of the status to be updated
     */
    public ClientStatusCommand(Index index, String direction) {
        requireAllNonNull(index, direction);

        this.index = index;
        this.direction = direction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (!personToEdit.isClient()) {
            throw new CommandException(MESSAGE_PERSON_NOT_CLIENT_FAILURE);
        }

        ClientStatus clientStatus;

        switch (direction) {
        case "up":
            clientStatus = personToEdit.getClientStatus().increment();
            break;
        case "down":
            clientStatus = personToEdit.getClientStatus().decrement();
            break;
        default:
            clientStatus = ClientStatus.initClientStatus();
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRelationship(), personToEdit.getPolicy(),
                clientStatus, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the policy is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = direction.equals("up") ? MESSAGE_STATUS_UP_SUCCESS : MESSAGE_STATUS_DOWN_SUCCESS;
        return String.format(message, Messages.format(personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientStatusCommand)) {
            return false;
        }

        // state check
        ClientStatusCommand e = (ClientStatusCommand) other;
        return index.equals(e.index)
                && direction.equals(e.direction);
    }
}
