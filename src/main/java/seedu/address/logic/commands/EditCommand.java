package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
<<<<<<< HEAD
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
=======
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
>>>>>>> master
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
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
<<<<<<< HEAD
import seedu.address.model.tag.Tag;
=======
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
>>>>>>> master

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_STUDENTID + "STUDENTID] "
            + "[" + PREFIX_NAME + "NAME] "

            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_GROUP + "GROUP]...\n"
            + "Example: " + COMMAND_WORD + " 1 "

            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

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
<<<<<<< HEAD

=======
        StudentId updatedStudentId = editPersonDescriptor.getStudentId().orElse(personToEdit.getStudentId());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
>>>>>>> master
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Grade updatedGrade = editPersonDescriptor.getGrade().orElse(personToEdit.getGrade());
        Set<Group> updatedGroups = editPersonDescriptor.getGroups().orElse(personToEdit.getGroups());

<<<<<<< HEAD
        return new Person(updatedName, updatedEmail, updatedAddress, updatedTags);
=======
        return new Person(updatedName, updatedStudentId, updatedPhone, updatedEmail, updatedGrade, updatedGroups);
>>>>>>> master
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
<<<<<<< HEAD

=======
        private StudentId studentId;
        private Phone phone;
>>>>>>> master
        private Email email;
        private Set<Group> groups;
        private Grade grade;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code groups} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
<<<<<<< HEAD

=======
            setStudentId(toCopy.studentId);
            setPhone(toCopy.phone);
>>>>>>> master
            setEmail(toCopy.email);
            setGroups(toCopy.groups);
            setGrade(toCopy.grade);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
<<<<<<< HEAD
            return CollectionUtil.isAnyNonNull(name, email, address, tags);
=======
            return CollectionUtil.isAnyNonNull(name, studentId, phone, email, grade, groups);
>>>>>>> master
        }

        public void setName(Name name) {
            this.name = name;
        }

        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }





        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
        }

        /**
         * Sets {@code groups} to this object's {@code groups}.
         * A defensive copy of {@code groups} is used internally.
         */
        public void setGroups(Set<Group> groups) {
            this.groups = (groups != null) ? new HashSet<>(groups) : null;
        }

        /**
         * Returns an unmodifiable group set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code groups} is null.
         */
        public Optional<Set<Group>> getGroups() {
            return (groups != null) ? Optional.of(Collections.unmodifiableSet(groups)) : Optional.empty();
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
<<<<<<< HEAD

=======
                    && Objects.equals(studentId, otherEditPersonDescriptor.studentId)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
>>>>>>> master
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(grade, otherEditPersonDescriptor.grade)
                    && Objects.equals(groups, otherEditPersonDescriptor.groups);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
<<<<<<< HEAD

=======
                    .add("studentId", studentId)
                    .add("phone", phone)
>>>>>>> master
                    .add("email", email)
                    .add("grade", grade)
                    .add("groups", groups)
                    .toString();
        }
    }
}
