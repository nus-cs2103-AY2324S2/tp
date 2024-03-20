package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the student id used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: STUDENT_ID (must be positive 5-digit integers)  "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PARENT_PHONES + "PHONE, WHICH PHONE NUMBER TO EDIT] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STUDENT_ID + "STUDENT_ID] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PARENT_PHONES + "91234567, 2"
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    private final StudentId studentId;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param studentId of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(StudentId studentId, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(studentId);
        requireNonNull(editPersonDescriptor);

        this.studentId = studentId;

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToEdit = null;
        boolean found = false;

        for (Person person : lastShownList) {
            if (person.getStudentId().equals(this.studentId)) {
                personToEdit = person;
                found = true;
                break;
            }
        }

        if(!found) {
            throw new CommandException("No person found with the given student ID.");
        }

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
        Phone updatedParentPhoneOne =
                editPersonDescriptor.getFirstParentPhone().orElse(personToEdit.getParentPhoneOne());
        Phone updatedParentPhoneTwo =
                editPersonDescriptor.getSecondParentPhone().orElse(personToEdit.getParentPhoneTwo());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        StudentId updatedStudentId = editPersonDescriptor.getStudentId().orElse(personToEdit.getStudentId());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedParentPhoneOne, updatedParentPhoneTwo, updatedEmail, updatedAddress,
                updatedStudentId, updatedTags);
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
        return studentId.equals(otherEditCommand.studentId)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone firstParentPhone;
        private Phone secondParentPhone;
        private Email email;
        private Address address;
        private StudentId studentId;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setFirstParentPhone(toCopy.firstParentPhone);
            setSecondParentPhone(toCopy.secondParentPhone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setStudentId(toCopy.studentId);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, firstParentPhone, secondParentPhone, email, address, studentId,
                    tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone, String numberToEdit) {
            if ("1".equals(numberToEdit)) {
                setFirstParentPhone(phone);
            } else if ("2".equals(numberToEdit)) {
                setSecondParentPhone(phone);
            }
        }

        public void setFirstParentPhone(Phone phone) {
            this.firstParentPhone = phone;
        }

        public void setSecondParentPhone(Phone phone) {
            this.secondParentPhone = phone;
        }

        public Optional<Phone> getFirstParentPhone() {
            return Optional.ofNullable(firstParentPhone);
        }

        public Optional<Phone> getSecondParentPhone() { return Optional.ofNullable(secondParentPhone); }

        public Optional<Phone> getEditedPhone() {
            if (firstParentPhone != null) {
                return Optional.ofNullable(firstParentPhone);
            } else {
                return Optional.ofNullable(secondParentPhone);
            }
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

        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
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
            if (firstParentPhone != null) {
                return Objects.equals(name, otherEditPersonDescriptor.name)
                        && Objects.equals(firstParentPhone, otherEditPersonDescriptor.firstParentPhone)
                        && Objects.equals(email, otherEditPersonDescriptor.email)
                        && Objects.equals(address, otherEditPersonDescriptor.address)
                        && Objects.equals(studentId, otherEditPersonDescriptor.studentId)
                        && Objects.equals(tags, otherEditPersonDescriptor.tags);
            } else {
                return Objects.equals(name, otherEditPersonDescriptor.name)
                        && Objects.equals(secondParentPhone, otherEditPersonDescriptor.secondParentPhone)
                        && Objects.equals(email, otherEditPersonDescriptor.email)
                        && Objects.equals(address, otherEditPersonDescriptor.address)
                        && Objects.equals(studentId, otherEditPersonDescriptor.studentId)
                        && Objects.equals(tags, otherEditPersonDescriptor.tags);
            }
        }

        @Override
        public String toString() {
            if (firstParentPhone != null) {
                return new ToStringBuilder(this)
                        .add("name", name)
                        .add("edited phone", firstParentPhone)
                        .add("email", email)
                        .add("address", address)
                        .add("student id", studentId)
                        .add("tags", tags)
                        .toString();
            } else {
                return new ToStringBuilder(this)
                        .add("name", name)
                        .add("edited phone", secondParentPhone)
                        .add("email", email)
                        .add("address", address)
                        .add("student id", studentId)
                        .add("tags", tags)
                        .toString();
            }
        }
    }
}
