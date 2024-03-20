package seedu.address.logic.attributes;

import java.util.UUID;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;

/**
 * A command to add a new attribute to a person in the address book, or to update an existing attribute.
 * This command can also be used to delete an attribute by providing a null value for the attribute value.
 */
public class AddAttributeCommand extends Command {

    public static final String COMMAND_WORD = "addAttribute";
    private final String uuid;
    private final String attributeName;
    private final String attributeValue;

    /**
     * Constructs an EditPersonCommand to add or delete an attribute.
     *
     * @param uuid           The UUID of the person to edit.
     * @param attributeName  The name of the attribute to add/delete.
     * @param attributeValue The value of the attribute to add (null if deleting).
     */
    public AddAttributeCommand(String uuid, String attributeName, String attributeValue) {
        this.uuid = uuid;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    /**
     * Executes the command to add or delete an attribute for a person identified by UUID.
     *
     * @param model The model interface containing the address book data and methods needed to perform operations.
     * @return A CommandResult object containing the result message.
     * @throws CommandException if the person with the specified UUID cannot be found.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        UUID uuidToUse = model.getFullUuid(uuid);
        Person person = model.getPersonByUuid(uuidToUse);
        if (person == null) {
            throw new CommandException("Person not found.");
        }

        Attribute attribute = Attribute.fromString(attributeName, attributeValue);
        person.updateAttribute(attribute);
        return new CommandResult(String.format("Attribute %s added to person %s.", attributeName, uuid));

    }
}

