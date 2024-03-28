package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unstars a contact in the address book.
 */
public class UnstarCommand extends Command {
    public static final String COMMAND_WORD = "unstar";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unstars the contact in Connectify.\n"
            + "Parameters: <contact_name>\n"
            + "Example: " + COMMAND_WORD + " Alex Tan";

    private final String contactName;

    /**
     * Constructs a {@code UnstarCommand} to unstar the specified contact.
     *
     * @param contactName The name of the contact to be unstarred.
     */
    public UnstarCommand(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Find the person by name
        List<Person> personsFound = model.getFilteredPersonList();
        Person contactToUnstar = null;
        for (Person person : personsFound) {
            if (person.getName().fullName.equalsIgnoreCase(contactName)) {
                contactToUnstar = person;
                break;
            }
        }

        if (contactToUnstar == null) {
            throw new CommandException("Error! Contact not found: " + contactName);
        }

        if (!contactToUnstar.isStarred()) {
            throw new CommandException("Error! Contact is not starred: " + contactName);
        }

        // Unstar the contact
        contactToUnstar.unstarContact();
        Person unstarredContact = new Person(contactToUnstar.getName(), contactToUnstar.getPhone(),
                contactToUnstar.getEmail(), contactToUnstar.getAddress(), contactToUnstar.getCompany(),
                contactToUnstar.getMeeting(), contactToUnstar.getPriority(), contactToUnstar.isStarred(),
                contactToUnstar.getTags());

        model.setPerson(contactToUnstar, unstarredContact);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format("Nice! You have unstarred this contact:\n"
                + contactToUnstar.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnstarCommand)) {
            return false;
        }

        UnstarCommand e = (UnstarCommand) other;
        return contactName.equals(e.contactName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactName", this.contactName)
                .toString();
    }
}
