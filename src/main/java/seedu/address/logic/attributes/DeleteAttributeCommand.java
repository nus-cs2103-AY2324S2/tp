package seedu.address.logic.attributes;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * A command to delete an attribute from a person in the address book.
 * This command identifies the person by their UUID and removes the specified attribute.
 */
public class DeleteAttributeCommand extends Command {

    public static final String COMMAND_WORD = "deleteAttribute";
    private static final String MESSAGE_SUCCESS = "Attribute deleted from person: %1$s";
    private final String toDelete;
    private final String attributeName;

    /**
     * Constructs a DeleteAttributeCommand to delete an attribute from the specified person.
     *
     * @param person        The identifier (UUID) of the person from whom the attribute will be deleted.
     * @param attributeName The name of the attribute to be deleted.
     */
    public DeleteAttributeCommand(String person, String attributeName) {
        this.toDelete = person;
        this.attributeName = attributeName;
    }

    /**
     * Executes the command to delete an attribute from a person identified by UUID.
     * Verifies the existence of the person and the attribute before proceeding with deletion.
     *
     * @param model The model interface containing the address book data and methods needed to perform operations.
     * @return A CommandResult object containing the success message.
     * @throws CommandException if the person with the specified UUID cannot be found or  attribute does not exist.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        UUID fullUuid = model.getFullUuid(toDelete);
        Person personToDelete = model.getPersonByUuid(fullUuid);
        if (personToDelete == null) {
            throw new CommandException("Person not found.");
        }

        if (!model.hasAttribute(personToDelete.getUuidString(), attributeName)) {
            throw new CommandException("Attribute not found.");
        }

        model.deleteAttribute(personToDelete.getUuidString(), attributeName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personToDelete)));
    }

}
