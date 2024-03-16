package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Updates the details of an existing person in the address book.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the person identified "
            + "by their full name used in the displayed person list. "
            + "The full name provided is not required to be case sensitive."
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: NAME "
            + "[" + PREFIX_NAME + "NEW_NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_NOK + "NEXT_OF_KIN] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_UPDATE_PERSON_SUCCESS = "Updated Person: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Name name;
    private final UpdatePersonDescriptor updatePersonDescriptor;

    /**
     * @param name of the person in the filtered person list to update
     * @param updatePersonDescriptor details to update the person with
     */
    public UpdateCommand(Name name, UpdatePersonDescriptor updatePersonDescriptor) {
        requireNonNull(name);
        requireNonNull(updatePersonDescriptor);

        this.name = name;
        this.updatePersonDescriptor = new UpdatePersonDescriptor(updatePersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        List<Person> sameNamePeople = lastShownList.stream()
                .filter(person -> person.getLowerCaseName().equals(name))
                .collect(Collectors.toList());

        if (sameNamePeople.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        Person personToUpdate = sameNamePeople.get(0);

        Person updatedPerson = createUpdatedPerson(personToUpdate, updatePersonDescriptor);

        if (!personToUpdate.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToUpdate, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UPDATE_PERSON_SUCCESS, Messages.format(updatedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToUpdate}
     * updated with {@code updatePersonDescriptor}.
     */
    private static Person createUpdatedPerson(Person personToUpdate, UpdatePersonDescriptor updatePersonDescriptor) {
        assert personToUpdate != null;

        Name updatedName = updatePersonDescriptor.getName().orElse(personToUpdate.getName());
        Phone updatedPhone = updatePersonDescriptor.getPhone().orElse(personToUpdate.getPhone());
        Email updatedEmail = updatePersonDescriptor.getEmail().orElse(personToUpdate.getEmail());
        Address updatedAddress = updatePersonDescriptor.getAddress().orElse(personToUpdate.getAddress());
        Description updatedDescription = updatePersonDescriptor.getDescription().orElse(personToUpdate.getDescription());
        NextOfKin updatedNextOfKin = updatePersonDescriptor.getNextOfKin().orElse(personToUpdate.getNextOfKin());
        Set<Tag> updatedTags = updatePersonDescriptor.getTags().orElse(personToUpdate.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedDescription, updatedNextOfKin, updatedTags
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        UpdateCommand otherUpdateCommand = (UpdateCommand) other;
        return name.equals(otherUpdateCommand.name)
                && updatePersonDescriptor.equals(otherUpdateCommand.updatePersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("updatePersonDescriptor", updatePersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to update the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class UpdatePersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Description description;
        private NextOfKin nextOfKin;
        private Set<Tag> tags;

        public UpdatePersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdatePersonDescriptor(UpdatePersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setDescription(toCopy.description);
            setNextOfKin(toCopy.nextOfKin);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is updated.
         */
        public boolean isAnyFieldUpdated() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setNextOfKin(NextOfKin nextOfKin) {
            this.nextOfKin = nextOfKin;
        }

        public Optional<NextOfKin> getNextOfKin() {
            return Optional.ofNullable(nextOfKin);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdatePersonDescriptor)) {
                return false;
            }

            UpdatePersonDescriptor otherUpdatePersonDescriptor = (UpdatePersonDescriptor) other;
            return Objects.equals(name, otherUpdatePersonDescriptor.name)
                    && Objects.equals(phone, otherUpdatePersonDescriptor.phone)
                    && Objects.equals(email, otherUpdatePersonDescriptor.email)
                    && Objects.equals(address, otherUpdatePersonDescriptor.address)
                    && Objects.equals(description, otherUpdatePersonDescriptor.description)
                    && Objects.equals(nextOfKin, otherUpdatePersonDescriptor.nextOfKin)
                    && Objects.equals(tags, otherUpdatePersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("description", description)
                    .add("nextOfKin", nextOfKin)
                    .add("tags", tags)
                    .toString();
        }
    }
}
