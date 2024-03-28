package seedu.edulink.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_INTAKE;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.edulink.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.edulink.commons.core.index.Index;
import seedu.edulink.commons.util.CollectionUtil;
import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Address;
import seedu.edulink.model.student.Email;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Intake;
import seedu.edulink.model.student.Major;
import seedu.edulink.model.student.Name;
import seedu.edulink.model.student.Phone;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
        + "by the index number used in the displayed person list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_ID + "ID] "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_MAJOR + "MAJOR] "
        + "[" + PREFIX_INTAKE + "INTAKE] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_ID + "A0951516M "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_MAJOR + "Physics "
        + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_NO_CHANGE = "There are no Changes according to the Arguments Provided";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
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
        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedPerson(studentToEdit, editPersonDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedStudent)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedPerson(Student studentToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(studentToEdit.getTags());
        Id updatedId = editPersonDescriptor.getId().orElse(studentToEdit.getId());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(studentToEdit.getMajor());
        Intake updatedIntake = editPersonDescriptor.getIntake().orElse(studentToEdit.getIntake());

        return new Student(updatedId, updatedMajor, updatedIntake, updatedName, updatedPhone,
            updatedEmail, updatedAddress, updatedTags);
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
        private Address address;
        private Set<Tag> tags;
        private Id id;
        private Major major;
        private Intake intake;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setId(toCopy.id);
            setMajor(toCopy.major);
            setIntake(toCopy.intake);
        }

        /**
         * Constructor to create EditPersonDescriptior
         *
         * @param toCopy , person Object to initialise the EditPersonDescriptor
         */
        public EditPersonDescriptor(Student toCopy) {
            setName(toCopy.getName());
            setPhone(toCopy.getPhone());
            setEmail(toCopy.getEmail());
            setAddress(toCopy.getAddress());
            setTags(toCopy.getTags());
            setId(toCopy.getId());
            setMajor(toCopy.getMajor());
            setIntake(toCopy.getIntake());
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(id, major, intake, name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public void setMajor(Major major) {
            this.major = major;
        }

        public void setIntake(Intake intake) {
            this.intake = intake;
        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        public Optional<Major> getMajor() {
            return Optional.ofNullable(major);
        }

        public Optional<Intake> getIntake() {
            return Optional.ofNullable(intake);
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
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(id, otherEditPersonDescriptor.id)
                && Objects.equals(name, otherEditPersonDescriptor.name)
                && Objects.equals(major, otherEditPersonDescriptor.major)
                && Objects.equals(intake, otherEditPersonDescriptor.intake)
                && Objects.equals(phone, otherEditPersonDescriptor.phone)
                && Objects.equals(email, otherEditPersonDescriptor.email)
                && Objects.equals(address, otherEditPersonDescriptor.address)
                && Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("id", id)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("major", major)
                .add("intake", intake)
                .add("tags", tags)
                .toString();
        }
    }
}
