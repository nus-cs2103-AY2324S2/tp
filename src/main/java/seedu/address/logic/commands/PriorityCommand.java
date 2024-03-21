package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Priority;

/**
 * Changes the priority of an existing person in the address book.
 */
public class PriorityCommand extends Command {

    public static final String COMMAND_WORD_MED = "pr/med";
    public static final String COMMAND_WORD_HIGH = "pr/high";

    public static final String MESSAGE_USAGE = COMMAND_WORD_MED + " or "
            + COMMAND_WORD_HIGH + ": Sets the priority of the contact identified "
            + "by the contact name to medium or high respectively.\n"
            + "Parameters: CONTACT_NAME\n"
            + "Example: " + COMMAND_WORD_MED + " John Doe";

    public static final String MESSAGE_PERSON_NOT_FOUND = "Oops, %1$s's contact does not exist.";
    public static final String MESSAGE_EMPTY_NAME = "Oops, please state the name of the contact.";
    public static final String MESSAGE_ADD_PRIORITY_SUCCESS = "Added this contact with %1$s priority:\n"
            + "%2$s\tName: %3$s | Phone: %4$s | Email: %5$s";
    public static final String MESSAGE_DELETE_PRIORITY_SUCCESS = "Removed the priority level from %1$s's contact";

    private final String name;
    private final Priority priority;

    /**
     * Constructs a PriorityCommand.
     *
     * @param name Name of the person in the filtered person list to edit the priority.
     * @param priority Priority of the person to be updated to.
     */
    public PriorityCommand(String name, Priority priority) {
        requireAllNonNull(name, priority);

        this.name = name;
        this.priority = priority;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (name.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_NAME, name));
        }
        List<Person> contactList = model.getFilteredPersonList();
        Person personToEdit = null;
        for (Person person : contactList) {
            if (person.getName().fullName.equalsIgnoreCase(name)) {
                personToEdit = person;
                break;
            }
        }
        if (personToEdit == null) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, name));
        }
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getCompany(), priority, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the priority is added to or removed from {@code personToEdit}.
     *
     * @param personToEdit Person whose priority is edited.
     * @return A success message indicating the priority change.
     */
    private String generateSuccessMessage(Person personToEdit) {
        if (priority.value.isEmpty()) {
            return String.format(MESSAGE_DELETE_PRIORITY_SUCCESS, personToEdit.getName());
        }

        String priorityMessage = priority.value.equals("high") ? "high" : "medium";
        String formattingCharacter = priority.value.equals("high") ? "**" : "*";

        return String.format(MESSAGE_ADD_PRIORITY_SUCCESS, priorityMessage, formattingCharacter,
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PriorityCommand)) {
            return false;
        }

        PriorityCommand e = (PriorityCommand) other;
        return name.equals(e.name)
                && priority.equals(e.priority);
    }
}
