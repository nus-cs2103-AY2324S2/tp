package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Command to star a contact in Connectify.
 */
public class StarCommand extends Command {
    public static final String COMMAND_WORD = "star";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Stars the contact in Connectify.\n"
            + "Parameters: <contact_name>\n"
            + "Example: " + COMMAND_WORD + " Alex Tan";

    private final String contactName;

    /**
     * Constructs a {@code StarCommand} to star the specified contact.
     *
     * @param contactName The name of the contact to be starred.
     */
    public StarCommand(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Find the person by name
        List<Person> personsFound = model.getFilteredPersonList();
        Person contactToStar = null;
        for (Person person : personsFound) {
            if (person.getName().fullName.equalsIgnoreCase(contactName)) {
                contactToStar = person;
                break;
            }
        }

        if (contactToStar == null) {
            throw new CommandException("Error! Contact not found: " + contactName);
        }

        // Star the contact
        contactToStar.starContact();
        Person starredContact = new Person(contactToStar.getName(), contactToStar.getPhone(), contactToStar.getEmail(),
                contactToStar.getAddress(), contactToStar.getCompany(),
                contactToStar.getPriority(), contactToStar.isStarred(), contactToStar.getTags());

        model.setPerson(contactToStar, starredContact);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult("Nice! I have starred this contact:\n" + contactToStar.getName() + " ★");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StarCommand)) {
            return false;
        }

        StarCommand e = (StarCommand) other;
        return contactName.equals(e.contactName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactName", this.contactName)
                .toString();
    }
}
