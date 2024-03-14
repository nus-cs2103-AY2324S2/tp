package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;
import static staffconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import staffconnect.commons.core.index.Index;
import staffconnect.commons.util.CollectionUtil;
import staffconnect.commons.util.ToStringBuilder;
import staffconnect.logic.Messages;
import staffconnect.logic.commands.exceptions.CommandException;
import staffconnect.model.Model;
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Email;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Person;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;
import staffconnect.model.tag.Tag;

/**
 * Edits the details of an existing person in the staff book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_VENUE + "VENUE] "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_AVAILABILITY + "AVAILABILITY]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the staff book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Venue updatedVenue = editPersonDescriptor.getVenue().orElse(personToEdit.getVenue());
        Module updatedModule = editPersonDescriptor.getModule().orElse(personToEdit.getModule());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Availability> updatedAvailabilities = editPersonDescriptor.getAvailabilities()
                .orElse(personToEdit.getAvailabilities());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedVenue, updatedModule,
                updatedTags, updatedAvailabilities);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Venue venue;
        private Module module;
        private Set<Tag> tags;
        private Set<Availability> availabilities;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} and {@code availabilities} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setVenue(toCopy.venue);
            setModule(toCopy.module);
            setTags(toCopy.tags);
            setAvailabilities(toCopy.availabilities);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, venue, module, tags, availabilities);
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

        public void setVenue(Venue venue) {
            this.venue = venue;
        }

        public Optional<Venue> getVenue() {
            return Optional.ofNullable(venue);
        }

        public void setModule(Module module) {
            this.module = module;
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
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

        /**
         * Sets {@code availabilities} to this object's {@code availabilities}.
         * A defensive copy of {@code availabilities} is used internally.
         */
        public void setAvailabilities(Set<Availability> availabilities) {
            this.availabilities = (availabilities != null) ? new HashSet<>(availabilities) : null;
        }

        /**
         * Returns an unmodifiable availability set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code availabilities} is null.
         */
        public Optional<Set<Availability>> getAvailabilities() {
            return (availabilities != null)
                    ? Optional.of(Collections.unmodifiableSet(availabilities))
                    : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(venue, otherEditPersonDescriptor.venue)
                    && Objects.equals(module, otherEditPersonDescriptor.module)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(availabilities, otherEditPersonDescriptor.availabilities);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("venue", venue)
                    .add("module", module)
                    .add("tags", tags)
                    .add("availabilities", availabilities)
                    .toString();
        }
    }
}
