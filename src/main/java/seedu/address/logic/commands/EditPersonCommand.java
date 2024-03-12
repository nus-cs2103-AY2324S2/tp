package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;

/**
 * Edits the details of an existing person in the address book.
 * The edit can add, update, or delete attributes of the person identified by a UUID.
 */
public class EditPersonCommand extends Command {
    public static final String COMMAND_WORD = "editperson";

    public static final String MESSAGE_USAGE = "editperson: Edits the attributes of the person identified "
            + "by the UUID number given when the person is input into the system. "
            + "Existing values will be overwritten by the input values for the add operation, "
            + "and attributes will be removed for the delete operation.\n"
            + "Parameters for adding an attribute: UUID (must be a valid UUID) /add /attributename attributevalue\n"
            + "Example for adding an attribute: editperson 1234 /add /email johndoe@example.com\n"
            + "Parameters for deleting an attribute: UUID (must be a valid UUID) /delete /attributename\n"
            + "Example for deleting an attribute: editperson 1234 /delete /email";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final UUID uuid;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates an EditPersonCommand to edit the person specified by
     * the UUID using the details provided in the descriptor.
     *
     * @param uuid The UUID of the person to be edited.
     * @param editPersonDescriptor Descriptor that contains the attributes to be edited.
     */
    public EditPersonCommand(UUID uuid, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(uuid);
        requireNonNull(editPersonDescriptor);

        this.uuid = uuid;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    /**
     * Executes the command to edit a person in the address book.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A CommandResult object containing success message.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Assuming `uuid` is a String variable containing the UUID of the person to edit
        int personIndex = findIndexByUuid(lastShownList, String.valueOf(uuid));

        if (personIndex == -1) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }

        Person personToEdit = lastShownList.get(personIndex);

        // Assuming createEditedAttributePerson method is adapted to work with dynamic attributes
        Person editedPerson = createEditedAttributePerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedAttributePerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        // Clone the person to edit to start editing its attributes
        Person editedPerson = new Person(personToEdit);

        // Iterate over the attributes in the descriptor and apply changes
        for (Map.Entry<String, Attribute> entry : editPersonDescriptor.getAttributes().entrySet()) {
            String attributeName = entry.getKey();
            Attribute attributeValue = entry.getValue();

            // You need a method in Person to update attributes by name
            editedPerson.setAttribute(attributeName, attributeValue);
        }

        return editedPerson;
    }

    /**
     * Finds and returns the index of the person with the given UUID in the list.
     *
     * @param personList The list of persons to search through.
     * @param uuidStr The UUID string of the person to find.
     * @return The index of the person if found, -1 otherwise.
     */
    public int findIndexByUuid(List<Person> personList, String uuidStr) {
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getUuid().equals(uuidStr)) {
                return i; // Return the index of the found person
            }
        }
        return -1; // Return -1 if not found
    }

    /**
     * Gets the edit descriptor for this command.
     *
     * @return The EditPersonDescriptor used in this command.
     */
    public EditPersonDescriptor getDescriptor() {

        return this.editPersonDescriptor;
    }
}


