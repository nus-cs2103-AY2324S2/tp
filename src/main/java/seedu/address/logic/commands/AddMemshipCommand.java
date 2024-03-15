package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMSHIP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Adds a membership tier for a person to the address book.
 */
public class AddMemshipCommand extends Command {
    public static final String COMMAND_WORD = "addmship";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add membership tier of the person " + "identified "
            + "by the name of the person in the listing. "
            + "Existing membership will be overwritten by the input.\n"
            + "Parameters: NAME " + PREFIX_MEMSHIP + "[MEMBERSHIP_TIER]\n"
            + "Example: " + COMMAND_WORD + " Alice Bob "
            + PREFIX_MEMSHIP + "T1";
    public static final String MESSAGE_ADD_MEMBERSHIP_SUCCESS = "Added membership to Person: %1$s";
    public static final String MESSAGE_DELETE_MEMBERSHIP_SUCCESS =
          "Removed membership from Person: %1$s";
    private final Name name;
    private final String mship;

    /**
     * @param name of the person in the filtered person list to edit the remark
     * @param mship of the person to be updated to
     */
    public AddMemshipCommand(Name name, String mship) {
        requireAllNonNull(name, mship);

        this.name = name;
        this.mship = mship;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Optional<Person> personOptional = lastShownList.stream()
                .filter(person -> person.getName().equals(this.name))
                .findFirst();

        if (personOptional.isEmpty()) {
            throw new CommandException(
                    Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        Person personToEdit = personOptional.get();

        Membership newMembership = new Membership(this.mship);

        Person editedPerson = new Person(personToEdit.getName(),
                personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), newMembership,
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !mship.isEmpty() ? MESSAGE_ADD_MEMBERSHIP_SUCCESS : MESSAGE_DELETE_MEMBERSHIP_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMemshipCommand)) {
            return false;
        }

        // state check
        AddMemshipCommand e = (AddMemshipCommand) other;
        return name.equals(e.name)
                && mship.equals(e.mship);
    }
}
