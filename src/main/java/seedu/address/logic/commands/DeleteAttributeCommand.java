package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

import seedu.address.logic.Messages;
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
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes attributes from a person in the address book. "
            + "\n"
            + "Command format:  " + COMMAND_WORD + " UUID /attributeName1 /attributeName2 ...\n"
            + "Example: " + COMMAND_WORD + " f8d9 /Name /Phone";
    private final String toDelete;
    private final String[] attributeList;

    /**
     * Constructs a DeleteAttributeCommand to delete an attribute from the specified person.
     *
     * @param person        The identifier (UUID) of the person from whom the attribute will be deleted.
     * @param attributeList  The list of attribute names to delete from the person.
     */
    public DeleteAttributeCommand(String person, String[] attributeList) {
        this.toDelete = person;
        this.attributeList = attributeList;
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
        // Get person object
        requireNonNull(model);
        UUID fullUuid = model.getFullUuid(toDelete);

        if (fullUuid == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_UUID + toDelete);
        }

        Person personToDelete = model.getPersonByUuid(fullUuid);

        // Perform deletion of attribute
        for (String attributeName : attributeList) {
            if (!model.hasAttribute(personToDelete.getUuidString(), attributeName)) {
                throw new CommandException(Messages.MESSAGE_NO_SUCH_ATTRIBUTE + attributeName);
            }
        }

        for (String attributeName : attributeList) {
            model.deleteAttribute(personToDelete.getUuidString(), attributeName);
        }

        // Return result
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personToDelete)));
    }
}
