package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ClientStatus;
import seedu.address.model.person.Person;

/**
 * Changes the status of an existing client in the address book.
 */
public class ClientStatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of the client identified "
            + "by the index number used in the last person listing. "
            + "Existing policy will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_STATUS + "[DIRECTION] (must be 'up' or 'down')\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_STATUS + "up";

    public static final String MESSAGE_STATUS_UP_SUCCESS = "Upgraded status of Client: %1$s";
    public static final String MESSAGE_STATUS_DOWN_SUCCESS = "Downgraded status of Client: %1$s";
    public static final String MESSAGE_STATUS_RESET_SUCCESS = "Reset status of Client: %1$s";
    public static final String MESSAGE_PERSON_NOT_CLIENT_FAILURE =
            "Invalid person. Only clients can be assigned a policy";
    public static final String MESSAGE_PERSON_INVALID_DIRECTION_FAILURE =
            "Invalid direction. Direction must be either 'up' to upgrade the status or 'down' to downgrade the status";

    public static final String CHANGE_STATUS_UP = "up";
    public static final String CHANGE_STATUS_DOWN = "down";
    public static final String CHANGE_STATUS_RESET = "";

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
        case CHANGE_STATUS_UP:
            clientStatus = personToEdit.getClientStatus().increment();
            break;
        case CHANGE_STATUS_DOWN:
            clientStatus = personToEdit.getClientStatus().decrement();
            break;
        case CHANGE_STATUS_RESET:
            clientStatus = ClientStatus.initClientStatus();
            break;
        default:
            throw new CommandException(MESSAGE_PERSON_INVALID_DIRECTION_FAILURE);
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRelationship(), personToEdit.getPolicies(),
                clientStatus, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the {@code ClientStatus} is upgraded, downgraded,
     * or reset.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message;

        switch (direction) {
        case CHANGE_STATUS_UP:
            message = MESSAGE_STATUS_UP_SUCCESS;
            break;
        case CHANGE_STATUS_DOWN:
            message = MESSAGE_STATUS_DOWN_SUCCESS;
            break;
        case CHANGE_STATUS_RESET:
            message = MESSAGE_STATUS_RESET_SUCCESS;
            break;
        default:
            message = MESSAGE_PERSON_INVALID_DIRECTION_FAILURE;
        }

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
