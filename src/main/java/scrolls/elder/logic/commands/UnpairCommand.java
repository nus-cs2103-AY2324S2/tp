package scrolls.elder.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.model.Model;
import scrolls.elder.model.person.Address;
import scrolls.elder.model.person.Befriendee;
import scrolls.elder.model.person.Email;
import scrolls.elder.model.person.Name;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.Phone;
import scrolls.elder.model.person.Role;
import scrolls.elder.model.person.Volunteer;
import scrolls.elder.model.tag.Tag;

/**
 * Unpairs a volunteer and a befriendee who were paired in the address book.
 */
public class UnpairCommand extends Command {
    public static final String COMMAND_WORD = "unpair";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Unpairs a volunteer and a befriendee specified"
                    + "by their index numbers used in the displayed person list.\n"
                    + "Parameters: INDEX1 INDEX2 (both must be a positive integers)\n"
                    + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_UNPAIR_SUCCESS = "Unpaired: %1$s and %2$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "Cannot unpair the same person from themselves.";
    public static final String MESSAGE_NOT_PAIRED = "The two persons are not paired, so unpairing is not possible.";

    private final Index index1;

    private final Index index2;

    /**
     * @param index1 of the first person to be paired
     * @param index2 of the second person to be paired with
     */
    public UnpairCommand(Index index1, Index index2) {
        requireNonNull(index1);
        requireNonNull(index2);

        this.index1 = index1;
        this.index2 = index2;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownBList = model.getFilteredBefriendeeList();
        List<Person> lastShownVList = model.getFilteredVolunteerList();

        if (index1.getZeroBased() >= lastShownBList.size() || index2.getZeroBased() >= lastShownVList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnpair1 = lastShownBList.get(index1.getZeroBased());
        Person personToUnpair2 = lastShownVList.get(index2.getZeroBased());

        // Check if the two persons are the same person
        if (personToUnpair1.isSamePerson(personToUnpair2)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // Check if the two persons are paired
        if (!personToUnpair1.getPairedWith().equals(Optional.of(personToUnpair2.getName()))
                || !personToUnpair2.getPairedWith().equals(Optional.of(personToUnpair1.getName()))) {
            throw new CommandException(MESSAGE_NOT_PAIRED);
        }

        // Unset the pairedWith attribute of the befriendee and volunteer
        Person newPerson1 = createEditedPairedPerson(personToUnpair1, Optional.empty());
        Person newPerson2 = createEditedPairedPerson(personToUnpair2, Optional.empty());
        model.setPerson(personToUnpair1, newPerson1);
        model.setPerson(personToUnpair2, newPerson2);

        // TODO: REMOVE DEAD CODE
        // No longer needed since we are using new Person objects
        // personToUnpair1.setPairedWith(Optional.empty());
        // personToUnpair2.setPairedWith(Optional.empty());
        // model.setPerson(lastShownBList.get(index1.getZeroBased()), personToUnpair1);
        // model.setPerson(lastShownVList.get(index2.getZeroBased()), personToUnpair2);

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(
                        MESSAGE_UNPAIR_SUCCESS, Messages.format(personToUnpair1), Messages.format(personToUnpair2)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnpairCommand)) {
            return false;
        }

        UnpairCommand otherUnpairCommand = (UnpairCommand) other;
        return index1.equals(otherUnpairCommand.index1)
                && index2.equals(otherUnpairCommand.index2);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index1", index1)
                .add("index2", index2)
                .toString();
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPairedPersonDescriptor}.
     */
    private static Person createEditedPairedPerson(Person personToEdit, Optional<Name> updatedPair) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        Role role = personToEdit.getRole();

        Person p;
        if (role.isVolunteer()) {
            p = new Volunteer(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
        } else {
            p = new Befriendee(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
        }
        p.setId(personToEdit.getId());
        p.setPairedWith(personToEdit.getPairedWith());

        // TODO: Change pairedWith to be immutable,
        // Need to change Persons and subclasses to instantiate with final pairedWith.
        p.setPairedWith(updatedPair);
        return p;
    }
}
